package ru.ovcharov_alexey.tanks.v4.engine;

/**
@author Alexey
*/
public class Global {


    public static class Size {
        private int width;
        private int heigth;

        public Size(int width, int heigth) {
            this.width = width;
            this.heigth = heigth;
        }

        public int getWidth() {
            return width;
        }

        public int getHeigth() {
            return heigth;
        }
        
    }
    public static final Size[] MAP_SIZE_VALUES = {
        new Size(768, 512),
        new Size(960, 640),
        new Size(1024, 768),
        new Size(1280, 1024),
        new Size(1600, 1200),
        new Size(1920, 1080)
    };
    private static int mapWidth = GeometryMap.MAX_WIDTH;
    private static int mapHeight = GeometryMap.MAX_HEIGTH;
    private static int speed = 50;

    public static double getMapWidth() {
        return mapWidth;
    }

    public static void setMapWidth(int mapWidth) {
        Global.mapWidth = mapWidth;
    }

    public static void setMapSize(Size size){
        Global.mapWidth = size.getWidth();
        Global.mapHeight = size.getHeigth();
    }
    public static double getMapHeight() {
        return mapHeight;
    }

    public static void setMapHeight(int mapHeight) {
        Global.mapHeight = mapHeight;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Global.speed = speed;
    }
    
}
