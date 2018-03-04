package ru.ovcharov_alexey.tanks.v4.engine.events;

/**
 * @author Alexey
 */
public enum GameEvent {
    GAME_START,
    GAME_PAUSE,
    GAME_RESUME,
    GAME_BREAK,
    GAME_WIN,
    GAME_LOSE, 
    GAME_NEXT_LEVEL, 
    ENEMY_KILL, 
    PLAYER_SHOT, 
    ENEMY_SHOT;
}
