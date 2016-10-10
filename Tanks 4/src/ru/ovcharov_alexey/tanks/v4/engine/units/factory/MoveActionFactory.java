package ru.ovcharov_alexey.tanks.v4.engine.units.factory;

import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.StraigthMoveWithoutBreaking;

/**
 * @author Alexey
 */
public class MoveActionFactory {

    public static final MoveAction UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING
            = new StraigthMoveWithoutBreaking(
                    EnumSet.of(Material.ARMOR,
                            Material.BRICK,
                            Material.WATER)
            );

    public static MoveAction createMoveAction(String className) {
        if (StraigthMoveWithoutBreaking.class.getCanonicalName().equals(className)) {
            return UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING;
        } else {
            throw new IllegalArgumentException("Класс с именем " + className + " не найден");
        }
    }

}
