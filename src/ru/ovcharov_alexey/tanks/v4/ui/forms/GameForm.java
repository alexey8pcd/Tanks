package ru.ovcharov_alexey.tanks.v4.ui.forms;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.Game;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameEvent;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameListener;
import ru.ovcharov_alexey.tanks.v4.engine.media.Audio;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.LevelAndCampaign;
import ru.ovcharov_alexey.tanks.v4.engine.persist.GeometryMapPersistance;

/**
 *
 * @author Алексей
 */
public class GameForm extends javax.swing.JDialog {

    private static final Audio AUDIO = Audio.getInstance();

    private Game engine;
    private KeyAdapter keyAdapter;
    private final GameListener gameListener = (GameEvent event) -> {
        if (null != event) {
            switch (event) {
                case GAME_WIN:
                    Global.getStatistics().addWinGame();
                case GAME_LOSE:
                case GAME_BREAK:
                    dispose();
                    break;
                case GAME_START:
                    Global.getStatistics().addStartedGame();
                    break;
                case ENEMY_KILL:
                    AUDIO.playSound("flame.wav", Audio.Loudness.MEDIUM);
                    Global.getStatistics().addEnemyKill();
                    break;
                case PLAYER_SHOT:
                    AUDIO.playSound("explosion.wav", Audio.Loudness.LOW);
                    break;
                case PLAYER_HIT_ENEMY:
                    AUDIO.playSound("shot.wav", Audio.Loudness.MEDIUM);
                    break;
                case ACHEIVEMENT:
                    AUDIO.playSound("acheivement.wav", Audio.Loudness.HIGH);
                    break;
            }
        }
    };

    public GameForm(java.awt.Frame parent, boolean modal, int width, int height) throws IOException {
        super(parent, modal);
        initComponents();
        initGame(width, height);
    }

    private void initGame(int width, int height) {
        try {
            setSize(width, height);
            this.setLocationRelativeTo(null);
            engine = new Game(canvas, width, height);
            engine.addGameListener(gameListener);
            keyAdapter = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                    engine.pressKey(e);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    engine.releaseKey(e);
                }

            };
            this.addKeyListener(keyAdapter);
            canvas.addKeyListener(keyAdapter);
        } catch (Exception e) {
            Global.logAndShowException(e);
        }
    }

    public void singlePlayGame() {
        GeometryMapPersistance.MapData mapData = GeometryMapPersistance.loadFromFile();
        if (mapData != GeometryMapPersistance.MapData.EMPTY) {
            try {
                engine.initRandomGame(mapData.getGeometryMap());
                engine.start();
                this.setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas = new java.awt.Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        getContentPane().add(canvas, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void campaign(LevelAndCampaign levelAndCampaign) {
        try {
            engine.initGame(levelAndCampaign);
            engine.start();
            this.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas;
    // End of variables declaration//GEN-END:variables
}
