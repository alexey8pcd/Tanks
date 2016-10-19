package ru.ovcharov_alexey.tanks.v4.engine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Alexey
 */
public class Statistics {

    public static Statistics empty() {
        return new Statistics(0, 0, 0);
    }

    private int gamesStarts;
    private int gamesWin;
    private int enemyKills;

    public Statistics(int gamesStarts, int gamesWin, int enemyKills) {
        this.gamesStarts = gamesStarts;
        this.gamesWin = gamesWin;
        this.enemyKills = enemyKills;
    }

    public Integer getGamesStarts() {
        return gamesStarts;
    }

    public Integer getGamesWin() {
        return gamesWin;
    }

    public Float getWinPercent() {
        if (gamesStarts == 0) {
            return 0f;
        }
        return (float) gamesWin / gamesStarts * 100;
    }

    public Integer getEnemyKills() {
        return enemyKills;
    }

    public void addStartedGame() {
        ++gamesStarts;
    }

    public void addWinGame() {
        ++gamesWin;
    }

    public void addEnemyKill() {
        ++enemyKills;
    }

    public void save(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(gamesStarts);
        dataOutputStream.writeInt(gamesWin);
        dataOutputStream.writeInt(enemyKills);
    }

    public static Statistics load(DataInputStream dataInputStream) throws IOException {
        int gamesStart = dataInputStream.readInt();
        int gamesWin = dataInputStream.readInt();
        int enemiesKill = dataInputStream.readInt();
        return new Statistics(gamesStart, gamesWin, enemiesKill);
    }
}
