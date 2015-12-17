package main;

import geometry.Drawable;
import geometry.GeometryMap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import persist.GeometryMapPersistance;

/**
 *
 * @author alex
 */
public class MapEditor {

    private class RectangleDrawingTool implements Drawable {

        GeometryMap.Material material;
        int x;
        int y;
        int width;
        int height;

        public RectangleDrawingTool(GeometryMap.Material material, int leftX, int topY) {
            this.material = material;
            this.x = leftX;
            this.y = topY;
        }

        public void setSize(int size) {
            this.width = size;
            this.height = size;
        }

        public void setLocationOfCenter(int x, int y) {
            this.x = x - width / 2;
            this.y = y - height / 2;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawRect(x - 1, y - 1, width + 1, height + 1);
            g.setColor(material.getColor());
            g.fillRect(x, y, width, height);
        }

        @Override
        public boolean isVisible() {
            return true;
        }

    }
    private final GeometryMap currentMap;
    private final BufferedImage buffer;
    private final Graphics rootGraphics;
    private final RectangleDrawingTool tool;
    
    private int scale;
    private int minScale;
    private int maxScale;

    public MapEditor(int width, int height, int defaultScale, 
            JSlider scaleSlider, Graphics graphics) {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        rootGraphics = graphics;
        currentMap = GeometryMap.newInstance();
        tool = new RectangleDrawingTool(GeometryMap.Material.BRICK, 0, 0);
        scale = defaultScale;
        maxScale = defaultScale;
        minScale = defaultScale;        
        tool.setSize(scale);
    }

    public void setMinScale(int minScale) {
        this.minScale = minScale;
    }

    public void setMaxScale(int maxScale) {
        this.maxScale = maxScale;
    }

    public void handleMouseMove(int xPosition, int yPosition) {
        tool.setLocationOfCenter(xPosition, yPosition);
        draw();
    }

    public void addTilesOnMap() {
        for (int x = tool.x; x < tool.x + tool.width; x++) {
            for (int y = tool.y; y < tool.y + tool.height; y++) {
                currentMap.setTile(x, y, tool.material);
            }
        }
        draw();
    }

    public GeometryMap getCurrentMap() {
        return currentMap;
    }   

    public void clearMap() {
        currentMap.clear();
        draw();
    }

    public void changeScale(MouseWheelEvent evt, JSlider sliderForScaling) {
        if (evt.getWheelRotation() > 0) {
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
        sliderForScaling.setValue(scale);
        sliderForScaling.updateUI();
        tool.setSize(scale);
        tool.setLocationOfCenter(evt.getX(), evt.getY());
        draw();
    }

    public void setMaterial(GeometryMap.Material material) {
        tool.material = material;
        draw();
    }

    public void setScaleOfTool(int scale) {
        tool.setSize(scale);
        draw();
    }

    public void handleMouseDrag(int xPosition, int yPosition) {
        tool.setLocationOfCenter(xPosition, yPosition);
        addTilesOnMap();
    }

    private void draw() {
        Graphics temp = buffer.getGraphics();
        temp.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
        drawObjects(temp);
        rootGraphics.drawImage(buffer, 0, 0, null);
    }

    private void drawObjects(Graphics graphics) {
        currentMap.drawWithGrid(graphics);
        tool.draw(graphics);
    }

}
