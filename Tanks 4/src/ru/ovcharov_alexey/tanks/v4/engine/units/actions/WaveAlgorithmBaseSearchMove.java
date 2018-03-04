package ru.ovcharov_alexey.tanks.v4.engine.units.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Direction;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Vector2D;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Movable;

/**
 * @author Alexey
 */
public class WaveAlgorithmBaseSearchMove extends AbstractMoveActionWithCollision {

    public WaveAlgorithmBaseSearchMove(EnumSet<Material> impassable) {
        super(impassable);
    }

    @Override
    public boolean move(Movable movable, GeometryMap map, GeometryPoint target) {
        try {
/*
            int rows = map.getRowsCount();
            int columns = map.getColumnsCount();
            int currentX = Math.round(movable.getX());
            int currentY = Math.round(movable.getY());
            int targetX = Math.round(target.getX());
            int targetY = Math.round(target.getY());
            if (currentX != targetX || currentY != targetY) {
                int startRow = currentX / rows;
                int startColumn = currentY / columns;
                int targetRow = targetX / rows;
                int targetColumn = targetY / columns;
                int[] array = waveFast(startRow, startColumn, targetRow, targetColumn, rows, columns, map);
                List<GeometryPoint> path = restorePath(array, startRow, startColumn,
                        rows, columns, targetRow, targetColumn);
                if (path.size() > 1) {
                    GeometryPoint last = path.get(path.size() - 1);
                    GeometryPoint preLast = path.get(path.size() - 2);
                    float dx = preLast.getX() - last.getX();
                    float dy = preLast.getY() - last.getY();
                    Direction direction = Direction.approximate(dx, dy);
                    Vector2D vd = Vector2D.create(direction, movable.getSpeed());
                    movable.setDirection(vd);
                    if (canMove(movable, map, target)) {
                        movable.setLocation(dLeftX, dTopY);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            */
            int logicalRows = map.getHeight() / movable.getHeight();
            int logicalColumns = map.getWidth() / movable.getWidth();
            int currentX = Math.round(movable.getX());
            int currentY = Math.round(movable.getY());
            int targetX = Math.round(target.getX());
            int targetY = Math.round(target.getY());
            if (currentX != targetX || currentY != targetY) {
                int startRow = currentY / logicalRows;
                int startColumn = currentX / logicalColumns;
                int targetRow = targetY / logicalRows;
                int targetColumn = targetX / logicalColumns;
                int[] array = waveFast1(startRow, startColumn, targetRow,
                        targetColumn, logicalRows, logicalColumns, map);
                List<GeometryPoint> path = restorePath(array, startRow, startColumn,
                        logicalRows, logicalColumns, targetRow, targetColumn);
                if (path.size() > 1) {
                    GeometryPoint last = path.get(path.size() - 1);
                    GeometryPoint preLast = path.get(path.size() - 2);
                    float dx = preLast.getX() - last.getX();
                    float dy = preLast.getY() - last.getY();
//                    Direction direction = Direction.approximate(dx, dy);
//                    Vector2D vd = Vector2D.create(direction, movable.getSpeed());
//                    movable.setDirection(vd);
                    if (canMove(movable, map, target)) {
                        movable.setLocation(dLeftX, dTopY);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            Global.logAndShowException(ex);
        }

        return false;
    }

    private void printArray(int[] array, int rows, int columns) {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(String.format("%4d ", array[j + i * rows]));
            }
            System.out.println();
        }
        System.out.println("_____________________");
    }

    private class Cell {

        int x, y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    private int[] waveFast(int startTileX, int startTileY, int targetTileX,
            int targetTileY, int rowsCount, int columnsCount, GeometryMap map) {
        int d = 0;
        int targetIndex = targetTileX + rowsCount * targetTileY;
        int[] marked = new int[rowsCount * columnsCount];
        Arrays.fill(marked, Integer.MIN_VALUE);
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if (impassable.contains(map.getTileAt(i, j))) {
                    marked[j + i * columnsCount] = Integer.MAX_VALUE;
                }
            }
        }

        Map<Integer, List<Cell>> loc = new HashMap<>();
        loc.put(d, Collections.singletonList(new Cell(startTileX, startTileY)));
        marked[startTileX + startTileY * rowsCount] = d;
        boolean end = false;
        do {
            boolean canNext = false;
            List<Cell> cells = loc.get(d);
            for (Cell cell : cells) {
                int x = cell.x;
                int y = cell.y;
                int rx = x + 1;
                int wy = rowsCount * y;
                List<Cell> next = loc.get(d + 1);
                if (next == null) {
                    next = new ArrayList<>();
                    loc.put(d + 1, next);
                }

                if (rx < rowsCount && marked[rx + wy] == Integer.MIN_VALUE) {
                    next.add(new Cell(rx, y));
                    marked[rx + wy] = d + 1;
                    canNext = true;

                    if (rx + wy == targetIndex) {
                        end = true;
                        break;
                    }

                }
                int lx = x - 1;
                if (lx >= 0 && marked[lx + wy] == Integer.MIN_VALUE) {
                    next.add(new Cell(lx, y));
                    marked[lx + wy] = d + 1;
                    canNext = true;

                    if (lx + wy == targetIndex) {
                        end = true;
                        break;
                    }

                }
                int dy = y + 1;
                if (dy < columnsCount
                        && marked[x + rowsCount * dy] == Integer.MIN_VALUE) {
                    next.add(new Cell(x, dy));
                    marked[x + rowsCount * dy] = d + 1;
                    canNext = true;

                    if (x + rowsCount * dy == targetIndex) {
                        end = true;
                        break;
                    }

                }
                int ty = y - 1;
                if (ty >= 0 && marked[x + rowsCount * ty] == Integer.MIN_VALUE) {
                    next.add(new Cell(x, ty));
                    marked[x + rowsCount * ty] = d + 1;
                    canNext = true;

                    if (x + rowsCount * ty == targetIndex) {
                        end = true;
                        break;
                    }
                }
            }
            if (!canNext) {
                end = true;
            }
            ++d;
        } while (!end);
        return marked;
    }

    
    private int[] waveFast1(int startRow, int startColumn,
            int targetRow, int targetColumn, int logicalRows,
            int logicalColumns, GeometryMap map) {
        int d = 0;
        int targetIndex = targetRow + logicalRows * targetColumn;

        int movableWidth = map.getWidth() / logicalColumns;
        int tileSize = map.getTileSize();
        int movableWidthInTiles = movableWidth / tileSize;
        int movableHeigth = map.getHeight() / logicalRows;
        int movableHeightInTiles = movableHeigth / tileSize;

        int[] marked = new int[logicalRows * logicalColumns];
        Arrays.fill(marked, Integer.MIN_VALUE);
        for (int i = 0; i < logicalRows; i++) {
            for (int j = 0; j < logicalColumns; j++) {
                boolean passible = true;
                label1:
                for (int k = 0; k < movableHeightInTiles; k++) {
                    for (int l = 0; l < movableWidthInTiles; l++) {
                        if (impassable.contains(
                                map.getTileAt(i + k, j + l))) {
                            passible = false;
                            break label1;
                        }
                    }
                }
                if (!passible) {
                    marked[j + i * logicalColumns] = Integer.MAX_VALUE;
                }
            }
        }

        Map<Integer, List<Cell>> loc = new HashMap<>();
        loc.put(d, Collections.singletonList(new Cell(startRow, startColumn)));
        marked[startRow + startColumn * logicalRows] = d;
        boolean end = false;
        do {
            boolean canNext = false;
            List<Cell> cells = loc.get(d);
            for (Cell cell : cells) {
                int x = cell.x;
                int y = cell.y;
                int rx = x + 1;
                int wy = logicalRows * y;
                List<Cell> next = loc.get(d + 1);
                if (next == null) {
                    next = new ArrayList<>();
                    loc.put(d + 1, next);
                }

                if (rx < logicalRows && marked[rx + wy] == Integer.MIN_VALUE) {
                    next.add(new Cell(rx, y));
                    marked[rx + wy] = d + 1;
                    canNext = true;

                    if (rx + wy == targetIndex) {
                        end = true;
                        break;
                    }
                }
                int lx = x - 1;
                if (lx >= 0 && marked[lx + wy] == Integer.MIN_VALUE) {
                    next.add(new Cell(lx, y));
                    marked[lx + wy] = d + 1;
                    canNext = true;

                    if (lx + wy == targetIndex) {
                        end = true;
                        break;
                    }
                }
                int dy = y + 1;
                if (dy < logicalColumns
                        && marked[x + logicalRows * dy] == Integer.MIN_VALUE) {
                    next.add(new Cell(x, dy));
                    marked[x + logicalRows * dy] = d + 1;
                    canNext = true;

                    if (x + logicalRows * dy == targetIndex) {
                        end = true;
                        break;
                    }
                }
                int ty = y - 1;
                if (ty >= 0 && marked[x + logicalRows * ty] == Integer.MIN_VALUE) {
                    next.add(new Cell(x, ty));
                    marked[x + logicalRows * ty] = d + 1;
                    canNext = true;

                    if (x + logicalRows * ty == targetIndex) {
                        end = true;
                        break;
                    }
                }
            }
            if (!canNext) {
                end = true;
            }
            ++d;
        } while (!end);
        return marked;
    }
     
