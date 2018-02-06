package ru.ovcharov_alexey.tanks.v4.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import java.awt.Graphics2D;
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
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameEvent;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameListener;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
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
import ru.ovcharov_alexey.tanks.v4.logic.forms.LoadGameForm;

/**
 * @author Alexey
 */
public class Game implements Runnable {

    private CombatUnit playerUnit;
    private final List<DamageDealer> shells;
    private List<CombatUnit> enemies;
    private static final Random RANDOM = new Random();
    private Level currentLevel;
    private Iterator<Level> leveliterator;
    private final BufferedImage bufferedImage;
    private Timer timer;

    private int time;
    private final Collection<DamageDealer> enemiesShells;
    private Thread thread;
    private final BufferStrategy bufferStrategy;
    private final Map<Integer, Boolean> keys = new HashMap<>();
    private final List<Bonus> bonuses = new ArrayList<>();
    private final List<Explosion> explosions = new ArrayList<>();
    private double yScale;
    private double xScale;

    private static int maxBonusTime;
    private final float delay = 1000f / (10 + Global.getSpeed());
    private boolean enemiesCanMove;
    private Bonus currentBonus;
    private int timerTime;
    private GameMode gameMode;
    private final List<GameListener> listeners = new ArrayList<>();
    private static final Font MAIN_FONT = new Font("Arial", Font.BOLD, 20);
    private final int explosionDelay = 4;
    private String framesString = "";
    private BufferedImage pauseScreen;
    private BufferedImage winScreen;
    private BufferedImage loseScreen;
    private BufferedImage nextLevel;

    public Game(Canvas canvas) throws IOException {
        gameMode = GameMode.OFF;
        shells = new ArrayList<>();
        enemiesShells = new ArrayList<>();
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        bufferedImage = new BufferedImage((int) Global.getMapWidth(),
                (int) Global.getMapHeight(), BufferedImage.TYPE_INT_RGB);
        timer = new Timer(1000, (ActionEvent e) -> {
            if (timerTime-- <= 0) {
                clearBonus();
                timer.stop();
            }
        });
        timer.setRepeats(true);
    }

    private void clearBonus() {
        if (currentBonus != null) {
            GameContext gameContext
                    = currentBonus.resetTo(playerUnit, enemiesCanMove);
            enemiesCanMove = gameContext.isEmemiesCanMove();
            playerUnit = gameContext.getPlayerUnit();
            currentBonus = null;
        }
    }

    public synchronized void start() {
        maxBonusTime = getMaxBonusTime();
        if (gameMode == GameMode.OFF) {
            Global.getLogger().info("Начинаю игру");
            if (pauseScreen == null || winScreen == null
                    || loseScreen == null || nextLevel == null) {
                try {
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
                        } catch (Exception e) {
                            Global.getLogger().log(java.util.logging.Level.SEVERE,
                                    e.getMessage(), e);
                        }
                    }).join();
                } catch (InterruptedException ex) {
                }

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
            Global.getLogger().info("Завершаю игру");
            gameMode = GameMode.OFF;
            timer.stop();
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
            if (time > 1000) {
                time = 0;
                framesString = String.valueOf(frames);
                frames = 0;
            }
            lastTime = now;
            delta += (elapsed / delay);
            boolean render = false;
            while (delta > 1 && gameMode != GameMode.OFF) {
                checkKeys();
                update();
                delta--;
                render = true;
            }
            if (render) {
                display();
                ++frames;
            } else {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException ex) {
                }
            }
        }

    }

    public void initGame(LevelAndCampaign levelAndCampaign) throws IOException {
        init();
        leveliterator = levelAndCampaign.getCampaign().getLevels().iterator();
        for (int i = 0; i <= levelAndCampaign.getLevelNumber()
                && leveliterator.hasNext(); i++) {
            currentLevel = leveliterator.next();
        }
        refreshLevel();
    }

    private void init() throws IOException {
        Explosion.init();
        Bonus.init();
        xScale = Global.getMapWidth() / GeometryMap.MAX_WIDTH;
        yScale = Global.getMapHeight() / GeometryMap.MAX_HEIGTH;
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
        leveliterator = new Iterator<Level>() {
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
        enemies = new ArrayList<>(currentLevel.getUnits());
        clearBonus();
        timer.stop();
        time = 0;
        timerTime = 0;
        playerUnit = UnitFactory.createPlayerUnit();
        int mapWidth = currentLevel.getMap().getWidth();
        int mapHeight = currentLevel.getMap().getHeight();
        playerUnit.setLocation(mapWidth - CombatUnit.UNIT_SIZE - 1,
                mapHeight - CombatUnit.UNIT_SIZE - 1);
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
        }
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_RIGHT))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.RIGHT) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(new Vector2D(playerUnit.getSpeed(), 0));
            }
        } else if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_LEFT))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.LEFT) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(new Vector2D(-playerUnit.getSpeed(), 0));
            }
        } else if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_UP))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.UP) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(new Vector2D(0, playerUnit.getSpeed()));
            }
        } else if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_DOWN))) {
            if (Direction.approximate(playerUnit.getDirection()) == Direction.DOWN) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(new Vector2D(0, -playerUnit.getSpeed()));
            }
        }

    }

