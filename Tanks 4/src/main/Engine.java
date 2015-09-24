package main;

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

    public Engine(int width, int height, int framesPerSecond, GeometryMap geometryMap) {
        super(width, height, framesPerSecond);
        this.geometryMap = geometryMap;
        mouseCursorPosition = new Point(-1, -1);
        mouseCursorSize = new Dimension(0, 0);
        mouseCursorColor = Color.BLACK;
        playerUnit = new CombatUnit(1, 0, 0, 32, Movable.Direction.LEFT,
                new StraigthMoveWithoutBreaking(
                        EnumSet.of(Material.ARMOR,
                                Material.BRICK,
                                Material.WATER)
                ), 100);
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
        for (int i = 0, n = shells.size(); i < n;) {
            Shell s = shells.get(i);
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
        playerUnit.draw(g);
        g.setColor(mouseCursorColor);
        g.drawRect(mouseCursorPosition.x, mouseCursorPosition.y,
                mouseCursorSize.width, mouseCursorSize.height);
        if (shells != null) {
            for (Shell s : shells) {
                s.draw(g);
            }
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
