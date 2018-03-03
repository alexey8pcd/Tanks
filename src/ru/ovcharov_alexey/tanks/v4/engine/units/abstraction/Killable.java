package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

/**
 * @author Alexey
 */
public interface Killable {

    default boolean isDead() {
        return !isLive();
    }

    boolean isLive();

    void kill();

    void restore();

}
