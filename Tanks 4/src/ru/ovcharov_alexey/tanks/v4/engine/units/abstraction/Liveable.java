package ru.ovcharov_alexey.tanks.v4.engine.units.abstraction;

/**
 *
 * @author alex
 */
public interface Liveable extends Killable {
    
    int NOT_HEALTH = 0;
    
    int getHealth();
    
    void setHealth(int health);
    
    /**
     * Проверяет, что у заданного объекта полное здоровье
     * @return 
     */
    boolean isHealthy();

    default boolean isDamaged(){
        return !isHealthy();
    }
}
