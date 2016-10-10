package ru.ovcharov_alexey.tanks.v4.logic.edit;

import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import java.awt.Color;
import java.awt.Graphics;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;

/**
 * @author Alexey
 */
public class Tool implements Drawable {

    private Material material;
    private int x;
    private int y;
    private int width;
    private int height;

    public Tool(Material material, int startX, int startY) {
        this.material = material;
        this.x = startX;
        this.y = startY;
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

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
