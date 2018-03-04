package ru.ovcharov_alexey.tanks.v4.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import static ru.ovcharov_alexey.tanks.v4.engine.Global.RANDOM;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameEvent;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameListener;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Visibility;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Liveable;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.bonus.Bonus;
import ru.ovcharov_alexey.tanks.v4.engine.units.bonus.BonusType;
import ru.ovcharov_alexey.tanks.v4.engine.units.bonus.GameContext;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.Shell;
import ru.ovcharov_alexey.tanks.v4.engine.units.factory.UnitFactory;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.InvisibleBomb;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.ShellPool;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Level;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.LevelAndCampaign;
import ru.ovcharov_alexey.tanks.v4.ui.forms.LoadGameForm;

/**
 * @author Alexey
 */
public class Game implements Runnable {

    private static final int TIME_LIMIT_WITHOUT_MOVE = 90;

    private static final int TWO_SECONDS = 2000;

    private static final int ONE_SECOND = 1000;

    private static int maxBonusTime;

    private static int getMaxBonusTime() {
        float speed = Global.getSpeed();
        if (speed >= 50) {
            return Math.round(30 - speed / 5);
        } else {
            return Math.round(40 - 2 * speed / 5);
        }
    }
    private final int height;
    private final int width;
    private final Map<String, Long> achievementsToDraw = new ConcurrentHashMap<>();
    private boolean fastGame;

    private CombatUnit playerUnit;
    private final List<DamageDealer> shells;
    private List<CombatUnit> enemies;
    private Level currentLevel;
    private Iterator<Level> levelIterator;
    private final BufferedImage bufferedImage;
    private Timer bonusTimer;
    private Timer fastGameTimer;

    private int time;
    private final Collection<DamageDealer> enemiesShells;
    private Thread thread;
    private final BufferStrategy bufferStrategy;
    private final Map<Integer, Boolean> keys = new HashMap<>();
    private final List<Bonus> bonuses = new ArrayList<>();
    private final List<Explosion> explosions = new ArrayList<>();
    private double yScale;
    private double xScale;

    private final float delay = 1000f / Global.getScaledSpeed();
    private boolean enemiesCanMove;
    private Bonus currentBonus;
    private int bonusTimerTime;
    private GameMode gameMode;
    private final List<GameListener> listeners = new ArrayList<>();
    private final int explosionDelay = 4;
    private String framesString = "";
    private BufferedImage pauseScreen;
    private BufferedImage winScreen;
    private BufferedImage loseScreen;
    private BufferedImage nextLevel;
    private BufferedImage achievementScreen;
    private int currentExperience;
    private int experienceForNextSkill;
    private int playerSkill;
    private final List<DamageText> damageTextList = new ArrayList<>();
    private int secondsWithoutMove;
    private static final Logger LOGGER = Global.getLogger();

    public Game(Canvas canvas, int width, int height) throws IOException {
        gameMode = GameMode.OFF;
        this.width = width;
        this.height = height;
        shells = new ArrayList<>();
        enemiesShells = new ArrayList<>();
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int timeToFastGame = (int) (5 * delay * ONE_SECOND);
        fastGameTimer = new Timer(timeToFastGame, (ActionEvent e) -> {
            fastGameTimer.stop();
            if (!Global.hasAcheivement(Acheivements.FAST_GAME)) {
                LOGGER.info("Достижение " + Acheivements.FAST_GAME + " провалено");
            }
            fastGame = false;
        });
        fastGameTimer.setRepeats(true);
        bonusTimer = new Timer(ONE_SECOND, (ActionEvent e) -> {
            if (bonusTimerTime-- <= 0) {
                clearBonus();
                bonusTimer.stop();
            }
        });
        bonusTimer.setRepeats(true);
    }

    private void updateAchievements() {
        long now = System.nanoTime();
        for (Iterator<Long> iterator = achievementsToDraw.values().iterator(); iterator.hasNext();) {
            Long acTime = iterator.next();
            if (now > acTime + TimeUnit.MILLISECONDS.toNanos(TWO_SECONDS)) {
                iterator.remove();
            }
        }
    }

    private void relocateShellsSmoothly() {
        /*
           для плавного перемещения вызываем несколько раз перемещение с небольшим шагом
         */
        for (int i = 0; i < 10; i++) {
            relocateShells();
        }
    }

