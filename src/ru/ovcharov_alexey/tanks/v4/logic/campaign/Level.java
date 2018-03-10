package ru.ovcharov_alexey.tanks.v4.logic.campaign;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.units.abstraction.UnitType;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;
import ru.ovcharov_alexey.tanks.v4.engine.units.factory.UnitFactory;

/**
 * @author Alexey
 */
public class Level {

    private String name;
    private GeometryMap map;
    private final List<UnitType> units = new ArrayList<>();
    private int bonusesCount;

    public Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeometryMap getMap() {
        return map;
    }

    public void setMap(GeometryMap map) {
        this.map = map;
    }

    public List<CombatUnit> getUnits() {
        List<CombatUnit> enemies = new ArrayList<>(units.size());
        for (UnitType unit : units) {
            enemies.add(UnitFactory.createEnemyUnit(unit));
        }
        return enemies;
    }

    public void addUnit(UnitType unit) {
        this.units.add(unit);
    }

    public void setBonusesCount(int bonusesCount) {
        this.bonusesCount = bonusesCount;
    }

    static Level loadLevel(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        GeometryMap gm = GeometryMap.load(dis);
        Level level = new Level(name);
        level.setMap(gm);
        int bonusesCount = dis.readInt();
        level.setBonusesCount(bonusesCount);
        int unitsCount = dis.readInt();
        for (int i = 0; i < unitsCount; i++) {
            level.addUnit(UnitType.typeOf(dis.readInt()));
        }
        return level;
    }

    void save(DataOutputStream dos) throws IOException {
        dos.writeUTF(name);
        map.save(dos);
        dos.writeInt(bonusesCount);
        dos.writeInt(units.size());
        for (UnitType unit : units) {
            dos.writeInt(unit.getType());
        }
    }

    void addUnits(List<UnitType> units) {
        this.units.addAll(units);
    }

    public int getBonusesCount() {
        return bonusesCount;
    }

}
