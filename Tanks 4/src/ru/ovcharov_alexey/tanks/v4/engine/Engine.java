package ru.ovcharov_alexey.tanks.v4.engine;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.shell.Shell;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.factory.UnitFactory;

/**
 * @author Alexey
 */
public class Engine extends Surface {

    private final GeometryMap geometryMap;
    private final Point mouseCursorPosition;
    private final Dimension mouseCursorSize;
    private Color mouseCursorColor;
    private final CombatUnit playerUnit;
    private final List<DamageDealer> shells;
    private final List<CombatUnit> enemies;
    private final Random random = new Random();



    private int time;
    private final int enemyUnitMoveDelayInMillis = 64;

    public Engine(int width, int height, int framesPerSecond, GeometryMap geometryMap) throws IOException {
        super(width, height, framesPerSecond);
        this.geometryMap = geometryMap;
        time = 0;
        mouseCursorPosition = new Point(-1, -1);
        mouseCursorSize = new Dimension(0, 0);
        mouseCursorColor = Color.BLACK;
        playerUnit = UnitFactory.createPlayerUnit();
        enemies = new ArrayList<>();
        int unitTypeCount = UnitType.values().length;
        for (int i = 0; i < 30; ++i) {
            int typeNumber = random.nextInt(unitTypeCount);
            enemies.add(UnitFactory.createEnemyUnit(UnitType.values()[typeNumber]));
        }
        shells = new ArrayList<>();
    }

    public GeometryMap getMap() {
        return geometryMap;
    }

    @Override
    public void checkKeys() {
        if (isKeyPressed(KeyEvent.VK_SPACE)) {
            playerUnit.attack(shells);
        }
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            playerUnit.setDirection(Direction.RIGHT);
            playerUnit.move(geometryMap);
        } else if (isKeyPressed(KeyEvent.VK_LEFT)) {
            playerUnit.setDirection(Direction.LEFT);
            playerUnit.move(geometryMap);
        } else if (isKeyPressed(KeyEvent.VK_UP)) {
            playerUnit.setDirection(Direction.UP);
            playerUnit.move(geometryMap);
        } else if (isKeyPressed(KeyEvent.VK_DOWN)) {
            playerUnit.setDirection(Direction.DOWN);
            playerUnit.move(geometryMap);
        }

    }

    @Override
    public void update() {
        int maxTime = 1024;
        if (time > maxTime) {
            time = 0;
        }
        relocateShells();
        relocateUnits();
        ++time;

    }

    private void relocateUnits() {
        if (time % enemyUnitMoveDelayInMillis == 0) {
            for (CombatUnit unit : enemies) {
                changeDirectionOfUnitIfCanNotMove(unit);
                changeDirectionOfUnitWithChance(unit);
            }
        }
    }

    private void changeDirectionOfUnitIfCanNotMove(CombatUnit unit) {
        final int restriction = Direction.values().length;
        if (!unit.move(geometryMap)) {
            unit.setDirection(Direction.values()[random.nextInt(restriction)]);
        }
    }

    private void changeDirectionOfUnitWithChance(CombatUnit unit) {
        final int chanceDirection = 5;
        final int restriction = Direction.values().length;
        if (random.nextInt(100) < chanceDirection) {
            unit.setDirection(Direction.values()[random.nextInt(restriction)]);
        }
    }

    private void relocateShells() {
        for (int i = 0, n = shells.size(); i < n;) {
            Shell s = (Shell) shells.get(i);
            if (!s.move(geometryMap)) {
                shells.remove(s);
                --n;
            } else {
                ++i;
            }
        }
    }

    @Override
    public void display(Graphics g) {
        geometryMap.draw(g);
//        g.setColor(mouseCursorColor);
//        g.drawRect(mouseCursorPosition.x, mouseCursorPosition.y,
//                mouseCursorSize.width, mouseCursorSize.height);
        drawShells(g);
        drawUnits(g);

    }

    private void drawUnits(Graphics g) {
        playerUnit.draw(g);
        if (enemies != null && !enemies.isEmpty()) {
            enemies.parallelStream().forEach((e) -> {
                e.draw(g);
            });
        }
    }

    private void drawShells(Graphics g) {
        if (shells != null) {
            shells.parallelStream().forEach((s) -> {
                if (s.isVisible()) {
                    ((Drawable) s).draw(g);
                }

            });
        }
    }

    public void setMouseCursor(int x, int y, int blockSize, Color color) {
        mouseCursorPosition.x = x;
        mouseCursorPosition.y = y;
        mouseCursorSize.width = blockSize;
        mouseCursorSize.height = blockSize;
        mouseCursorColor = color;
    }

}