//<editor-fold defaultstate="collapsed" desc="update">
    private void update() {
        if (gameMode == GameMode.RUN) {
            if (enemies.isEmpty()) {
                if (leveliterator.hasNext()) {
                    drawImage(nextLevel);
                    gameMode = GameMode.PAUSE;
                    currentLevel = leveliterator.next();
                    notifyListeners(GameEvent.GAME_NEXT_LEVEL);
                    refreshLevel();
                } else {
                    drawImage(winScreen);
                    gameMode = GameMode.OFF;
                    sleep(2000);
                    notifyListeners(GameEvent.GAME_WIN);
                    return;
                }
            }
            if (!playerUnit.isLive()) {
                drawImage(loseScreen);
                gameMode = GameMode.OFF;
                sleep(2000);
                notifyListeners(GameEvent.GAME_LOSE);
                return;
            }
            updateAttack();
            attackEnemies();
            relocateEnemiesUnits();
            for (int i = 0; i < 10; i++) {
                relocateShells();
            }
            removeDeadUnits();
            checkBonuses();
            if (time % explosionDelay == 0) {
                updateExplosions();
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
                notifyListeners(GameEvent.ENEMY_KILL);
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
                        timerTime = maxBonusTime;
                        timer.restart();
                        currentBonus = bonus;
                    }
                    iterator.remove();
                }
            }
        }

    }

    private void attackEnemies() {
        enemies.stream().sequential().filter(Liveable::isLive).
                forEach((CombatUnit c) -> c.attack(enemiesShells, playerUnit));
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
                    playerUnit.decreaseHealth(bomb.getDamage());
                    explosions.add(new Explosion(bomb));
                    iterator.remove();
                }
            } else {
                Shell s = (Shell) dd;
                if (s.intersectsWith(playerUnit)) {
                    playerUnit.decreaseHealth(s.getDamage());
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
                    enemy.decreaseHealth(s.getDamage());
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
        final int chanceDirection = 3;
        final int restriction = Direction.values().length;
        if (RANDOM.nextInt(100) < chanceDirection) {
            Vector2D vector2D = Vector2D.create(
                    Direction.values()[RANDOM.nextInt(restriction)], unit.getSpeed());
            unit.setDirection(vector2D);
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
            g.clearRect(0, 0, currentLevel.getMap().getWidth(), currentLevel.getMap().getHeight());
            currentLevel.getMap().draw(g);
            drawBonuses(g);
            drawShells(g);
            drawUnits(g);
            drawExplosions(g);
            g.setColor(Color.RED);
            g.drawString(framesString, 10, 10);
            bufferStrategy.getDrawGraphics().drawImage(bufferedImage, 0, 0, null);
            bufferStrategy.show();
        }
    }

    private void drawBonuses(Graphics2D g) {
        bonuses.stream().forEach((bonus) -> bonus.draw(g));
        if (currentBonus != null) {
            g.setColor(Color.BLUE);
            int startX = currentLevel.getMap().getWidth() - 10 - maxBonusTime * 3;
            g.fillRect(startX, 3, timerTime * 3, 4);
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
        Global.getLogger().info("Получил команду на остановку игры");
        timer.stop();
        gameMode = GameMode.PAUSE;
        drawImage(pauseScreen);
        notifyListeners(GameEvent.GAME_PAUSE);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
    }

    private void drawText(String text) {
        Graphics2D drawGraphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        drawGraphics.scale(xScale, yScale);
        drawGraphics.clearRect(0, 0, (int) Global.getMapWidth(), (int) Global.getMapHeight());
        drawGraphics.setFont(MAIN_FONT);
        FontMetrics metrics = drawGraphics.getFontMetrics(MAIN_FONT);
        int y = 0;
        for (String line : text.split("\n")) {
            int x = (int) (Global.getMapWidth() - metrics.stringWidth(line) * xScale) / 2;
            drawGraphics.drawString(line, x, y += metrics.getHeight());
        }
        bufferStrategy.show();
    }

    private void notifyListeners(GameEvent event) {
        listeners.stream().forEach((GameListener l) -> l.actionPerformed(event));
    }

    private void resume() {
        Global.getLogger().info("Получил команду на продолжение игры");
        timer.start();
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
        drawGraphics.drawImage(bufferedImage, 0, 0, (int) Global.getMapWidth(),
                (int) Global.getMapHeight(), null);
        bufferStrategy.show();
    }

    private static int getMaxBonusTime() {
        float speed = Global.getSpeed();
        if (speed >= 50) {
            return Math.round(30 - speed / 5);
        } else {
            return Math.round(40 - 2 * speed / 5);
        }
    }

}
