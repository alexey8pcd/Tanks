package main;

import actions.StraigthMoveWithoutBreaking;
import geometry.Drawable;
import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import geometry.Movable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import units.UnitSpeed;
import units.battle.CombatUnit;
import units.battle.DamageDealer;
import units.battle.Shell;
import units.battle.UnitType;
import units.battle.factory.UnitFactory;

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
    private final int moveDelay = 64;

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
            playerUnit.setDirection(Movable.Direction.RIGHT);
            playerUnit.move(geometryMap);
        } else if (isKeyPressed(KeyEvent.VK_LEFT)) {
            playerUnit.setDirection(Movable.Direction.LEFT);
            playerUnit.move(geometryMap);
        } else if (isKeyPressed(KeyEvent.VK_UP)) {
            playerUnit.setDirection(Movable.Direction.UP);
            playerUnit.move(geometryMap);
        } else if (isKeyPressed(KeyEvent.VK_DOWN)) {
            playerUnit.setDirection(Movable.Direction.DOWN);
            playerUnit.move(geometryMap);
        }

    }

    @Override
    public void update() {
        if (time > 1024) {
            time = 0;
        }
        //перемещение снарядов
        for (int i = 0, n = shells.size(); i < n;) {
            Shell s = (Shell) shells.get(i);
            if (!s.move(geometryMap)) {
                shells.remove(s);
                --n;
            } else {
                ++i;
            }
        }

        if (time % moveDelay == 0) {
            //перемещение боевых единиц
            for (CombatUnit unit : enemies) {
                if (!unit.move(geometryMap)) {
                    unit.setDirection(Movable.Direction.values()[random.nextInt(4)]);
                }
                if (random.nextInt(100) < 5) {
                    unit.setDirection(Movable.Direction.values()[random.nextInt(4)]);
                }
            }
        }
        ++time;

    }

    @Override
    public void display(Graphics g) {
        geometryMap.draw(g);
        playerUnit.draw(g);
        g.setColor(mouseCursorColor);
        g.drawRect(mouseCursorPosition.x, mouseCursorPosition.y,
                mouseCursorSize.width, mouseCursorSize.height);
        if (shells != null) {
            shells.parallelStream().forEach((s) -> {
                if (s.isVisible()) {
                    ((Drawable) s).draw(g);
                }

            });
        }
        if (enemies != null && !enemies.isEmpty()) {
            enemies.parallelStream().forEach((e) -> {
                e.draw(g);
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
