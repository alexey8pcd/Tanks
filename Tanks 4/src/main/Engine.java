package main;

import actions.MoveAction;
import actions.StraigthMoveWithoutBreaking;
import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import geometry.Movable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import units.battle.CombatUnit;
import units.battle.Shell;

/**
 * @author Alexey
 */
public class Engine extends Surface {

    private GeometryMap geometryMap;
    private Point mouseCursorPosition;
    private Dimension mouseCursorSize;
    private Color mouseCursorColor;
    private CombatUnit playerUnit;
    private List<Shell> shells;
    private List<CombatUnit> enemies;
    private final Random random = new Random();
    private MoveAction unitMoveAction = new StraigthMoveWithoutBreaking(
            EnumSet.of(Material.ARMOR, Material.BRICK, Material.WATER));
    private int time;
    private int moveDelay = 64;

    public Engine(int width, int height, int framesPerSecond, GeometryMap geometryMap) {
        super(width, height, framesPerSecond);
        this.geometryMap = geometryMap;
        time = 0;
        mouseCursorPosition = new Point(-1, -1);
        mouseCursorSize = new Dimension(0, 0);
        mouseCursorColor = Color.BLACK;
        playerUnit = new CombatUnit(CombatUnit.UnitSpeed.NORMAL.getValue(), 0, 0, 32,
                Movable.Direction.LEFT, unitMoveAction, 100);
        enemies = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            int v = random.nextInt(CombatUnit.UnitSpeed.values().length);
            enemies.add(new CombatUnit(CombatUnit.UnitSpeed.values()[v].getValue(),
                    0, 0, 32, Movable.Direction.LEFT, unitMoveAction, 100));
        }
        shells = new ArrayList<>();
    }

    public GeometryMap getMap() {
        return geometryMap;
    }

    @Override
    public void checkKeys() {
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            playerUnit.setDirection(Movable.Direction.RIGHT);
            playerUnit.move(geometryMap);
        }
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            playerUnit.setDirection(Movable.Direction.LEFT);
            playerUnit.move(geometryMap);
        }
        if (isKeyPressed(KeyEvent.VK_UP)) {
            playerUnit.setDirection(Movable.Direction.UP);
            playerUnit.move(geometryMap);
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            playerUnit.setDirection(Movable.Direction.DOWN);
            playerUnit.move(geometryMap);
        }
        if (isKeyPressed(KeyEvent.VK_SPACE)) {
            playerUnit.attack(shells);
        }

    }

    @Override
    public void update() {
        if (time > 1024) {
            time = 0;
        }
        //перемещение снарядов
        for (int i = 0, n = shells.size(); i < n;) {
            Shell s = shells.get(i);
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
                s.draw(g);
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
