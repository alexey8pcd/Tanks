package ru.ovcharov_alexey.tanks.v4.engine.units.bonus;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;
import javax.imageio.ImageIO;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;

/**
 *
 * @author alex
 */
public class Bonus extends GeometryShape implements Drawable {

    public static int SIZE = 16;

    private static final EnumMap<BonusType, Image> IMAGES
            = new EnumMap<>(BonusType.class);

    public static void init() throws IOException {
        for (BonusType bonusType : BonusType.values()) {
            String name = "/images/bonuses/" + bonusType.name().toLowerCase() + ".png";
            IMAGES.put(bonusType, ImageIO.read(Bonus.class.getResourceAsStream(name)));
        }
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    private final BonusType bonusType;

    public Bonus(int x, int y, int size, BonusType bonusType) {
        super(x, y, size);
        this.bonusType = bonusType;
    }
    
    public GameContext applyTo(CombatUnit combatUnit, boolean enemiesCanMove){
        GameContext gameContext = new GameContext(combatUnit);
        gameContext.setEmemiesCanMove(enemiesCanMove);
        return bonusType.applyTo(gameContext);
    }
    
    public GameContext resetTo(CombatUnit combatUnit, boolean enemiesCanMove){
        GameContext gameContext = new GameContext(combatUnit);
        gameContext.setEmemiesCanMove(enemiesCanMove);
        return bonusType.resetTo(gameContext);
    }
    
    public BonusType getBonusType() {
        return bonusType;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(IMAGES.get(bonusType), (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

    public boolean isAllowed(CombatUnit playerUnit, boolean enemiesCanMove, Bonus currentBonus) {
        GameContext gameContext = new GameContext(playerUnit);
        gameContext.setEmemiesCanMove(enemiesCanMove);
        gameContext.setCurrentBonus(currentBonus);
        return bonusType.isAllowed(gameContext);
    }

}
