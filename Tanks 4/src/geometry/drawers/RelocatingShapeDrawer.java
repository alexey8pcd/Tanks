package geometry.drawers;

import geometry.RelocatingShape;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author alex
 */
public class RelocatingShapeDrawer {

    public RelocatingShapeDrawer(Image[] images) {
        this.images = images;
    }

    private final Image[] images;

    public void drawUnit(RelocatingShape shape, Graphics g) {
        Image image = images[shape.getDirection().getIndex()];
        g.drawImage(image, shape.getX(), shape.getY(),
                shape.getWidth(), shape.getHeight(), null);
    }

}
