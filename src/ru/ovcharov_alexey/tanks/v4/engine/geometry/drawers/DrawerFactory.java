package ru.ovcharov_alexey.tanks.v4.engine.geometry.drawers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;

/**
 *
 * @author alex
 */
public class DrawerFactory {

    private static RelocatingShapeDrawer playerUnitDrawer;
    private static RelocatingShapeDrawer playerUnitArmoredDrawer;
    private static final Map<UnitType, RelocatingShapeDrawer> DRAWERS;
    private static final int DIRECTIONS_COUNT = 4;
    private static final String[] PLAYER_UNIT_BASE_IMAGE_NAMES = {
        "/images/PLAYER/at_left.png",
        "/images/PLAYER/at_up.png",
        "/images/PLAYER/at_right.png",
        "/images/PLAYER/at_down.png"
    };
    private static final String[] PLAYER_UNIT_ARMORED_IMAGE_NAMES = {
        "/images/PLAYER/at_left_armored.png",
        "/images/PLAYER/at_up_armored.png",
        "/images/PLAYER/at_right_armored.png",
        "/images/PLAYER/at_down_armored.png"
    };

    static {
        DRAWERS = new HashMap<>();
        try {
            playerUnitDrawer = createPlayerUnitDrawer(PLAYER_UNIT_BASE_IMAGE_NAMES);
            playerUnitArmoredDrawer = createPlayerUnitDrawer(PLAYER_UNIT_ARMORED_IMAGE_NAMES);
            for (UnitType type : UnitType.values()) {
                DRAWERS.put(type, createDrawer(type));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка при загрузке изображений, " + e);
        }
    }

    private static RelocatingShapeDrawer createDrawer(UnitType type) throws IOException {
        NamedImage[] directionImages = new NamedImage[DIRECTIONS_COUNT];

        for (int i = 0; i < DIRECTIONS_COUNT; i++) {
            String name = "/images/" + type.name() + "/"
                    + Direction.values()[i].toString().toLowerCase() + ".png";
            BufferedImage image = ImageIO.read(DrawerFactory.class.getResourceAsStream(name));
            directionImages[i] = new NamedImage(image, name);
        }
        return new RelocatingShapeDrawer(directionImages);
    }

    private static RelocatingShapeDrawer createPlayerUnitDrawer(String[] imageNames) throws IOException {
        NamedImage[] directionImages = loadImages(imageNames);
        return new RelocatingShapeDrawer(directionImages);
    }

    private static NamedImage[] loadImages(String[] playerImageNames) throws IOException {
        NamedImage[] directionImages = new NamedImage[DIRECTIONS_COUNT];
        for (int i = 0; i < DIRECTIONS_COUNT; i++) {
            InputStream resource
                    = DrawerFactory.class.getResourceAsStream(playerImageNames[i]);
            BufferedImage image = ImageIO.read(resource);
            directionImages[i] = new NamedImage(image, playerImageNames[i]);
        }
        return directionImages;
    }

    public static RelocatingShapeDrawer getPlayerUnitDrawer() {
        return playerUnitDrawer;
    }

    public static RelocatingShapeDrawer getPlayerUnitArmoredDrawer() {
        return playerUnitArmoredDrawer;
    }
    
    public static RelocatingShapeDrawer getDrawer(UnitType unitType) {
        return DRAWERS.get(unitType);
    }
}
