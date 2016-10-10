package ru.ovcharov_alexey.tanks.v4.persist;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * @author Alexey Ипользуется для загрузки и сохранения карты на диске
 */
public class GeometryMapPersistance {

    public static boolean saveMapToFile(File toSave, GeometryMap map) {
        try {
            try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(toSave))) {
                map.save(outputStream);
            }
            return true;
        } catch (Exception ex) {
            showSaveErrorMessage(ex);
        }
        return false;
    }

    

    private static void showSaveErrorMessage(Exception ex) throws HeadlessException {
        showErrorMessage(ex, "сохранении");
    }

    private static void showErrorMessage(Exception ex, String action) throws HeadlessException {
        JOptionPane.showMessageDialog(null, "Произошла ошибка при " + action
                + " карты, причина: \n" + ex,
                "Ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
    }

    private static void showLoadErrorMessage(Exception ex) throws HeadlessException {
        showErrorMessage(ex, "загрузке");
    }

    public static GeometryMap loadFromFile(File source) {
        try {
            try (DataInputStream inputStream = new DataInputStream(new FileInputStream(source))) {
                return GeometryMap.load(inputStream);
            }
        } catch (IOException ex) {
            showLoadErrorMessage(ex);
        }
        return null;
    }


}