    private void clearBonus() {
        if (currentBonus != null) {
            GameContext gameContext
                    = currentBonus.resetTo(playerUnit, enemiesCanMove);
            enemiesCanMove = gameContext.isEmemiesCanMove();
            playerUnit = gameContext.getPlayerUnit();
            LOGGER.info("Время действия бонуса истекло");
            currentBonus = null;
        }
    }

    public synchronized void start() {
        maxBonusTime = getMaxBonusTime();
        if (gameMode == GameMode.OFF) {
            LOGGER.info("Начинаю игру");
            if (pauseScreen == null || winScreen == null
                    || loseScreen == null || nextLevel == null) {
                LoadGameForm.asyncAction(() -> {
                    try {
                        pauseScreen = ImageIO.read(Game.class.getResourceAsStream(
                                "/images/screens/pause.png"));
                        winScreen = ImageIO.read(Game.class.getResourceAsStream(
                                "/images/screens/win.png"));
                        loseScreen = ImageIO.read(Game.class.getResourceAsStream(
                                "/images/screens/lose.png"));
                        nextLevel = ImageIO.read(Game.class.getResourceAsStream(
                                "/images/screens/next_level.png"));
                        achievementScreen = ImageIO.read(Game.class.getResourceAsStream(
                                "/images/screens/achievement.png"));
                    } catch (IOException e) {
                        LOGGER.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
                    }
                });

            }
            time = 0;
            thread = new Thread(this);
            thread.start();
            enemiesCanMove = true;
            gameMode = GameMode.RUN;
            notifyListeners(GameEvent.GAME_START);
        }
    }

    public synchronized void stop() {
        if (gameMode != GameMode.OFF) {
            LOGGER.info("Завершаю игру");
            gameMode = GameMode.OFF;
            bonusTimer.stop();
            notifyListeners(GameEvent.GAME_BREAK);
            thread.interrupt();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        float delta = 0;
        display();
        int frames = 0;
        long lastTime = System.currentTimeMillis();
        while (gameMode != GameMode.OFF) {
            long now = System.currentTimeMillis();
            long elapsed = (now - lastTime);
            time += elapsed;
            if (time > ONE_SECOND) {
                time = 0;
                if (gameMode == GameMode.RUN) {
                    ++secondsWithoutMove;
                }
                framesString = String.valueOf(frames);
                frames = 0;
            }
            lastTime = now;
            delta += (elapsed / delay);
            boolean render = false;
            while (delta > 1 && gameMode != GameMode.OFF) {
                checkKeys();
                update();
                --delta;
                render = true;
            }
            if (render) {
                display();
                ++frames;
            } else {
                sleep(1L);
            }
        }

    }

    public void initGame(LevelAndCampaign levelAndCampaign) throws IOException {
        init();
        levelIterator = levelAndCampaign.getCampaign().getLevels().iterator();
        for (int i = 0; i <= levelAndCampaign.getLevelNumber()
                && levelIterator.hasNext(); i++) {
            currentLevel = levelIterator.next();
        }
        refreshLevel();
    }

    private void init() throws IOException {
        Explosion.init();
        Bonus.init();
        xScale = (double) width / GeometryMap.MAX_WIDTH;
        yScale = (double) height / GeometryMap.MAX_HEIGTH;
        currentExperience = 0;
        experienceForNextSkill = Global.BASE_EXPERIENCE_FOR_NEXT_SKILL;
        playerSkill = 1;
    }

    public void initRandomGame(GeometryMap map) throws IOException {
        init();
        currentLevel = new Level("Random level");
        currentLevel.setMap(map);
        for (int i = 0; i < 5; i++) {
            currentLevel.addUnit(
                    UnitFactory.createEnemyUnit(UnitType.randomType(RANDOM)));
        }
        currentLevel.setBonusesCount(4);
        levelIterator = new Iterator<Level>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Level next() {
                return currentLevel;
            }
        };
        refreshLevel();
    }

