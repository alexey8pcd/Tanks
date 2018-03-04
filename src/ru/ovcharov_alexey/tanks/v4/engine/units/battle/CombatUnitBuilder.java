package ru.ovcharov_alexey.tanks.v4.engine.units.battle;

import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BreakingStrength;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.AttackAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers.RelocatingShapeDrawer;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitSpeed;

public class CombatUnitBuilder {

    private UnitSpeed unitSpeed;
    private UnitType type;
    private int x;
    private int y;
    private int maxHealth;
    private int armor;
    private int damage;
    private BreakingStrength breakingStrength;
    private MoveAction moveAction;
    private AttackAction attackAction;
    private RelocatingShapeDrawer drawer;

    public CombatUnitBuilder() {
    }

    public CombatUnitBuilder setMoveSpeed(UnitSpeed unitSpeed) {
        this.unitSpeed = unitSpeed;
        return this;
    }

    public CombatUnitBuilder setType(UnitType type) {
        this.type = type;
        return this;
    }

    public CombatUnitBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public CombatUnitBuilder setX(int x) {
        this.x = x;
        return this;
    }

    public CombatUnitBuilder setY(int y) {
        this.y = y;
        return this;
    }

    public CombatUnitBuilder setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public CombatUnitBuilder setArmor(int armor) {
        this.armor = armor;
        return this;
    }

    public CombatUnitBuilder setBreakingStrength(BreakingStrength breakingStrength) {
        this.breakingStrength = breakingStrength;
        return this;
    }

    public CombatUnitBuilder setMoveAction(MoveAction moveAction) {
        this.moveAction = moveAction;
        return this;
    }

    public CombatUnitBuilder setAttackAction(AttackAction attackAction) {
        this.attackAction = attackAction;
        return this;
    }

    public CombatUnitBuilder setDrawer(RelocatingShapeDrawer drawer) {
        this.drawer = drawer;
        return this;
    }

    public CombatUnit createCombatUnit() {
        return new CombatUnit(unitSpeed, type, x, y, 
                maxHealth, armor, damage,
                breakingStrength, moveAction, 
                attackAction, drawer);
    }

}
