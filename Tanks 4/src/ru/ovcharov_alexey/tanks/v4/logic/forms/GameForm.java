package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.Game;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameEvent;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.LevelAndCampaign;
import ru.ovcharov_alexey.tanks.v4.engine.persist.GeometryMapPersistance;

/**
 *
 * @author Алексей
 */
public class GameForm extends javax.swing.JDialog {

    private Game engine;
    private KeyAdapter keyAdapter;

    public GameForm(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();
        initGame();
    }

    private void initGame() {
        try {
            int width = (int) Global.getMapWidth();
            int height = (int) Global.getMapHeight();
            this.setSize(width + 1, height + 1);
            this.setLocationRelativeTo(null);
            engine = new Game(canvas);
            engine.addGameListener((GameEvent event) -> {
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
                            Global.getStatistics().addEnemyKill();
                    }
                }
            });
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