    private void refreshLevel() {
        shells.clear();
        secondsWithoutMove = 0;
        damageTextList.clear();
        enemies = new ArrayList<>(currentLevel.getUnits());
        clearBonus();
        bonusTimer.stop();
        time = 0;
        bonusTimerTime = 0;
        playerUnit = UnitFactory.createPlayerUnit(playerSkill);
        int mapWidth = currentLevel.getMap().getWidth();
        int mapHeight = currentLevel.getMap().getHeight();
        playerUnit.setLocation(mapWidth - playerUnit.getWidth() - 1,
                mapHeight - playerUnit.getHeight() - 1);
        bonuses.clear();
        for (int i = 0; i < currentLevel.getBonusesCount(); i++) {
            int x;
            int y;
            Material material;
            do {
                x = RANDOM.nextInt(mapWidth - Bonus.SIZE);
                y = RANDOM.nextInt(mapHeight - Bonus.SIZE);
                material = currentLevel.getMap().getTile(x, y);
            } while (material == Material.METAL || material == Material.WATER);
            BonusType type = BonusType.randomType(RANDOM);
            bonuses.add(new Bonus(x, y, Bonus.SIZE, type));
        }
        fastGameTimer.start();
        fastGame = true;
    }

    public void pressKey(KeyEvent e) {
        keys.put(e.getKeyCode(), true);
    }

    public void releaseKey(KeyEvent e) {
        keys.put(e.getKeyCode(), false);
    }

    private void checkKeys() {
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_ESCAPE))) {
            if (gameMode == GameMode.RUN) {
                pause();
            } else if (gameMode == GameMode.PAUSE) {
                stop();
            }
        }
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_ENTER))) {
            if (gameMode == GameMode.PAUSE) {
                resume();
            }
        }
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_SPACE))) {
            playerUnit.attack(shells, null);
            notifyListeners(GameEvent.PLAYER_SHOT);
        }
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_RIGHT))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.RIGHT) {
                boolean move = playerUnit.move(currentLevel.getMap(), null);
                if (move) {
                    secondsWithoutMove = 0;
                }
            } else {
                playerUnit.setDirection(new Vector2D(playerUnit.getSpeed(), 0));
            }
        } else if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_LEFT))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.LEFT) {
                boolean move = playerUnit.move(currentLevel.getMap(), null);
                if (move) {
                    secondsWithoutMove = 0;
                }
            } else {
                playerUnit.setDirection(new Vector2D(-playerUnit.getSpeed(), 0));
            }
        } else if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_UP))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.UP) {
                boolean move = playerUnit.move(currentLevel.getMap(), null);
                if (move) {
                    secondsWithoutMove = 0;
                }
            } else {
                playerUnit.setDirection(new Vector2D(0, playerUnit.getSpeed()));
            }
        } else if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_DOWN))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.DOWN) {
                boolean move = playerUnit.move(currentLevel.getMap(), null);
                if (move) {
                    secondsWithoutMove = 0;
                }
            } else {
                playerUnit.setDirection(new Vector2D(0, -playerUnit.getSpeed()));
            }
        }

    }

