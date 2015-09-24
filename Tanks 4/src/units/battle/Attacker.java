package units.battle;

import java.util.List;

/**
 *
 * @author alex
 */
public interface Attacker extends DDamage{

    public void attack(List<Shell> missiles);

}
