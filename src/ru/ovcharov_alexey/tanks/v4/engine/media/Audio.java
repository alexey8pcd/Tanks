package ru.ovcharov_alexey.tanks.v4.engine.media;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.util.IOUtils;

/**
 * @author Alexey
 */
public class Audio {

    private static final Map<String, byte[]> CACHE = new ConcurrentHashMap<>();
    private static final String[] CLIPS = {
        "explosion.wav",
        "shot.wav",
        "ricochet.wav",
        "flame.wav",
        "acheivement.wav"
    };

    static {
        for (String clip : CLIPS) {
            try (InputStream is = Audio.class.getResourceAsStream("/sounds/" + clip)) {
                byte[] bs = IOUtils.toByteArray(is);
                CACHE.put(clip, bs);
            } catch (IOException ex) {
                Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    private Audio() {
    }
    
    public enum Loudness {
        LOW(20f * (float) Math.log10(0.16)),
        MEDIUM(20f * (float) Math.log10(0.32)),
        HIGH(20f * (float) Math.log10(1));
        private final float loudValue;

        private Loudness(float loudValue) {
            this.loudValue = loudValue;
        }

        
        private float getFloat() {
            return loudValue;
        }
    }

    public static Audio getInstance() {

        return new Audio();
    }

    public void playSound(String resourceName, Loudness loudness) {
        if (Global.audioEnabled()) {
            byte[] bytes = CACHE.get(resourceName);
            if (bytes != null) {
                try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                        new ByteArrayInputStream(bytes))) {
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    Control control = clip.getControl(FloatControl.Type.MASTER_GAIN);
                    if(control instanceof FloatControl){
                        ((FloatControl) control).setValue(loudness.getFloat());
                    }
                    clip.start();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                    Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
                }
            }

        }
    }

}
