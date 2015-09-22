package geometry;

import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class Unit extends GeometryShape implements Movable {

    private int moveSpeed;

    public Unit(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean move(GeometryMap map) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
