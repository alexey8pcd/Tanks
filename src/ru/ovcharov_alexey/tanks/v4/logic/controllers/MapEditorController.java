package ru.ovcharov_alexey.tanks.v4.logic.controllers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.logic.edit.Tool;
import ru.ovcharov_alexey.tanks.v4.engine.persist.GeometryMapPersistance;

/**
 * @author Alexey
 */
public class MapEditorController {

    private GeometryMap currentMap;
    private BufferedImage buffer;
    private Graphics rootGraphics;
    private Tool tool;
    private int scale;
    private final int minScale = 4;
    private final int maxScale = 128;
    private List<GeometryMap> history;
    private final int maxHistoryDepth = 20;
    private int currentHistoryIndex;

    public MapEditorController(int mapWidth, int mapHeight, Graphics graphics) throws Exception {
        buffer = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_RGB);
        this.rootGraphics = graphics;
        currentMap = GeometryMap.newInstance();
        history = new ArrayList<>();
        tool = new Tool(Material.BRICKS, 0, 0);
        scale = 16;
        tool.setSize(scale);
    }

    public void draw() {
        Graphics2D temp = (Graphics2D) buffer.getGraphics();
        temp.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
        drawObjects(temp);
        rootGraphics.drawImage(buffer, 0, 0, null);
    }

    private void drawObjects(Graphics2D graphics) {
        currentMap.drawWithGrid(graphics);
        tool.draw(graphics);
    }

    public int getScale() {
        return scale;
    }

    public void redo() {
        if (currentHistoryIndex < history.size() - 1) {
            currentMap = history.get(++currentHistoryIndex);
            draw();
        }
    }

    public void saveMap() {
        GeometryMapPersistance.saveMapToFile(currentMap);
    }

    public void addTilesOnMap() {
        saveToHistory();
        int maxX = tool.getX() + tool.getWidth();
        int maxY = tool.getY() + tool.getHeight();
        for (int x = tool.getX(); x < maxX; x++) {
            for (int y = tool.getY(); y < maxY; y++) {
                currentMap.setTile(x, y, tool.getMaterial());
            }
        }
        draw();
    }

    private void saveToHistory() {
        if (history.size() >= maxHistoryDepth) {
            history.remove(0);
            if (currentHistoryIndex > 0) {
                --currentHistoryIndex;
            }
        }
        if (history.isEmpty()) {
            history.add(currentMap);
            currentHistoryIndex = 0;
        } else if (currentHistoryIndex == history.size() - 1) {
            history.add(currentMap);
            ++currentHistoryIndex;
        } else {
            history.set(currentHistoryIndex, currentMap);
            for (int i = currentHistoryIndex + 1; i < history.size();) {
                history.remove(i);
            }
        }
        currentMap = new GeometryMap(currentMap);
    }

    public void changeScale(int wheelRotation, int x, int y) {
        if (wheelRotation > 0) {
            scale -= 4;
            if (scale < minScale) {
                scale = minScale;
            }
        } else {
            scale += 4;
            if (scale > maxScale) {
                scale = maxScale;
            }
        }
        tool.setSize(scale);
        tool.setLocationOfCenter(x, y);
        draw();
    }

    public void setToolSize(int scale) {
        this.tool.setSize(scale);
        draw();
    }

    public void setToolLocation(int x, int y) {
        this.tool.setLocationOfCenter(x, y);
        draw();
    }

    public void setToolMaterial(Material material) {
        this.tool.setMaterial(material);
        draw();
    }

    public void clearMap() {
        try {
            saveToHistory();
            this.currentMap.clear();
            draw();
        } catch (Exception ex) {
            Global.logAndShowException(ex);
        }
    }

    public void undo() {
        if (history.isEmpty() || currentHistoryIndex < 0) {
            return;
        }
        currentMap = history.get(currentHistoryIndex);
        if (currentHistoryIndex > 0) {
            --currentHistoryIndex;
        }
        draw();
    }

    public GeometryMap getMap() {
        return currentMap;
    }
}
