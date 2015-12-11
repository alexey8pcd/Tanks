package geometry.drawers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import units.battle.UnitType;

/**
 *
 * @author alex
 */
public class DrawerFactory {

    private static RelocatingShapeDrawer playerUnitDrawer;
    private static final Map<UnitType, RelocatingShapeDrawer> drawers;

    static {
        drawers = new HashMap<>();
        try {
            playerUnitDrawer = createPlayerUnitDrawer();
            for (UnitType type : UnitType.values()) {
                drawers.put(type, createDrawer(type));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка при загрузке изображений, " + e);
        }
    }

    private static RelocatingShapeDrawer createDrawer(UnitType type) throws IOException {
        return new RelocatingShapeDrawer(new BufferedImage[]{
            ImageIO.read(new File("images/" + type.name() + "/left.png")),
            ImageIO.read(new File("images/" + type.name() + "/up.png")),
            ImageIO.read(new File("images/" + type.name() + "/right.png")),
            ImageIO.read(new File("images/" + type.name() + "/down.png")),});
    }

    private static RelocatingShapeDrawer createPlayerUnitDrawer() throws IOException {
        return new RelocatingShapeDrawer(new BufferedImage[]{
            ImageIO.read(new File("images/PLAYER/at_left.png")),
            ImageIO.read(new File("images/PLAYER/at_up.png")),
            ImageIO.read(new File("images/PLAYER/at_right.png")),
            ImageIO.read(new File("images/PLAYER/at_down.png"))
        });
    }

    public static RelocatingShapeDrawer getPlayerUnitDrawer() throws IOException {
        return playerUnitDrawer;
    }

    public static RelocatingShapeDrawer getDrawer(UnitType unitType) {
        return drawers.get(unitType);
    }

}
