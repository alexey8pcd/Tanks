package ru.ovcharov_alexey.tanks.v4.engine.units.bonus;

import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;

/**
 * @author Alexey
 */
public class GameContext {

    private CombatUnit playerUnit;
    private boolean ememiesCanMove;
    private boolean durable;
    private Bonus currentBonus;

    public Bonus getCurrentBonus() {
        return currentBonus;
    }

    public void setCurrentBonus(Bonus currentBonus) {
        this.currentBonus = currentBonus;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurableBonus(boolean durable) {
        this.durable = durable;
    }

    public GameContext(CombatUnit playerUnit) {
        this.playerUnit = playerUnit;
    }

    public CombatUnit getPlayerUnit() {
        return playerUnit;
    }

    public void setPlayerUnit(CombatUnit playerUnit) {
        this.playerUnit = playerUnit;
    }

    public boolean isEmemiesCanMove() {
        return ememiesCanMove;
    }

    public void setEmemiesCanMove(boolean ememiesCanMove) {
        this.ememiesCanMove = ememiesCanMove;
    }

    boolean bonusNotActive() {
        return currentBonus == null;
    }

}
