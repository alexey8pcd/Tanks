package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class GeometryMap extends GeometryShape implements Drawable {

    /**
     * Представляет материал для формирования объектов карты. Имеет цвет и
     * твердость.
     */
    public static enum Material {

        TERRA(0, Color.WHITE, 0),//по-умолчанию, можно установить цвет фона
        BRICK(2, new Color(0xff, 0x88, 0), 1),//коричневый
        ARMOR(3, Color.GRAY, 2),
        WATER(0, Color.BLUE, 3),
        WOOD(1, Color.GREEN, 4),
        ICE(0, Color.CYAN, 5);
        private final int hardness;//твердость
        private final Color color;
        private final int code;
        private static final int MIN_CODE = 0;
        private static final int MAX_CODE = 5;

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
            for (Material m : values()) {
                if (m.code == code) {
                    return m;
                }
            }
            return TERRA;
        }
    }

    public static final int MIN_TILE_SIZE = 1;
    public static final int DEFAUL_TILE_SIZE = 8;
    public static final int MAX_TILE_SIZE = 16;
    public static final int MAX_WIDTH = 768;
    public static final int MAX_HEIGTH = 512;
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

    public int getRowsCount() {
        return rowsCount;
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public int getTileSize() {
        return tileSize;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    /**
     * Создает новую карту 768*512 пикселей с ячейками в 1 пиксель.
     *
     * @return
     */
    public static GeometryMap newInstance() {
        return new GeometryMap(MAX_WIDTH, MAX_HEIGTH, DEFAUL_TILE_SIZE);
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

    public void clear() {
        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < columnsCount; column++) {
                setTileAt(row, column, Material.TERRA);
            }
        }
    }

    public void setTileAt(int row, int column, Material material) {
        if (material == null) {
            throw new NullPointerException("Материал не может быть null");
        }
        int index = row * columnsCount + column;
        if (index < 0 || index >= tiles.length) {
            return;
        }
        tiles[index] = material;
    }

    public void setTile(int x, int y, Material material) {
        if (material == null) {
            throw new NullPointerException("Материал не может быть null");
        }
        if (x < 0 || x > width || y < 0 || height > height) {
            return;
        }
        int row = y / tileSize;
        int column = x / tileSize;
        setTileAt(row, column, material);
    }

    public Material getTile(int x, int y) {
        int row = y / tileSize;
        int column = x / tileSize;
        return getTileAt(row, column);
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

    public void drawWithGrid(Graphics g) {
        for (int i = 0, y = 0; i < rowsCount; ++i, y += tileSize) {
            for (int j = 0, x = 0; j < columnsCount; ++j, x += tileSize) {
                g.setColor(tiles[i * columnsCount + j].getColor());
                g.fillRect(x, y, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawLine(x, 0, x, height);
                g.drawLine(0, y, width, y);
            }
        }

    }

}
