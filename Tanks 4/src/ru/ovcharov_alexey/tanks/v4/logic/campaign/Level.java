package ru.ovcharov_alexey.tanks.v4.logic.campaign;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.units.battle.CombatUnit;

/**
 * @author Alexey
 */
public class Level {

    private String name;
    private GeometryMap map;
    private List<CombatUnit> units;

    public Level(String name) {
        this.name = name;
        this.units = new ArrayList<>();
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
        return units;
    }

    public void addUnit(CombatUnit unit) {
        this.units.add(unit);
    }

    static Level loadLevel(DataInputStream dis) throws Exception {
        String name = dis.readUTF();
        GeometryMap gm = GeometryMap.load(dis);
        Level level = new Level(name);
        level.setMap(gm);
        int unitsCount = dis.readInt();
        for (int i = 0; i < unitsCount; i++) {
            level.addUnit(CombatUnit.load(dis));
        }
        return level;
    }

    void save(DataOutputStream dos) throws IOException {
        dos.writeUTF(name);
        map.save(dos);
        dos.writeInt(units.size());
        for (CombatUnit unit : units) {
            unit.save(dos);
        }
    }

    void addUnits(List<CombatUnit> units) {
        this.units.addAll(units);
    }

}
