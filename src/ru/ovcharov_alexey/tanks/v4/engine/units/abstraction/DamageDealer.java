package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Visibility;

/**
 *
 * @author alex
 */
public interface DamageDealer extends Visibility{

    int getDamage();

    boolean isFixedPosition();
    
}
