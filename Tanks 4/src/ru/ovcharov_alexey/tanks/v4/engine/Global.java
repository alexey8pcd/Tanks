package ru.ovcharov_alexey.tanks.v4.engine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 * @author Alexey
 */
public class Global {

    private static int mapSizeIndex;
    private static Statistics statistics = Statistics.empty();
    private static String pathToCompaniesFolder = ".";
    private static final Logger LOGGER = Logger.getLogger("Tanks 4");

    static {
        LOGGER.setLevel(java.util.logging.Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("game.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException | SecurityException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static Statistics getStatistics() {
        return statistics;
    }

    public static int getMapSizeIndex() {
        return mapSizeIndex;
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR);
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
                    dataOutputStream.writeInt(LOGGER.getLevel().intValue());
                }
            }
        } catch (Exception ex) {
            Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
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
                Global.LOGGER.setLevel(Level.parse(String.valueOf(dataInputStream.readInt())));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void clearStatistics() {
        Global.statistics = Statistics.empty();
    }

    public static void logAndShowException(Exception ex) {
        Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
        showErrorMessage("В игре произошла ошибка, подробная информация в файле журнала");
    }

    public static void disableLogger() {
        LOGGER.setLevel(Level.OFF);
    }

    public static void enableLogErrors() {
        LOGGER.setLevel(Level.SEVERE);
    }

    public static void enableLogAllMessages() {
        LOGGER.setLevel(Level.INFO);
    }
    
    public static boolean isLoggerEnabled(){
        return LOGGER.getLevel() != Level.OFF;
    }
    
    public static boolean isLoggingOnlyErrors(){
        return LOGGER.getLevel() == Level.SEVERE;
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
