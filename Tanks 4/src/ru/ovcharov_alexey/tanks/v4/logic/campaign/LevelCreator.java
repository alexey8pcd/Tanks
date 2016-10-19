package ru.ovcharov_alexey.tanks.v4.logic.campaign;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.factory.UnitFactory;
import ru.ovcharov_alexey.tanks.v4.logic.forms.MapEditorForm;
import ru.ovcharov_alexey.tanks.v4.persist.GeometryMapPersistance;

/**
 * @author Alexey
 */
public class LevelCreator {

    private GeometryMap map;
    private List<CombatUnit> units;
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
                CombatUnit enemyUnit = UnitFactory.createEnemyUnit(
                        UnitType.typeOf(selectedIndex + 1));
                units.add(enemyUnit);
            }
        }
    }

    public int getUnitsCount() {
        return units.size();
    }

    public String getUnitAt(int index) {
        return units.get(index).getUnitType().toString();
    }

    public void removeUnits(int[] selectedIndices) {
        if (selectedIndices.length == 0
                && units.size() - selectedIndices.length > 0) {
            return;
        }
        List<CombatUnit> rest = new ArrayList<>();
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