    private List<GeometryPoint> restorePath(int[] array, int startTileX,
            int startTileY, int arrayWidth, int arrayHeight,
            int targetTileX, int targetTileY) throws Exception {
        List<GeometryPoint> path = new ArrayList<>();
        int currentX = targetTileX, currentY = targetTileY;
        int value;
        while (currentX != startTileX || currentY != startTileY) {
            value = array[currentX + currentY * arrayWidth];
            path.add(new GeometryPoint(currentX, currentY));
            int lx = currentX - 1;
            int rx = currentX + 1;
            int ty = currentY - 1;
            int dy = currentY + 1;

            if (inArray(lx, currentY, arrayWidth, arrayHeight)) {
                int lxy = array[lx + currentY * arrayWidth];
                if (lxy == value - 1) {
                    currentX = lx;
                    continue;
                }
            }
            if (inArray(rx, currentY, arrayWidth, arrayHeight)) {
                int rxy = array[rx + currentY * arrayWidth];
                if (rxy == value - 1) {
                    currentX = rx;
                    continue;
                }
            }
            if (inArray(currentX, ty, arrayWidth, arrayHeight)) {
                int xty = array[currentX + ty * arrayWidth];
                if (xty == value - 1) {
                    currentY = ty;
                    continue;
                }
            }
            if (inArray(currentX, dy, arrayWidth, arrayHeight)) {
                int xdy = array[currentX + dy * arrayWidth];
                if (xdy == value - 1) {
                    currentY = dy;
                    continue;
                }
            }
            break;
        }
        return path;
    }

    private boolean inArray(int x, int y, int arrayWidth, int arrayHeight) {
        return x >= 0 && x < arrayWidth && y >= 0 && y < arrayHeight;
    }

}
