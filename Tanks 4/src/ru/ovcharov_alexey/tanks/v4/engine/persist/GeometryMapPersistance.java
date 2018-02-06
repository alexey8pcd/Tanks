package ru.ovcharov_alexey.tanks.v4.engine.persist;

import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Alexey Ипользуется для загрузки и сохранения карты на диске
 */
public class GeometryMapPersistance {

    public static boolean saveMapToFile(GeometryMap map) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Сохранить карту");
            chooser.setSelectedFile(new File("map.dat"));
            chooser.setFileFilter(DAT_FILE_FILTER);
            int result = chooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                try (DataOutputStream outputStream = 
                        new DataOutputStream(new FileOutputStream(selectedFile))) {
                    map.save(outputStream);
                }
                JOptionPane.showMessageDialog(null, "Карта сохранена успешно",
                        "Информация", JOptionPane.INFORMATION_MESSAGE);
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

    public static MapData loadFromFile() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Выбрать карту");

            chooser.setFileFilter(DAT_FILE_FILTER);
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String canonicalPath = selectedFile.getCanonicalPath();
                try (DataInputStream inputStream
                        = new DataInputStream(new FileInputStream(selectedFile))) {
                    GeometryMap geometryMap = GeometryMap.load(inputStream);
                    return new MapData(geometryMap, canonicalPath);
                }
            }
        } catch (Exception ex) {
            showLoadErrorMessage(ex);
        }
        return MapData.EMPTY;
    }
    private static final javax.swing.filechooser.FileFilter DAT_FILE_FILTER
            = new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return (f.getName().endsWith(".dat"));
        }

        @Override
        public String getDescription() {
            return "Файлы карт(*.dat)";
        }
    };

    public static class MapData {

        public static final MapData EMPTY = new MapData(null, null);

        private final GeometryMap geometryMap;
        private final String fileName;

        public MapData(GeometryMap geometryMap, String fileName) {
            this.geometryMap = geometryMap;
            this.fileName = fileName;
        }

        public GeometryMap getGeometryMap() {
            return geometryMap;
        }

        public String getFileName() {
            return fileName;
        }

    }

}
