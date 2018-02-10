package ru.ovcharov_alexey.tanks.v4.engine.units.battle;

import java.awt.Color;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BreakingStrength;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BattleUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.AttackAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.physics.RelocatingShape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers.RelocatingShapeDrawer;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers.DrawerFactory;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitSpeed;
import ru.ovcharov_alexey.tanks.v4.engine.units.factory.AttackActionFactory;
import ru.ovcharov_alexey.tanks.v4.engine.units.factory.MoveActionFactory;

/**
 *
 * @author alex
 */
public class CombatUnit extends RelocatingShape implements BattleUnit {

    private int damage;
    private int armor;//броня в процентах
    private int currentHealth;
    private final UnitType unitType;
    private int maxHealth;
    private AttackAction attackAction;
    private RelocatingShapeDrawer drawer;
    private BreakingStrength breakingStrength;
    private boolean canAttack = true;

    private static final int UNIT_SIZE = 22;
    public static final int MAX_ARMOR = 80;
    public static final int MAX_DAMAGE = 100;
    private static final int DEFAULT_RECHARGE_TIME = 50;
    private static final int NO_DAMAGE_LIMIT = 30;
    private int rechargeTime = DEFAULT_RECHARGE_TIME;
    private int rechargeProgress = rechargeTime;
    private boolean damaged;
    private Vector2D directionOfFire;
    private int fireDetect;
    private int criticalDamageChance = Global.BASE_CHANCE_TO_CRITICAL_DAMAGE;

    public Vector2D getDirectionOfFire() {
        return directionOfFire;
    }

    public void setCriticalDamageChance(int criticalDamageChance) {
        this.criticalDamageChance = Math.min(75, Math.max(criticalDamageChance, 
                Global.BASE_CHANCE_TO_CRITICAL_DAMAGE));
    }

    public int getCriticalDamageChance() {
        return criticalDamageChance;
    }

    public CombatUnit(UnitSpeed unitSpeed, UnitType type, int maxHealth,
            int armor, int damage,
            BreakingStrength breakingStrength,
            MoveAction moveAction,
            AttackAction attackAction,
            RelocatingShapeDrawer drawer) {
        super(unitSpeed.getValue(), moveAction, UNIT_SIZE);
        if (breakingStrength == null
                || moveAction == null
                || attackAction == null
                || drawer == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }
        this.unitType = type;
        this.damage = damage;
        this.maxHealth = maxHealth;
        this.breakingStrength = breakingStrength;
        this.attackAction = attackAction;
        this.drawer = drawer;
        setArmor(armor);
        currentHealth = maxHealth;
    }

