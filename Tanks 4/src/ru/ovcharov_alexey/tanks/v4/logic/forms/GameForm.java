package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.Game;
import ru.ovcharov_alexey.tanks.v4.engine.GameMode;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameEvent;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameListener;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Campaign;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.LevelAndCampaign;
import ru.ovcharov_alexey.tanks.v4.persist.GeometryMapPersistance;

/**
 *
 * @author Алексей
 */
public class GameForm extends javax.swing.JDialog {

    private Game engine;

    public GameForm(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();
        initGame();
    }

    private void initGame() throws IOException {
        int width = (int) Global.getMapWidth();
        int height = (int) Global.getMapHeight();
        this.setSize(width + 1, height + 1);
        this.setLocationRelativeTo(null);
        Canvas canvas = new Canvas();
        this.getContentPane().add(canvas);
        canvas.setSize(width - 1, height - 1);
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
        this.addKeyListener(new KeyAdapter() {
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

        });
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

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
    // End of variables declaration//GEN-END:variables
}
