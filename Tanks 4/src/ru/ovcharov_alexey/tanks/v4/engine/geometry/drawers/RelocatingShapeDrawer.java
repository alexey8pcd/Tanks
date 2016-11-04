package ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers;

import ru.ovcharov_alexey.tanks.v4.engine.physics.RelocatingShape;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author alex
 */
public class RelocatingShapeDrawer {

    public RelocatingShapeDrawer(NamedImage[] images) {
        this.images = images;
    }

    private final NamedImage[] images;

    public NamedImage[] getImages() {
        return images;
    }

    public void drawUnit(RelocatingShape shape, Graphics g) {
        BufferedImage image = images[shape.getDirection().getIndex()].getImage();
        g.drawImage(image, Math.round(shape.getX() - 2), Math.round(shape.getY() - 2),
                shape.getWidth() + 4, shape.getHeight() + 4, null);
    }

}
