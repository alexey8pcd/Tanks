package ru.ovcharov_alexey.tanks.v4.engine.units.bonus;

import java.util.Random;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers.DrawerFactory;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BreakingStrength;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;

/**
 * @author Alexey
 */
public enum BonusType {

    /**
     * увеличение скорости стрельбы и пробивание брони
     **/
    POWER_UP(1, 18) {

        public static final int RECHARGE_TIME_FACTOR = 4;

        @Override
        public GameContext applyTo(GameContext gameContext) {
            CombatUnit playerUnit = gameContext.getPlayerUnit();
            playerUnit.setRechargeTime(playerUnit.getRechargeTime() / RECHARGE_TIME_FACTOR);
            playerUnit.setBreakingStrength(BreakingStrength.BREAK_ARMOR);
            gameContext.setDurableBonus(true);
            return gameContext;
        }

        @Override
        public GameContext resetTo(GameContext gameContext) {
            CombatUnit playerUnit = gameContext.getPlayerUnit();
            playerUnit.setRechargeTime(playerUnit.getRechargeTime() * RECHARGE_TIME_FACTOR);
            playerUnit.setBreakingStrength(BreakingStrength.BREAK_BRICKS);
            return gameContext;
        }

        @Override
        public boolean isAllowed(GameContext gameContext) {
            return gameContext.bonusNotActive();
        }

    },
    /**
     * остановка вражеских юнитов
     **/
    STOP_ENEMIES(2, 24) {

        @Override
        public GameContext applyTo(GameContext gameContext) {
            gameContext.setEmemiesCanMove(false);
            gameContext.setDurableBonus(true);
            return gameContext;
        }

        @Override
        public GameContext resetTo(GameContext gameContext) {
            gameContext.setEmemiesCanMove(true);
            return gameContext;
        }

        @Override
        public boolean isAllowed(GameContext gameContext) {
            return gameContext.bonusNotActive();
        }

    },
    /**
     * Временное увеличение брони
     */
    ARMOR_UP(3, 22) {

        private static final int ARMOR_FACTOR = 2;

        @Override
        public GameContext applyTo(GameContext gameContext) {
            CombatUnit playerUnit = gameContext.getPlayerUnit();
            playerUnit.setArmor(playerUnit.getArmor() * ARMOR_FACTOR);
            playerUnit.setDrawer(DrawerFactory.getPlayerUnitArmoredDrawer());
            gameContext.setDurableBonus(true);
            return gameContext;
        }

        @Override
        public GameContext resetTo(GameContext gameContext) {
            CombatUnit playerUnit = gameContext.getPlayerUnit();
            playerUnit.setArmor(playerUnit.getArmor() / ARMOR_FACTOR);
            playerUnit.setDrawer(DrawerFactory.getPlayerUnitDrawer());
            return gameContext;
        }

        @Override
        public boolean isAllowed(GameContext gameContext) {
            return gameContext.bonusNotActive();
        }

    },
    /**
     * Восстановление здоровья
     */
    REPAIR(4, 20) {

        @Override
        public GameContext applyTo(GameContext gameContext) {
            gameContext.getPlayerUnit().restore();
            gameContext.setDurableBonus(false);
            return gameContext;
        }

        @Override
        public GameContext resetTo(GameContext gameContext) {
            return gameContext;
        }

        @Override
        public boolean isAllowed(GameContext gameContext) {
            return gameContext.getPlayerUnit().isDamaged();
        }

    },
    SWIM(5, 16) {
        
        @Override
        public GameContext applyTo(GameContext gameContext) {
            gameContext.getPlayerUnit().getMoveAction().removeImpassible(Material.WATER);
            gameContext.setDurableBonus(true);
            return gameContext;
        }

        @Override
        public GameContext resetTo(GameContext gameContext) {
            gameContext.getPlayerUnit().getMoveAction().addImpassible(Material.WATER);
            return gameContext;
        }

        @Override
        public boolean isAllowed(GameContext gameContext) {
            return gameContext.bonusNotActive();
        }

    };

    //усиление брони
    private final int value;
    private final int chance;

    private BonusType(int value, int chance) {
        this.value = value;
        this.chance = chance;
    }

    public int getValue() {
        return value;
    }

    public int getChance() {
        return chance;
    }

    public static BonusType randomType(Random random) {
        int next = random.nextInt(100);
        int i = 0;
        int limit = values()[i++].chance;
        for (; i < values().length; i++) {
            if (next < limit) {
                return values()[i - 1];
            } else {
                limit += values()[i].chance;
            }
        }
        return values()[values().length - 1];
    }

    public abstract GameContext applyTo(GameContext gameContext);

    public abstract GameContext resetTo(GameContext gameContext);
    
    public abstract boolean isAllowed(GameContext gameContext);

}
