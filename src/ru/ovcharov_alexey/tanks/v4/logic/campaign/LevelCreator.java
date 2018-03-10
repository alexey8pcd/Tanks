package ru.ovcharov_alexey.tanks.v4.logic.campaign;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.ui.forms.MapEditorForm;
import ru.ovcharov_alexey.tanks.v4.engine.persist.GeometryMapPersistance;

/**
 * @author Alexey
 */
public class LevelCreator {

    private GeometryMap map;
    private List<UnitType> units;
    private static final int MAX_UNITS_COUNT = 24;

    public LevelCreator() {
        units = new ArrayList<>();
    }

    public String createMap() {
        MapEditorForm mapEditor = new MapEditorForm(null, true);
        mapEditor.disableSaveOnClose();
        mapEditor.setVisible(true);
        this.map = mapEditor.getMap();
        return "Из редактора: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public String loadMap() {
        GeometryMapPersistance.MapData mapData = GeometryMapPersistance.loadFromFile();
        if (mapData == GeometryMapPersistance.MapData.EMPTY) {
            return "Карта не выбрана";
        } else {
            this.map = mapData.getGeometryMap();
            return mapData.getFileName();
        }
    }

    public void addUnits(int count, int selectedIndex) {
        if (units.size() + count < MAX_UNITS_COUNT) {
            for (int i = 0; i < count; i++) {
                units.add(UnitType.typeOf(selectedIndex + 1));
            }
        }
    }

    public int getUnitsCount() {
        return units.size();
    }

    public String getUnitAt(int index) {
        return units.get(index).toString();
    }

    public void removeUnits(int[] selectedIndices) {
        if (selectedIndices.length == 0
                && units.size() - selectedIndices.length > 0) {
            return;
        }
        List<UnitType> rest = new ArrayList<>();
        for (int i = 0; i < units.size(); i++) {
            if (Arrays.binarySearch(selectedIndices, i) < 0) {
                rest.add(units.get(i));
            }
        }
        units = rest;
    }

    public Level createLevel(String name, int bonusesCount) {
        Level level = new Level(name);
        level.setMap(map);
        level.setBonusesCount(bonusesCount);
        level.addUnits(units);
        return level;
    }

    public boolean isValid() {
        return map != null && !units.isEmpty();
    }

}