    public CombatUnit(UnitSpeed unitSpeed, UnitType unitType, int x, int y,
            int maxHealth, int armor, int damage, BreakingStrength breakingStrength,
            MoveAction moveAction, AttackAction attackAction,
            RelocatingShapeDrawer drawer) {
        super(unitSpeed.getValue(), x, y, UNIT_SIZE, Direction.RIGHT, moveAction);
        if (breakingStrength == null
                || moveAction == null
                || attackAction == null
                || drawer == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }
        this.unitType = unitType;
        this.damage = damage;
        this.moveAction = moveAction;
        this.drawer = drawer;
        this.attackAction = attackAction;
        this.breakingStrength = breakingStrength;
        this.maxHealth = maxHealth;
        setArmor(armor);
        currentHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public void attack(Collection<DamageDealer> container, CombatUnit attackable) {
        if (canAttack) {
            attackAction.attack(this, container, attackable);
            canAttack = false;
            rechargeProgress = 0;
        }
    }

    public void setCanAttack() {
        canAttack = true;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void save(DataOutputStream dos) throws IOException {
        dos.writeInt(unitType.getType());
        dos.writeFloat(speed);
        dos.writeInt(maxHealth);
        dos.writeInt(armor);
        dos.writeInt(damage);
        dos.writeInt(breakingStrength.getPower());
        dos.writeUTF(moveAction.getClass().getCanonicalName());
        dos.writeUTF(attackAction.getClass().getCanonicalName());

    }

    public static CombatUnit load(DataInputStream dis) throws IOException {
        UnitType type = UnitType.typeOf(dis.readInt());
        UnitSpeed speed = UnitSpeed.typeOf(dis.readInt());
        int health = dis.readInt();
        int armor = dis.readInt();
        int damage = dis.readInt();
        BreakingStrength breakingStrength = BreakingStrength.typeOf(dis.readInt());
        RelocatingShapeDrawer drawer = DrawerFactory.getDrawer(type);

        MoveAction moveAction = MoveActionFactory.createMoveAction(dis.readUTF());
        AttackAction attackAction
                = AttackActionFactory.createAttackAction(dis.readUTF());
        return new CombatUnit(speed, type, health, armor, damage,
                breakingStrength, moveAction, attackAction, drawer);
    }

    public RelocatingShapeDrawer getDrawer() {
        return drawer;
    }

    public int getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(int rechargeTime) {
        this.rechargeTime = rechargeTime;
        if (rechargeProgress > rechargeTime) {
            rechargeProgress = rechargeTime;
        }
    }

    public void setDrawer(RelocatingShapeDrawer drawer) {
        this.drawer = drawer;
    }

    public int getArmor() {
        return armor;
    }

    public final void setArmor(int armor) {
        armor = armor < 0 ? 0 : armor;
        armor = armor > MAX_ARMOR ? MAX_ARMOR : armor;
        this.armor = armor;
    }

    @Override
    public AttackAction getAttackAction() {
        return attackAction;
    }

    @Override
    public void setAttackAction(AttackAction attackAction) {
        this.attackAction = attackAction;
    }

    public void setBreakingStrength(BreakingStrength breakingStrength) {
        this.breakingStrength = breakingStrength;
    }

    public BreakingStrength getBreakingStrength() {
        return breakingStrength;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    @Override
    public void draw(Graphics2D g) {
        drawer.drawUnit(this, g);
        if (attackAction.isRepeated()) {
            drawRecharge(g);
        }
        drawHealth(g);
    }

    private void drawRecharge(Graphics2D g) {
        g.setColor(Color.ORANGE);
        double dv = (double) getWidth() / rechargeTime;
        if (getY() > 1) {
            g.fillRect((int) getX(), (int) getY() - 2,
                    (int) (rechargeProgress * dv), 2);
        } else {
            g.fillRect((int) getX(), (int) getY() + getHeight() + 1,
                    (int) (rechargeProgress * dv), 2);
        }
    }

    private void drawHealth(Graphics2D g) {
        double dh = (double) getWidth() / maxHealth;
        g.setColor(Color.GREEN);
        if (getY() > 3) {
            g.fillRect((int) getX(), (int) getY() - 4,
                    (int) (currentHealth * dh), 2);
        } else {
            g.fillRect((int) getX(), (int) getY() + getHeight() + 3,
                    (int) (currentHealth * dh), 2);
        }
    }

    @Override
    public boolean isLive() {
        return currentHealth > NOT_HEALTH;
    }

    public void recharge() {
        if (!canAttack) {
            if (rechargeProgress >= rechargeTime) {
                canAttack = true;
            } else {
                ++rechargeProgress;
            }
        }
    }

    @Override
    public boolean isFixedPosition() {
        return false;
    }

    public void decreaseHealth(int value) {
        setHealth(currentHealth - calculateRealDamage(value));
    }

    public final int calculateRealDamage(int damage) {
        return Math.max(1, damage - damage * armor / 100);
    }

    @Override
    public boolean isHealthy() {
        return currentHealth == maxHealth;
    }

    @Override
    public void kill() {
        this.currentHealth = NOT_HEALTH;
    }

    @Override
    public void restore() {
        setHealth(maxHealth);
    }

    @Override
    public int getHealth() {
        return currentHealth;
    }

    @Override
    public void setHealth(int currentHealth) {
        if (currentHealth < 1) {
            currentHealth = NOT_HEALTH;
        } else if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        this.currentHealth = currentHealth;
        updateDamaged();
    }

    private void updateDamaged() {
        if (currentHealth < NO_DAMAGE_LIMIT && !damaged) {
            damaged = true;
            speed /= 2;
        } else if (damaged && currentHealth >= NO_DAMAGE_LIMIT) {
            damaged = false;
            speed *= 2;
        }
    }

    public void setDirectionOfFire(Vector2D direction) {
        this.directionOfFire = direction;
        fireDetect = 200;
    }

    public void decreaseFireDetectTime() {
        fireDetect = Math.max(0, fireDetect - 1);
        if (fireDetect == 0) {
            directionOfFire = null;
        }
    }

}
