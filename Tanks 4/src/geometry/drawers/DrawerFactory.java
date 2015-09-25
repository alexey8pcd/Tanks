package geometry.drawers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import units.battle.UnitType;

/**
 *
 * @author alex
 */
public class DrawerFactory {

    private static RelocatingShapeDrawer playerUnitDrawer;

    public static RelocatingShapeDrawer playerUnitDrawer() throws IOException {
        if (playerUnitDrawer == null) {
            playerUnitDrawer = new RelocatingShapeDrawer(new BufferedImage[]{
                ImageIO.read(new File("images/player_left.png")),
                ImageIO.read(new File("images/player_top.png")),
                ImageIO.read(new File("images/player_right.png")),
                ImageIO.read(new File("images/player_down.png"))
            });
        }
        return playerUnitDrawer;
    }

    public static RelocatingShapeDrawer getDrawer(UnitType unitType) {
        return null;
    }

}
