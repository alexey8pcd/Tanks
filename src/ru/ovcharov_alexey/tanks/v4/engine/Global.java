package ru.ovcharov_alexey.tanks.v4.engine;

import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Campaign;
import ru.ovcharov_alexey.tanks.v4.util.NonNull;

/**
 * @author Alexey
 */
public class Global {

    private static boolean fullScreen = true;

    public static final Random RANDOM = new Random();
    public static final Font LARGE_FONT = new Font("Arial", Font.BOLD, 26);
    public static final Font MAIN_FONT = new Font("Arial", Font.BOLD, 14);
    public static final double CRITICAL_DAMAGE_FACTOR = 2.25;
    public static final int BASE_CHANCE_TO_CRITICAL_DAMAGE = 10;
    public static final int BASE_EXPERIENCE_FOR_NEXT_SKILL = 100;
    public static final double EXPERIENCE_GROW = 1.33;
    public static final int EXTRA_HP_PER_LEVEL = 10;
    public static final int BASE_EXPERIENCE_PER_ENEMY = 20;
    public static final int EXTRA_DAMAGE_PER_LEVEL = 5;
    public static final long MAX_SHOW_DAMAGE_TIME = TimeUnit.SECONDS.toNanos(3);

    private static final Map<String, String> ACHIEVEMENTS = new ConcurrentHashMap<>();
    private static final Logger LOGGER = Logger.getLogger("Tanks 4");
    private static final int BASE_SPEED = 50;

    private static int speed = BASE_SPEED;
    private static boolean audio = true;
    private static String pathToCompaniesFolder = ".";
    private static Statistics statistics = Statistics.empty();
    /**
     * Коэффициент аэродинамического сопротивления
     */
    public static double SPEED_SLOW_COEFF = 0.002;

    static {
        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.fileNameLabelText", "Название файла");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Тип файла");
        UIManager.put("FileChooser.lookInLabelText", "Найти в");
        LOGGER.setLevel(java.util.logging.Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("game.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException | SecurityException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static List<ru.ovcharov_alexey.tanks.v4.logic.campaign.Level> getOpenLevels(Campaign selectedCampaign,
            List<ru.ovcharov_alexey.tanks.v4.logic.campaign.Level> allLevels) {
        return allLevels;
    }

    public static void setFullScreen(boolean value) {
        fullScreen = value;
    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static void setSpeed(int speed) {
        Global.speed = speed;
    }

    public static float getScaledSpeed() {
        return 10 + BASE_SPEED + (speed - BASE_SPEED) / 3;
    }

    public static float getSpeed() {
        return speed;
    }

    public static boolean isLoggingOnlyErrors() {
        return LOGGER.getLevel() == Level.SEVERE;
    }

    public static boolean isLoggerEnabled() {
        return LOGGER.getLevel() != Level.OFF;
    }

    public static void enableLogAllMessages() {
        LOGGER.setLevel(Level.INFO);
    }

    public static void enableLogErrors() {
        LOGGER.setLevel(Level.SEVERE);
    }

    public static void disableLogger() {
        LOGGER.setLevel(Level.OFF);
    }

    public static void logAndShowException(Exception ex) {
        Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
        showErrorMessage("В игре произошла ошибка, подробная информация в файле журнала");
    }

    public static void clearStatistics() {
        Global.statistics = Statistics.empty();
    }

    public static void load() {
        try {
            File settings = new File("settings.dat");
            if (settings.exists()) {
                try (DataInputStream dataInputStream
                        = new DataInputStream(new FileInputStream(settings))) {
                    Global.setSpeed(dataInputStream.readInt());
                    Global.setAudioEnable(dataInputStream.readBoolean());
                    Global.statistics = Statistics.load(dataInputStream);
                    Global.pathToCompaniesFolder = dataInputStream.readUTF();
                    Global.LOGGER.setLevel(Level.parse(String.valueOf(dataInputStream.readInt())));
                    if (dataInputStream.available() > 0) {
                        int achievementsCount = dataInputStream.readInt();
                        for (int i = 0; i < achievementsCount; i++) {
                            String key = dataInputStream.readUTF();
                            String value = dataInputStream.readUTF();
                            ACHIEVEMENTS.put(key, value);
                        }
                    }
                }
            }
        } catch (IOException | IllegalArgumentException | SecurityException ex) {
            ex.printStackTrace(System.err);
        }
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
                    dataOutputStream.writeInt(speed);
                    dataOutputStream.writeBoolean(audio);
                    statistics.save(dataOutputStream);
                    dataOutputStream.writeUTF(pathToCompaniesFolder);
                    dataOutputStream.writeInt(LOGGER.getLevel().intValue());
                    dataOutputStream.writeInt(ACHIEVEMENTS.size());
                    for (Map.Entry<String, String> entry : ACHIEVEMENTS.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        dataOutputStream.writeUTF(key);
                        dataOutputStream.writeUTF(value);
                    }
                }
            }
        } catch (IOException ex) {
            Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public static void setPathToCompaniesFolder(String pathToCompaniesFolder) {
        Global.pathToCompaniesFolder = pathToCompaniesFolder;
    }

    public static String getPathToCompaniesFolder() {
        return pathToCompaniesFolder;
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR);
    }

    public static Statistics getStatistics() {
        return statistics;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    @NonNull
    public static Map<String, String> getAchievements() {
        return ACHIEVEMENTS;
    }

    public static String getAcheivementByName(String name) {
        return ACHIEVEMENTS.get(name);
    }

    public static void addAcheivement(String name, String description) {
        ACHIEVEMENTS.put(name, description);
    }

    public static boolean hasAcheivement(String name) {
        return getAcheivementByName(name) != null;
    }

    public static boolean audioEnabled() {
        return audio;
    }

    private static final String[] RANKS = {
        "Рядовой",
        "Сержант",
        "Старшина",
        "Капрал",
        "Лейтенант",
        "Капитан",
        "Майор",
        "Подполковник",
        "Полковник",
        "Генерал",
        "Военачальник",
        "Маршал"
    };

    public static String getRank(int playerSkill) {
        if (playerSkill > RANKS.length - 2) {
            return RANKS[RANKS.length - 1] + "[" + playerSkill + "]";
        }
        return RANKS[playerSkill - 1] + "[" + playerSkill + "]";
    }

    public static void setAudioEnable(boolean value) {
        audio = value;
    }

}