//<editor-fold defaultstate="collapsed" desc="update">
    private void update() {
        if (gameMode == GameMode.RUN) {
            if (enemies.isEmpty()) {
                if (fastGame) {
                    Global.addAcheivement(Acheivements.FAST_GAME, Acheivements.FAST_GAME_DESCRIPTION);
                    achievementsToDraw.put(Acheivements.FAST_GAME, System.nanoTime());
                }
                if (levelIterator.hasNext()) {
                    drawImage(nextLevel);
                    gameMode = GameMode.PAUSE;
                    currentLevel = levelIterator.next();
                    notifyListeners(GameEvent.GAME_NEXT_LEVEL);
                    refreshLevel();
                } else {
                    drawImage(winScreen);
                    gameMode = GameMode.OFF;
                    sleep(TWO_SECONDS);
                    notifyListeners(GameEvent.GAME_WIN);
                    return;
                }
            }
            if (secondsWithoutMove > TIME_LIMIT_WITHOUT_MOVE) {
                LOGGER.info("Машина игрока более " + TIME_LIMIT_WITHOUT_MOVE
                        + " секунд находится без движения, пауза");
                pause();
            } else if (!playerUnit.isLive()) {
                LOGGER.info("Машина игрока уничтожена врагом, игра проиграна");
                drawImage(loseScreen);
                gameMode = GameMode.OFF;
                sleep(TWO_SECONDS);
                notifyListeners(GameEvent.GAME_LOSE);
            } else {
                updateAttack();
                attackEnemies();
                relocateEnemiesUnits();
                relocateShellsSmoothly();
                removeDeadUnits();
                checkBonuses();
                if (time % explosionDelay == 0) {
                    updateExplosions();
                }
                updateDamageText();
                updateAchievements();
            }
        }
    }

    private void updateExplosions() {
        for (Iterator<Explosion> iterator = explosions.iterator(); iterator.hasNext();) {
            Explosion e = iterator.next();
            e.next();
            if (!e.isLive()) {
                iterator.remove();
            }
        }
    }

    private void removeDeadUnits() {
        for (Iterator<CombatUnit> iterator = enemies.iterator(); iterator.hasNext();) {
            CombatUnit unit = iterator.next();
            if (!unit.isLive()) {
                iterator.remove();
                LOGGER.info("Игрок уничтожил вражескую машину: " + unit.getUnitType());
                notifyListeners(GameEvent.ENEMY_KILL);
                addExperience((int) (Global.BASE_EXPERIENCE_PER_ENEMY
                        * unit.getUnitType().getExperienceFactor()));
            }
        }
    }

    private void checkBonuses() {
        for (Iterator<Bonus> iterator = bonuses.iterator(); iterator.hasNext();) {
            Bonus bonus = iterator.next();
            if (bonus.intersectsWith(playerUnit)) {
                if (bonus.isAllowed(playerUnit, enemiesCanMove, currentBonus)) {
                    GameContext gameContext = bonus.applyTo(playerUnit, enemiesCanMove);
                    playerUnit = gameContext.getPlayerUnit();
                    enemiesCanMove = gameContext.isEmemiesCanMove();
                    if (gameContext.isDurable()) {
                        bonusTimerTime = maxBonusTime;
                        bonusTimer.restart();
                        currentBonus = bonus;
                    }
                    iterator.remove();
                    LOGGER.info("Игрок получает бонус " + bonus.getBonusType());
                }
            }
        }

    }

    private void attackEnemies() {
        enemies.stream().sequential().filter(Liveable::isLive).
                forEach((CombatUnit c) -> {
                    c.attack(enemiesShells, playerUnit);
                    notifyListeners(GameEvent.ENEMY_SHOT);
                });
    }

    private void updateAttack() {
        enemies.stream().forEach((enemy) -> {
            enemy.recharge();
        });
        playerUnit.recharge();
    }

    private synchronized void relocateShells() {
        relocatePlayerShells();
        relocateEnemiesShells();
    }

    private void relocateEnemiesShells() {
        for (Iterator<DamageDealer> iterator = enemiesShells.iterator();
                iterator.hasNext();) {
            DamageDealer dd = iterator.next();
            if (dd.isFixedPosition()) {
                InvisibleBomb bomb = (InvisibleBomb) dd;
                if (bomb.intersectsWith(playerUnit)) {
                    int damage = bomb.getDamage();
                    playerUnit.decreaseHealth(damage);
                    int realDamage = playerUnit.calculateRealDamage(damage);
                    LOGGER.info("Машина игрока получает " + realDamage + " урона от взрыва фугаса");
                    showDamage(-realDamage, bomb.getPoint(), false);
                    explosions.add(new Explosion(bomb));
                    iterator.remove();
                }
            } else {
                Shell s = (Shell) dd;
                if (s.intersectsWith(playerUnit)) {
                    int damage = s.getDamage();
                    if (s.isCritical()) {
                        damage *= Global.CRITICAL_DAMAGE_FACTOR;
                    }
                    playerUnit.decreaseHealth(damage);
                    int realDamage = playerUnit.calculateRealDamage(damage);
                    LOGGER.info("Машина игрока получает " + realDamage + " урона от взрыва снаряда ");
                    showDamage(-realDamage, s.getPoint(), s.isCritical());
                    ShellPool.getInstance().put(s);
                    iterator.remove();
                    explosions.add(new Explosion(s));
                    continue;
                }
                if (!s.move(currentLevel.getMap(), null)) {
                    ShellPool.getInstance().put(s);
                    iterator.remove();
                    explosions.add(new Explosion(s));
                }
            }
        }
    }

    private void relocatePlayerShells() {
        label:
        for (Iterator<DamageDealer> iterator = shells.iterator();
                iterator.hasNext();) {
            Shell s = (Shell) iterator.next();
            for (CombatUnit enemy : enemies) {
                if (s.intersectsWith(enemy)) {
                    int damage = s.getDamage();
                    if (s.isCritical()) {
                        damage *= Global.CRITICAL_DAMAGE_FACTOR;
                    }
                    enemy.decreaseHealth(damage);
                    LOGGER.info("Снаряд игрока наносит " + damage + " урона "
                            + (s.isCritical() ? "(критический эффект" : "")
                            + " вражеской машине: " + enemy.getUnitType());
                    int realDamage = enemy.calculateRealDamage(damage);
                    showDamage(realDamage, s.getPoint(), s.isCritical());
                    enemy.setDirectionOfFire(s.getDirection());
                    ShellPool.getInstance().put(s);
                    explosions.add(new Explosion(s));
                    iterator.remove();
                    continue label;
                }
            }
            if (!s.move(currentLevel.getMap(), null)) {
                ShellPool.getInstance().put(s);
                explosions.add(new Explosion(s));
                iterator.remove();
            }
        }
    }

    private void relocateEnemiesUnits() {
        if (enemiesCanMove) {
            enemies.stream().map((unit) -> {
                changeDirectionOfUnitIfCanNotMove(unit);
                return unit;
            }).forEach((unit) -> {
                changeDirectionOfUnitWithChance(unit);
            });
        }

    }

    private void changeDirectionOfUnitIfCanNotMove(CombatUnit unit) {
        final int restriction = Direction.values().length;
        if (!unit.move(currentLevel.getMap(), playerUnit.getPoint())) {
            Vector2D vector2D = Vector2D.create(
                    Direction.values()[RANDOM.nextInt(restriction)], unit.getSpeed());
            unit.setDirection(vector2D);
        }
    }

    private void changeDirectionOfUnitWithChance(CombatUnit unit) {
        Vector2D directionOfFire = unit.getDirectionOfFire();
        if (directionOfFire != null) {
            Direction approximate = Direction.approximate(directionOfFire);
            Direction orto = approximate.getOrto();
            unit.setDirection(Vector2D.create(orto, unit.getSpeed()));
            unit.decreaseFireDetectTime();
        } else {
            final int chanceDirection = 7;
            final int restriction = Direction.values().length;
            if (RANDOM.nextInt(1000) < chanceDirection) {
                Vector2D vector2D = Vector2D.create(
                        Direction.values()[RANDOM.nextInt(restriction)], unit.getSpeed());
                unit.setDirection(vector2D);
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw">
    private void display() {
        if (gameMode == GameMode.RUN) {
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g.scale(xScale, yScale);
            g.clearRect(0, 0, width, height);
            currentLevel.getMap().draw(g);
            drawBonuses(g);
            drawShells(g);
            drawUnits(g);
            drawExplosions(g);
            drawDamageText(g);
            g.setColor(Color.RED);
            g.drawString(framesString, 10, 10);
            g.drawString("Опыт: " + currentExperience + "/" + experienceForNextSkill, 30, 10);
            g.drawString("Навык: " + playerSkill, 140, 10);
            g.drawString("Уровень: " + currentLevel.getName(), 200, 10);
            drawAchievements(g, width, height);
            bufferStrategy.getDrawGraphics().drawImage(bufferedImage, 0, 0, null);
            bufferStrategy.show();
        }
    }

    private void drawAchievements(Graphics2D g, int mapWidth, int mapHeight) {
        if (!achievementsToDraw.isEmpty()) {
            g.scale(1 / xScale, 1 / yScale);
            String ach = achievementsToDraw.keySet().iterator().next();
            int dh = mapHeight / 4;
            int dy = dh * 3;
            dh -= 10;
            Font font = g.getFont();
            Font largeFont = Global.LARGE_FONT;
            g.setFont(largeFont);
            g.drawImage(achievementScreen, mapWidth / 3, dy, mapWidth / 3, dh, null);
            int recWidth = mapWidth / 3;
            int recHeight = dh;
            int x = mapWidth / 3;
            int y = dy;
            Rectangle rect = new Rectangle(x, y, recWidth, recHeight);
            FontMetrics metrics = g.getFontMetrics(largeFont);
            drawCenteredString(g, ach, rect, metrics);
            g.setFont(font);
        }
    }

    private void drawCenteredString(Graphics2D g, String text, Rectangle rect, FontMetrics metrics) {
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int height = metrics.getHeight();
        int y = rect.y + ((rect.height - height) / 2) + metrics.getAscent();
        g.drawString(text, x, y);
    }

    private void drawBonuses(Graphics2D g) {
        bonuses.stream().forEach((bonus) -> bonus.draw(g));
        if (currentBonus != null) {
            g.setColor(Color.BLUE);
            int startX = currentLevel.getMap().getWidth() - 10 - maxBonusTime * 3;
            g.fillRect(startX, 3, bonusTimerTime * 3, 4);
            g.setColor(Color.BLACK);
            g.drawRect(startX, 3, maxBonusTime * 3, 4);
        }

    }

    private void drawUnits(Graphics2D g) {
        playerUnit.draw(g);
        if (enemies != null && !enemies.isEmpty()) {
            enemies.stream().sequential().forEach((e) -> {
                e.draw(g);
            });
        }
    }

    private synchronized void drawShells(Graphics2D g) {
        shells.parallelStream().filter(Visibility::isVisible).
                forEach((shell) -> {
                    ((Drawable) shell).draw(g);
                });
        enemiesShells.parallelStream().filter(Visibility::isVisible).
                forEach((shell) -> {
                    ((Drawable) shell).draw(g);
                });
    }

    private void drawExplosions(Graphics2D g) {
        explosions.stream().filter((explosion) -> {
            return explosion.isVisible();
        }).forEach((explosion) -> {
            explosion.draw(g);
        });
    }
//</editor-fold>

    private void pause() {
        LOGGER.info("Получил команду на остановку игры");
        bonusTimer.stop();
        gameMode = GameMode.PAUSE;
        drawImage(pauseScreen);
        notifyListeners(GameEvent.GAME_PAUSE);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void notifyListeners(GameEvent event) {
        listeners.stream().forEach((GameListener l) -> l.actionPerformed(event));
    }

    private void resume() {
        LOGGER.info("Получил команду на продолжение игры");
        bonusTimer.start();
        gameMode = GameMode.RUN;
        notifyListeners(GameEvent.GAME_RESUME);
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void addGameListener(GameListener gameListener) {
        this.listeners.add(gameListener);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void drawImage(BufferedImage bufferedImage) {
        Graphics2D drawGraphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        drawGraphics.drawImage(bufferedImage, 0, 0, width, height, null);
        bufferStrategy.show();
    }

    private void addExperience(int extraExperience) {
        currentExperience += extraExperience;
        if (currentExperience >= experienceForNextSkill) {
            currentExperience -= experienceForNextSkill;
            increasePlayerSkill();
            experienceForNextSkill = (int) (experienceForNextSkill * Global.EXPERIENCE_GROW);
        }
    }

    private void increasePlayerSkill() {
        ++playerSkill;
        if (playerUnit != null && playerUnit.isLive()) {
            playerUnit.setDamage(playerUnit.getDamage() + Global.EXTRA_DAMAGE_PER_LEVEL);
            playerUnit.setMaxHealth(playerUnit.getMaxHealth() + Global.EXTRA_HP_PER_LEVEL);
            playerUnit.setCriticalDamageChance(playerUnit.getCriticalDamageChance() + 1);
        }
    }

    private void showDamage(int damage, GeometryPoint point, boolean critical) {
        damageTextList.add(new DamageText(damage, critical, point));
    }

    private void updateDamageText() {
        long systemTime = System.nanoTime();
        for (Iterator<DamageText> iterator = damageTextList.iterator(); iterator.hasNext();) {
            DamageText damageText = iterator.next();
            if (systemTime - damageText.getStartTime() > Global.MAX_SHOW_DAMAGE_TIME) {
                iterator.remove();
            } else {
                damageText.relocate();
            }
        }
    }

    private void drawDamageText(Graphics2D g) {
        damageTextList.stream().forEach((dt) -> dt.draw(g));
    }

}
