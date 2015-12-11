package units.battle.factory;

import static actions.AttackAction.*;
import static actions.MoveAction.*;
import geometry.drawers.DrawerFactory;
import java.io.IOException;
import static units.battle.BreakingStrength.*;
import units.battle.CombatUnit;
import static units.UnitSpeed.*;
import units.battle.CombatUnitBuilder;
import units.battle.UnitType;

/**
 *
 * @author alex
 */
public class UnitFactory {

    public static CombatUnit createPlayerUnit() throws IOException{
        return new CombatUnitBuilder().setArmor(40).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(SLOW).
                        setDrawer(DrawerFactory.getPlayerUnitDrawer()).
                        setMaxHealth(100).setType(UnitType.TANK).createCombatUnit();
    }
    
    public static CombatUnit createEnemyUnit(UnitType unitType) {
        switch (unitType) {
            case LIGHT_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(0).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_WOOD).
                        setMoveSpeed(VERY_FAST).
                        setDamage(50).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case MIDDLE_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(20).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_BRICKS).setMoveSpeed(FAST).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case HEAVY_COMBAT_VEHICLE:
                return new CombatUnitBuilder().setArmor(40).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(SLOW).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();
            case TANK:
                return new CombatUnitBuilder().setArmor(60).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(NORMAL).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(200).setType(unitType).createCombatUnit();
             case FOCUSED_BLASTING:
                 return new CombatUnitBuilder().setArmor(80).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(NORMAL).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(100).setType(unitType).createCombatUnit();                
            case DOUBLE_WEAPON_VEHICLE:
                return new CombatUnitBuilder().setArmor(30).
                        setMoveAction(UNIT_STRAIGHT_MOVE_WITHOUT_BREAKING).
                        setAttackAction(UNIT_ATTACK_ACTION_WITH_SHELLS).
                        setBreakingStrength(BREAK_ARMOR).setMoveSpeed(NORMAL).
                        setDrawer(DrawerFactory.getDrawer(unitType)).
                        setMaxHealth(140).setType(unitType).createCombatUnit();
            default:
                throw new AssertionError(unitType.name());
        }
    }

    public static CombatUnit[] createSeveral(UnitType unitType, int amount) {
        return null;
    }

}
