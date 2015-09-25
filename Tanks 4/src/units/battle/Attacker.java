package units.battle;

import actions.AttackAction;
import java.util.Collection;

/**
 *
 * @author alex
 */
public interface Attacker extends DDamage {

    public void setAttackAction(AttackAction action);

    public AttackAction getAttackAction();

    public void attack(Collection<DDamage> container);
    
    public void setDamage(int damage);

}
