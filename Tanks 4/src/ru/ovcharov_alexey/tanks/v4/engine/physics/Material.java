package ru.ovcharov_alexey.tanks.v4.engine.physics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.ImageUtils;

/**
 * Представляет материал для формирования объектов карты. Имеет цвет и
 * твердость.
 */
public enum Material {
    TERRA(0, Color.WHITE, 0), //по-умолчанию, можно установить цвет фона
    BRICKS(2, new Color(0xff, 0x88, 0), 1), //коричневый
    METAL(3, Color.GRAY, 2),
    WATER(0, new Color(0x89BAFF), 3),
    FOREST(1, Color.GREEN, 4),
    ICE(0, Color.CYAN, 5);
    private final int hardness; //твердость
    private final Color color;
    private final int code;
    private int[] imageBuffer;

    public void setImageBuffer(int[] imageBuffer) {
        this.imageBuffer = imageBuffer;
    }

    private static final int MIN_CODE = 0;
    private static final int MAX_CODE = 5;

    public static void init(int tileSize) throws Exception {
        for (Material value : Material.values()) {
            String name = "/images/button_images/" + value.name().toLowerCase() + ".png";
            BufferedImage image = ImageIO.read(Material.class.getResourceAsStream(name));
            BufferedImage scaledImage = ImageUtils.scale(image, tileSize, tileSize);
            int[] buff = new int[tileSize * tileSize];
            value.setImageBuffer(scaledImage.getRGB(0, 0, tileSize, tileSize, buff, 0, tileSize));
        }
    }

    private Material(int hardness, Color color, int code) {
        this.hardness = hardness;
        this.color = color;
        this.code = code;
    }

    public int getHardness() {
        return hardness;
    }

    public Color getColor() {
        return color;
    }

    public int getCode() {
        return code;
    }

    public static Material getMaterial(int code) {
        if (code < MIN_CODE || code > MAX_CODE) {
            code = MIN_CODE;
        }
        for (Material m : Material.values()) {
            if (m.code == code) {
                return m;
            }
        }
        return TERRA;
    }

    public int[] getImageBuffer() {
        return imageBuffer;
    }

}
