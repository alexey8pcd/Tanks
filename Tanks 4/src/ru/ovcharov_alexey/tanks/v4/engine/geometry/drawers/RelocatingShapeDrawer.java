package ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers;

import ru.ovcharov_alexey.tanks.v4.engine.physics.RelocatingShape;
import java.awt.Graphics;
import java.awt.Image;
import java.io.DataOutputStream;
import java.io.IOException;

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
        Image image = images[shape.getDirection().getIndex()].getImage();
        g.drawImage(image, shape.getX(), shape.getY(),
                shape.getWidth(), shape.getHeight(), null);
    }

}
