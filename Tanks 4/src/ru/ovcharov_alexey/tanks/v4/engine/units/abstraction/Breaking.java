package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

/**
 *
 * @author alex
 */
public interface Breaking extends Liveable {

    public BreakingStrength getBreakingStrength();

    public void setBreakingStrength(BreakingStrength breakingStrength);

}
