package persist;

import geometry.GeometryMap;
import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import main.MapEditor;

/**
 * @author Alexey Ипользуется для загрузки и сохранения карты на диске
 */
public abstract class GeometryMapPersistance {

    public final static boolean saveMapToFile(File toSave, MapEditor mapEditor) {
        GeometryMap map = mapEditor.getCurrentMap();
        try {
            try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(toSave))) {
                saveMap(outputStream, map);
            }
            return true;
        } catch (Exception ex) {
            showSaveErrorMessage(ex);
        }
        return false;
    }

    private static void saveMap(DataOutputStream outputStream, GeometryMap map) throws IOException {
        outputStream.writeInt(map.getTileSize());
        outputStream.writeInt(map.getRowsCount());
        outputStream.writeInt(map.getColumnsCount());
        for (int row = 0; row < map.getRowsCount(); row++) {
            for (int column = 0; column < map.getColumnsCount(); column++) {
                outputStream.writeByte(map.getTileAt(row, column).getCode());
            }
        }
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
                return loadMap(inputStream);
            }
        } catch (IOException ex) {
            showLoadErrorMessage(ex);
        }
        return null;
    }

    private static GeometryMap loadMap(DataInputStream inputStream) throws IOException {
        int tileSize = inputStream.readInt();
        int rowCount = inputStream.readInt();
        int columnCount = inputStream.readInt();
        GeometryMap map = GeometryMap.newInstance(tileSize, rowCount, columnCount);
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                map.setTileAt(row, column, GeometryMap.Material.getMaterial(inputStream.readByte()));
            }
        }
        return map;
    }

}
