package units.battle;

import actions.AttackAction;
import java.util.Collection;

/**
 *
 * @author alex
 */
public interface Attacker extends DamageDealer {

    public void setAttackAction(AttackAction action);

    public AttackAction getAttackAction();

    public void attack(Collection<DamageDealer> container);
    
    public void setDamage(int damage);

}
