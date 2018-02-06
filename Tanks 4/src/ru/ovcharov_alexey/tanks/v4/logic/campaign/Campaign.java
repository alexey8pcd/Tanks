package ru.ovcharov_alexey.tanks.v4.logic.campaign;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey
 */
public class Campaign {

    private final List<Level> levels;
    private String name;

    public Campaign(String name) {
        levels = new ArrayList<>();
        this.name = name;
    }
    public Campaign(){
        levels = new ArrayList<>();
    }

    public void addLevel(Level level) {
        this.levels.add(level);
    }

    public void removeLevel(Level level) {
        this.levels.remove(level);
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void save(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.exists()) {
            try (DataOutputStream dos
                    = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeUTF(name);
                dos.writeInt(levels.size());
                for (Level level : levels) {
                    level.save(dos);
                }
            }
        } else {
            throw new IOException("Не могу создать Файл "
                    + file + ", возможно нет прав");
        }
    }

    public static Campaign loadFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            String name = dis.readUTF();
            int levelsCount = dis.readInt();
            Campaign campaign = new Campaign(name);
            for (int i = 0; i < levelsCount; i++) {
                campaign.addLevel(Level.loadLevel(dis));
            }
            return campaign;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
