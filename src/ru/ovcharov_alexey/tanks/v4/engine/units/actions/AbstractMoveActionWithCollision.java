package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Scene;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Shape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;

/**
 * Используется для проверки перемещения по карте. Перемещение невозможно, если
 * встречается препятствие.
 *
 * @author alex
 */
public abstract class AbstractMoveActionWithCollision implements MoveAction {

    protected float dLeftX;
    protected float dTopY;
    protected boolean slowMove;
    protected final EnumSet<Material> impassable;

    public AbstractMoveActionWithCollision(EnumSet<Material> impassable) {
        this.impassable = impassable;
    }

    @Override
    public void addImpassible(Material material) {
        impassable.add(material);
    }

    @Override
    public void removeImpassible(Material material) {
        impassable.remove(material);
    }

    @Override
    public abstract boolean move(Movable movable, GeometryPoint target, Scene scene);

    @Override
    public boolean canMove(Movable movable, GeometryPoint target, Scene scene) {
        calculateDesirePosition(movable);
        Shape shape = new GeometryShape(dLeftX, dTopY, movable.getWidth(), movable.getHeight());
        if (!scene.into(shape)) {
            return false;
        }
        boolean collisionDetected = scene.isCollisionDetected(shape, impassable);
        if (!collisionDetected) {
            collisionDetected = scene.isUnitsCollisionDetected(shape, movable);
            if (!collisionDetected) {
                slowMove = scene.isSlowMove(shape, Material.ICE);
            }
        }
        return !collisionDetected;
    }

    /**
     * Вычислить желаемую позицию. Результат сохраняется в полях dLeftX и dTopY
     *
     * @param movable
     */
    protected void calculateDesirePosition(Movable movable) {
        dLeftX = movable.getX() + movable.getDirection().getI() * movable.getSpeed();
        dTopY = movable.getY() - movable.getDirection().getJ() * movable.getSpeed();
    }
}
