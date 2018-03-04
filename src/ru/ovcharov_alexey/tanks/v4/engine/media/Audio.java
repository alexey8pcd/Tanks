package ru.ovcharov_alexey.tanks.v4.engine.media;

import java.io.IOException;
import java.util.logging.Level;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import ru.ovcharov_alexey.tanks.v4.engine.Global;

/**
 * @author Alexey
 */
public class Audio {

    public static void playSound(String resourceName) {
        if (Global.audioEnabled()) {
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    Audio.class.getResourceAsStream(resourceName))) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

}
