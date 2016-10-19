package ru.ovcharov_alexey.tanks.v4.engine;

import java.awt.Canvas;
import java.awt.Color;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameEvent;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameListener;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Visibility;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BreakingStrength;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.Liveable;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.bonus.Bonus;
import ru.ovcharov_alexey.tanks.v4.engine.units.bonus.BonusType;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.Shell;
import ru.ovcharov_alexey.tanks.v4.engine.units.factory.UnitFactory;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.InvisibleBomb;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.ShellPool;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Campaign;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Level;

/**
 * @author Alexey
 */
public class Game implements Runnable {

    private CombatUnit playerUnit;
    private List<DamageDealer> shells;
    private List<CombatUnit> enemies;
    private Random random = new Random();
    private Level currentLevel;
    private Iterator<Level> leveliterator;
    private BufferedImage bufferedImage;
    private static final int TIMER_MAX_TIME = 20;
    private Timer timer;

    private int time;
    private int width;
    private int heigth;
    private Collection<DamageDealer> enemiesShells;
    private static final Logger logger = Logger.getLogger(Game.class.getName());
    private Thread thread;
    private BufferStrategy bufferStrategy;
    private Map<Integer, Boolean> keys = new HashMap<>();
    private final List<Bonus> bonuses = new ArrayList<>();
    private final List<Explosion> explosions = new ArrayList<>();
    private double yScale;
    private double xScale;

    static {
        logger.setLevel(java.util.logging.Level.INFO);
    }
    private float delay = 1000f / (10 + Global.getSpeed());
    private int playerMoveDelay = (int) (11 - Global.getSpeed() / 10);
    private int explosionDelay = (int) (11 - Global.getSpeed() / 10);
    private boolean enemiesCanMove;
    private Bonus currentBonus;
    private int timerTime;
    private GameMode gameMode;
    private List<GameListener> listeners = new ArrayList<>();

    public Game(Canvas canvas, int width, int height) throws IOException {
        gameMode = GameMode.OFF;
        shells = new ArrayList<>();
        enemiesShells = new ArrayList<>();
        this.width = width;
        this.heigth = height;
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        timer = new Timer(1000, (ActionEvent e) -> {
            if (timerTime-- <= 0) {
                if (currentBonus != null) {
                    switch (currentBonus.getBonusType()) {
                        case POWER_UP:
                            playerUnit.setRechargeTime(playerUnit.getRechargeTime() * 2);
                            playerUnit.setBreakingStrength(BreakingStrength.BREAK_BRICKS);
                            break;
                        case STOP_ENEMIES:
                            enemiesCanMove = true;
                            break;
                        case ARMOR_UP:
                            playerUnit.setArmor(playerUnit.getArmor() / 2);
                            break;
                        case SWIM:
                            playerUnit.getMoveAction().addImpassible(Material.WATER);
                            break;
                    }
                    currentBonus = null;
                }
                timer.stop();
            }
        });
        timer.setRepeats(true);
    }

    public synchronized void start() {
        if (gameMode == GameMode.OFF) {
            logger.info("Начинаю игру");
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
            logger.info("Завершаю игру");
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
        long lastTime = System.currentTimeMillis();
        while (gameMode != GameMode.OFF) {
            if (++time == 100_000) {
                time = 0;
            }
            long now = System.currentTimeMillis();
            long elapsed = (now - lastTime);
            lastTime = now;
            delta += (elapsed / delay);
            boolean render = false;
            if (time % playerMoveDelay == 0) {
                checkKeys();
            }
            while (delta > 1 && gameMode != GameMode.OFF) {
                update();
                delta--;
                render = true;
            }
            if (render) {
                display();
            } else {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException ex) {
                }
            }
        }

    }

