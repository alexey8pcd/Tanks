package units.battle;

import java.util.List;

/**
 *
 * @author alex
 */
public interface Attacker {

    public void attack(List<Shell> missiles);

    public int getDamage();
}
