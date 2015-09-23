package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class GeometryMap extends GeometryShape implements Drawable{

    public static final int MIN_TILE_SIZE = 1;
    public static final int MAX_TILE_SIZE = 16;
    public static final int MAX_WIDTH = 768;
    public static final int MAX_HEIGTH = 512;

    public static enum Material {

        TERRA(1, Color.WHITE),//по-умолчанию, можно установить цвет фона
        BRICK(2, new Color(0xff, 0xff, 0)),//коричневый
        ARMOR(3, Color.GRAY),
        WATER(4, Color.BLUE),
        WOOD(5, Color.GREEN),
        ICE(6, Color.CYAN);
        private final int value;
        private final Color color;

        private Material(int value, Color color) {
            this.value = value;
            this.color = color;
        }

        public int getValue() {
            return value;
        }

        public Color getColor() {
            return color;
        }
    }

    private final Material[] tiles;
    private final int rowsCount;
    private final int columnsCount;
    private final int tileSize;

    private GeometryMap(int width, int height, int tileSize) {
        super(0, 0, width, height);
        this.tileSize = tileSize;
        rowsCount = height / tileSize;
        columnsCount = width / tileSize;
        tiles = new Material[rowsCount * columnsCount];
        for (int i = 0; i < rowsCount; ++i) {
            for (int j = 0; j < columnsCount; ++j) {
                tiles[i * columnsCount + j] = Material.TERRA;
            }
        }
    }

    public static GeometryMap newInstance(int tileSize, int rows, int columns) {
        if (tileSize < MIN_TILE_SIZE || tileSize > MAX_TILE_SIZE) {
            throw new IllegalArgumentException("Размер ячейки карты задан неверно");
        }
        int width = columns * tileSize;
        int height = rows * tileSize;
        if (width < MIN_TILE_SIZE || width > MAX_WIDTH || height < MIN_TILE_SIZE || height > MAX_HEIGTH) {
            throw new IllegalArgumentException("Размер карты задан неверно");
        }
        return new GeometryMap(width, height, tileSize);
    }

    public void setTileAt(int row, int column, Material material) {
        if (material == null) {
            throw new NullPointerException("Материал не может быть null");
        }
        tiles[row * columnsCount + column] = material;
    }

    public Material getTile(int x, int y) {
        int row = y / tileSize;
        int column = x / tileSize;
        return getTileAt(column, row);
    }

    public Material getTileAt(int row, int column) {
        return tiles[row * columnsCount + column];
    }

    public void removeTile(int row, int column) {
        tiles[row * columnsCount + column] = Material.TERRA;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0, y = 0; i < rowsCount; ++i, y += tileSize) {
            for (int j = 0, x = 0; j < columnsCount; ++j, x += tileSize) {
                g.setColor(tiles[i * columnsCount + j].getColor());
                g.fillRect(x, y, tileSize, tileSize);
            }
        }

    }

}
