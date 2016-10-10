package ru.ovcharov_alexey.tanks.v4.logic.controllers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import ru.ovcharov_alexey.tanks.v4.logic.edit.Tool;
import ru.ovcharov_alexey.tanks.v4.persist.GeometryMapPersistance;

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
    private Deque<GeometryMap> history;
    private final int maxHistoryDepth = 20;

    public MapEditorController(int mapWidth, int mapHeight, Graphics graphics) {
        buffer = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_RGB);
        this.rootGraphics = graphics;
        currentMap = GeometryMap.newInstance();
        history = new ArrayDeque<>(maxHistoryDepth);
        tool = new Tool(Material.BRICK, 0, 0);
        scale = 16;
        tool.setSize(scale);
    }

    public void draw() {
        Graphics temp = buffer.getGraphics();
        temp.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
        drawObjects(temp);
        rootGraphics.drawImage(buffer, 0, 0, null);
    }

    private void drawObjects(Graphics graphics) {
        currentMap.drawWithGrid(graphics);
        tool.draw(graphics);
    }

    public int getScale() {
        return scale;
    }

    public void redo() {
    }

    public void saveMap() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("map.dat"));
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            boolean saved = GeometryMapPersistance.saveMapToFile(selectedFile,
                    currentMap);
            if (saved) {
                JOptionPane.showMessageDialog(null, "Карта сохранена успешно",
                        "Информация", JOptionPane.INFORMATION_MESSAGE);
            }
        }
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

    public void saveToHistory() {
        if (history.size() > maxHistoryDepth) {
            history.removeFirst();
        }
        history.add(GeometryMap.copyMap(currentMap));
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
        saveToHistory();
        this.currentMap.clear();
        draw();
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }

        if (history.size()
                == 1) {
            currentMap = history.peek();
        } else {
            currentMap = history.removeLast();
        }
        draw();
    }

    public GeometryMap getMap() {
        return currentMap;
    }
}
