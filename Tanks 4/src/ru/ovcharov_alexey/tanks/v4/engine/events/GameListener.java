package ru.ovcharov_alexey.tanks.v4.engine.events;

/**
 * Слушатель, который реагирует на события начала игры, паузы и остановки игры
 * @author Алексей
 */
@FunctionalInterface
public interface GameListener {

    void actionPerformed(GameEvent eventType);
}