    public void initGame(Campaign campaign) throws IOException {
        init();
        leveliterator = campaign.getLevels().iterator();
        currentLevel = leveliterator.next();
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
                    UnitFactory.createEnemyUnit(UnitType.randomType(random)));
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
        time = 0;
        playerUnit = UnitFactory.createPlayerUnit();
        playerUnit.setLocation(currentLevel.getMap().getWidth() - CombatUnit.UNIT_SIZE - 1,
                currentLevel.getMap().getHeight() - CombatUnit.UNIT_SIZE - 1);
        bonuses.clear();
        for (int i = 0; i < currentLevel.getBonusesCount(); i++) {
            bonuses.add(new Bonus(random.nextInt(width), random.nextInt(heigth),
                    Bonus.SIZE, BonusType.randomType(random)));
        }
    }

    public void pressKey(KeyEvent e) {
        keys.put(e.getKeyCode(), true);
    }

    public void releaseKey(KeyEvent e) {
        keys.put(e.getKeyCode(), false);
    }

    private void displayWinLevel() {
        JOptionPane.showMessageDialog(null, "Вы прошли уровень!");
    }

    private void displayWinGame() {
        JOptionPane.showMessageDialog(null, "Вы победили!");
    }

    private void displayLoseGame() {
        JOptionPane.showMessageDialog(null, "Вы проиграли!");
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
            if (playerUnit.getDirection() == Direction.RIGHT) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(Direction.RIGHT);
            }
        }
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_LEFT))) {
            if (playerUnit.getDirection() == Direction.LEFT) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(Direction.LEFT);
            }
        }
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_UP))) {
            if (playerUnit.getDirection() == Direction.UP) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(Direction.UP);
            }
        }
        if (Boolean.TRUE.equals(keys.get(KeyEvent.VK_DOWN))) {
            if (playerUnit.getDirection() == Direction.DOWN) {
                playerUnit.move(currentLevel.getMap(), null);
            } else {
                playerUnit.setDirection(Direction.DOWN);
            }
        }

    }

    //<editor-fold defaultstate="collapsed" desc="update">
    private void update() {
        if (gameMode == GameMode.RUN) {
            if (enemies.isEmpty()) {
                if (leveliterator.hasNext()) {
                    displayWinLevel();
                    currentLevel = leveliterator.next();
                    notifyListeners(GameEvent.GAME_NEXT_LEVEL);
                    refreshLevel();
                } else {
                    displayWinGame();
                    gameMode = GameMode.OFF;
                    notifyListeners(GameEvent.GAME_WIN);
                    return;
                }
            }
            if (!playerUnit.isLive()) {
                displayLoseGame();
                gameMode = GameMode.OFF;
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
                if (bonus.getBonusType() == BonusType.REPAIR) {
                    if (playerUnit.getCurrentHealth() < playerUnit.getMaxHealth()) {
                        playerUnit.setCurrentHealth(playerUnit.getMaxHealth());
                        iterator.remove();
                    }
                } else if (currentBonus == null) {
                    currentBonus = bonus;
                    timerTime = TIMER_MAX_TIME;
                    timer.restart();
                    switch (bonus.getBonusType()) {
                        case POWER_UP:
                            playerUnit.setRechargeTime(playerUnit.getRechargeTime() / 2);
                            playerUnit.setBreakingStrength(BreakingStrength.BREAK_ARMOR);
                            break;
                        case STOP_ENEMIES:
                            enemiesCanMove = false;
                            break;
                        case ARMOR_UP:
                            playerUnit.setArmor(playerUnit.getArmor() * 2);
                            break;
                        case SWIM:
                            playerUnit.getMoveAction().removeImpassible(Material.WATER);
                            break;
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
            unit.setDirection(Direction.values()[random.nextInt(restriction)]);
        }
    }

    private void changeDirectionOfUnitWithChance(CombatUnit unit) {
        final int chanceDirection = 3;
        final int restriction = Direction.values().length;
        if (random.nextInt(100) < chanceDirection) {
            unit.setDirection(Direction.values()[random.nextInt(restriction)]);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="draw">
    private void display() {
        if (gameMode == GameMode.RUN) {
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            g.scale(xScale, yScale);
            g.clearRect(0, 0, width, heigth);
            currentLevel.getMap().draw(g);
            drawBonuses(g);
            drawShells(g);
            drawUnits(g);
            drawExplosions(g);
            bufferStrategy.getDrawGraphics().drawImage(bufferedImage, 0, 0, null);
            bufferStrategy.show();
        }
    }

    private void drawBonuses(Graphics2D g) {
        bonuses.stream().forEach((bonus) -> bonus.draw(g));
        if (currentBonus != null) {
            g.setColor(Color.BLUE);
            int startX = currentLevel.getMap().getWidth() - 10 - TIMER_MAX_TIME * 3;
            g.fillRect(startX, 3, timerTime * 3, 4);
        }

    }

    private void drawUnits(Graphics g) {
        playerUnit.draw(g);
        if (enemies != null && !enemies.isEmpty()) {
            enemies.stream().sequential().forEach((e) -> {
                e.draw(g);
            });
        }
    }

    private synchronized void drawShells(Graphics g) {
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
        logger.info("Получил команду на остановку игры");
        timer.stop();
        gameMode = GameMode.PAUSE;
        Graphics2D drawGraphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        drawGraphics.scale(xScale, yScale);
        drawGraphics.clearRect(0, 0, width, heigth);
        drawGraphics.drawString("Игра приостановлена. Нажмите Enter для "
                + "продолжения или Esc для выхода", width / 3, heigth / 2);
        bufferStrategy.show();
        notifyListeners(GameEvent.GAME_PAUSE);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
    }

    private void notifyListeners(GameEvent event) {
        logger.info(() -> "Оповещаю слушателей о событии " + event);
        listeners.stream().forEach((GameListener l) -> l.actionPerformed(event));
        logger.info("Оповестил слушателей");
    }

    private void resume() {
        logger.info("Получил команду на продолжение игры");
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

}
