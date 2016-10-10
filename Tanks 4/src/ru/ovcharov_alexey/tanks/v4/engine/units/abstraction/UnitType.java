package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

/**
 *
 * @author alex
 */
public enum UnitType {

    LIGHT_COMBAT_VEHICLE(1, "Легкая боевая машина"),
    MIDDLE_COMBAT_VEHICLE(2, "Средняя боевая машина"),
    HEAVY_COMBAT_VEHICLE(3, "Тяжелая боевая машина"),
    TANK(4, "Танк"),
    FOCUSED_BLASTING(5, "Фугас"),
    DOUBLE_WEAPON_VEHICLE(6, "Двухорудийная машина");
    private final int type;
    private final String name;

    public static UnitType typeOf(int type) {
        for (UnitType value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        throw new IllegalArgumentException("Тип " + type + " не существует");
    }

    public static UnitType typeOf(String name){
        for (UnitType value : values()) {
            if(value.name.equals(name)){
                return value;
            }
        }
        throw new IllegalArgumentException("Тип с названием " + name + " не существует");
    }

    private UnitType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
    
    

}
