/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ovcharov_alexey.tanks.v4.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.Drawable;
import ru.ovcharov_alexey.tanks.v4.engine.geometry.GeometryPoint;

/**
 *
 * @author Admin
 */
public class DamageText implements Drawable {

    private final int damage;
    private final boolean critical;
    private final GeometryPoint position;
    private final long startTime;

    public DamageText(int damage, boolean critical, GeometryPoint point) {
        this.damage = damage;
        this.critical = critical;
        this.position = new GeometryPoint(point);
        this.startTime = System.nanoTime();
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public void draw(Graphics2D g) {
        Font font = g.getFont();
        g.setColor(Color.PINK);
        g.setFont(critical ? Global.LARGE_FONT : Global.MAIN_FONT);
        g.drawString(String.valueOf(damage), position.getX(), position.getY());
        g.setFont(font);
    }

    public void relocate() {
        this.position.setY(this.position.getY() + 1);
    }

    @Override
    public boolean isVisible() {
        return true;
    }

}
