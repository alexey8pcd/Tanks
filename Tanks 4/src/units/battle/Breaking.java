package units.battle;

import units.LiveAndDeath;

/**
 *
 * @author alex
 */
public interface Breaking extends LiveAndDeath {

    public BreakingStrength getBreakingStrength();

    public void setBreakingStrength(BreakingStrength breakingStrength);

}
