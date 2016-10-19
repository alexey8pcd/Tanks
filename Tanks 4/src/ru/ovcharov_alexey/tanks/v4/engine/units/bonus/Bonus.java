package ru.ovcharov_alexey.tanks.v4.engine.units.bonus;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author alex
 */
public class Bonus extends GeometryShape implements Drawable {

    public static int SIZE = 16;
    private BufferedImage image;

    @Override
    public boolean isVisible() {
        return true;
    }

    private final BonusType bonusType;

    public Bonus(int x, int y, int size, BonusType bonusType) throws IOException {
        super(x, y, size);
        this.bonusType = bonusType;
        String name = "/images/bonuses/" + bonusType.name().toLowerCase() + ".png";
        image = ImageIO.read(this.getClass().getResourceAsStream(name));
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    @Override
    public void draw(Graphics g) {
//        g.setColor(bonusType.getColor());
//        g.fillOval((int) getX(), (int) getY(), getWidth(), getHeight());
        g.drawImage(image, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }

}
