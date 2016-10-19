package ru.ovcharov_alexey.tanks.v4.engine.units.factory;

import ru.ovcharov_alexey.tanks.v4.engine.units.actions.SimpleSearchMove;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.StraigthMove;

/**
 * @author Alexey
 */
public class MoveActionFactory {

    public static final MoveAction UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING
            = new StraigthMove(
                    EnumSet.of(Material.ARMOR,
                            Material.BRICK,
                            Material.WATER)
            );
    public static final MoveAction UNIT_SIMPLE_SEARCH_MOVE = new SimpleSearchMove(
            EnumSet.of(Material.ARMOR, Material.BRICK, Material.WATER));

    public static MoveAction createMoveAction(String className) {
        if (StraigthMove.class.getCanonicalName().equals(className)) {
            return UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING;
        } else {
            throw new IllegalArgumentException("Класс с именем " + className + " не найден");
        }
    }

}
