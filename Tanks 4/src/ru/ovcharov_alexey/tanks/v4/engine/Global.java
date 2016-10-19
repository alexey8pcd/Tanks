package ru.ovcharov_alexey.tanks.v4.engine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Alexey
 */
public class Global {

    private static int mapSizeIndex;
    private static Statistics statistics = Statistics.empty();
    private static String pathToCompaniesFolder = ".";

    public static Statistics getStatistics() {
        return statistics;
    }

    public static int getMapSizeIndex() {
        return mapSizeIndex;
    }

    public static String getPathToCompaniesFolder() {
        return pathToCompaniesFolder;
    }

    public static void setPathToCompaniesFolder(String pathToCompaniesFolder) {
        Global.pathToCompaniesFolder = pathToCompaniesFolder;
    }

    public static void save() {
        try {
            File settings = new File("settings.dat");
            if (!settings.exists()) {
                settings.createNewFile();
            }
            if (settings.exists()) {
                try (DataOutputStream dataOutputStream
                        = new DataOutputStream(new FileOutputStream(settings))) {
                    dataOutputStream.writeInt(mapSizeIndex);
                    dataOutputStream.writeInt(speed);
                    statistics.save(dataOutputStream);
                    dataOutputStream.writeUTF(pathToCompaniesFolder);
                }
            }
        } catch (Exception ex) {
        }

    }

    public static void load() {
        try {
            File settings = new File("settings.dat");
            try (DataInputStream dataInputStream
                    = new DataInputStream(new FileInputStream(settings))) {
                Global.setMapSizeIndex(dataInputStream.readInt());
                Global.setSpeed(dataInputStream.readInt());
                Global.statistics = Statistics.load(dataInputStream);
                Global.pathToCompaniesFolder = dataInputStream.readUTF();
            }
        } catch (Exception e) {
        }
    }

    public static void clearStatistics() {
        Global.statistics = Statistics.empty();
    }

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

    public static void setMapSizeIndex(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < MAP_SIZE_VALUES.length) {
            Global.mapSizeIndex = selectedIndex;
            Size size = MAP_SIZE_VALUES[selectedIndex];
            Global.mapWidth = size.getWidth();
            Global.mapHeight = size.getHeigth();
        }

    }

    public static double getMapHeight() {
        return mapHeight;
    }

    public static float getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Global.speed = speed;
    }

}
