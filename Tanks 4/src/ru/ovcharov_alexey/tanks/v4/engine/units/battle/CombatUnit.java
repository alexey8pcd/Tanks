package ru.ovcharov_alexey.tanks.v4.engine.units.battle;

import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BreakingStrength;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.DamageDealer;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.BattleUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.AttackAction;
import ru.ovcharov_alexey.tanks.v4.engine.units.actions.MoveAction;
import ru.ovcharov_alexey.tanks.v4.engine.physics.RelocatingShape;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers.RelocatingShapeDrawer;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
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
    private int armor;
    private int currentHealth;
    private final UnitType unitType;
    private final int maxHealth;
    private AttackAction attackAction;
    private RelocatingShapeDrawer drawer;
    private BreakingStrength breakingStrength;
    public static final int MIN_HEALTH = 0;
    public static final int UNIT_SIZE = 24;
    public static final int MAX_ARMOR = 80;
    public static final int MAX_DAMAGE = 100;

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
    }

    @Override
    public void attack(Collection<DamageDealer> container) {
        attackAction.attack(this, container);
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
        dos.writeInt(speed);
        dos.writeInt(maxHealth);
        dos.writeInt(armor);
        dos.writeInt(damage);
        dos.writeInt(breakingStrength.getPower());
        dos.writeUTF(moveAction.getClass().getCanonicalName());
        dos.writeUTF(attackAction.getClass().getCanonicalName());

    }

    public static CombatUnit load(DataInputStream dis) throws Exception {
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
    public void draw(Graphics g) {
        drawer.drawUnit(this, g);
        /* для тестирования
         int x = getX();
         int y = getY();
         int width = getWidth();
         int height = getHeight();
         g.setColor(Color.DARK_GRAY);
         g.fillRect(x, y, width, height);
         g.setColor(Color.BLACK);
         switch (getDirection()) {
         case LEFT:
         g.fillRect(x, y + height / 2, width / 4, height / 4);
         break;
         case RIGHT:
         g.fillRect(x + width, y + height / 2, width / 4, height / 4);
         break;
         case UP:
         g.fillRect(x + width / 2, y, width / 4, height / 4);
         break;
         case DOWN:
         g.fillRect(x + width / 2, y + height, width / 4, height / 4);
         }
         */
    }

    @Override
    public boolean isLive() {
        return currentHealth > MIN_HEALTH;
    }

    @Override
    public void setLive(boolean alive) {
        currentHealth = alive ? maxHealth : MIN_HEALTH;
    }

}
