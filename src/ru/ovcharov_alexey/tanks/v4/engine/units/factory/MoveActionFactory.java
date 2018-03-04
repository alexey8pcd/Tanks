package ru.ovcharov_alexey.tanks.v4.engine.units.factory;

import ru.ovcharov_alexey.tanks.v4.engine.units.actions.SimpleSearchMove;
import java.util.EnumSet;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.StraigthMove;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.WaveAlgorithmBaseSearchMove;

/**
 * @author Alexey
 */
public class MoveActionFactory {

    public static MoveAction createMoveAction(String className) {
        if (StraigthMove.class.getCanonicalName().equals(className)) {
            return new StraigthMove(EnumSet.of(Material.METAL, Material.BRICKS,
                    Material.WATER)
            );
        } else if (SimpleSearchMove.class.getCanonicalName().equals(className)) {
            return new SimpleSearchMove(
                    EnumSet.of(Material.METAL, Material.BRICKS, Material.WATER));
        } else if (WaveAlgorithmBaseSearchMove.class.getCanonicalName().equals(className)) {
            return new WaveAlgorithmBaseSearchMove(EnumSet.of(Material.METAL, Material.BRICKS,
                    Material.WATER));
        } else {
            throw new IllegalArgumentException("Класс с именем " + className + " не найден");
        }
    }

}
