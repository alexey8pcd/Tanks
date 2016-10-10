package ru.ovcharov_alexey.tanks.v4.engine;

import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import java.awt.Color;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryShape;

/**
 *
 * @author alex
 */
public class GeometryMap extends GeometryShape implements Drawable {


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
    
    public void save(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(getTileSize());
        outputStream.writeInt(getRowsCount());
        outputStream.writeInt(getColumnsCount());
        for (int row = 0; row < getRowsCount(); row++) {
            for (int column = 0; column < getColumnsCount(); column++) {
                outputStream.writeByte(getTileAt(row, column).getCode());
            }
        }
    }
    
    public static GeometryMap load(DataInputStream inputStream) throws IOException {
        int tileSize = inputStream.readInt();
        int rowCount = inputStream.readInt();
        int columnCount = inputStream.readInt();
        GeometryMap map = GeometryMap.newInstance(tileSize, rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                map.setTileAt(row, column, Material.getMaterial(inputStream.readByte()));
            }
        }
        return map;
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

    public static GeometryMap copyMap(GeometryMap toCopy) {
        GeometryMap map = new GeometryMap(toCopy.getWidth(), toCopy.getHeight(),
                toCopy.getTileSize());
        for (int row = 0; row < map.rowsCount; row++) {
            for (int column = 0; column < map.columnsCount; column++) {
                map.setTileAt(row, column, toCopy.getTileAt(row, column));
            }
        }
        return map;
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
