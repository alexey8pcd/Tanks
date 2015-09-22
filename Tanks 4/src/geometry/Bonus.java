package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alex
 */
public class Bonus extends GeometryShape {

    public static enum BonusType {

        POWER_UP(1, Color.RED),//увеличение скорости стрельбы и пробивание брони
        STOP_ENEMIES(2, Color.ORANGE),//остановка вражеских юнитов
        ARMOR_UP(3, Color.PINK);//усиление брони

        private final int value;
        private final Color color;

        private BonusType(int value, Color color) {
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

    private BonusType bonusType;

    public Bonus(Color color, int x, int y, int size, BonusType bonusType) {
        super(x, y, size);
        this.bonusType = bonusType;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(bonusType.getColor());
        g.fillOval(getX(), getY(), width, height);
    }

}
