package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.Game;
import ru.ovcharov_alexey.tanks.v4.engine.GameMode;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.engine.events.GameListener;
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
        this.setSize(width + 4, height + 24);
        this.setLocationRelativeTo(null);
        Canvas canvas = new Canvas();
        this.getContentPane().add(canvas);
        canvas.setSize(width, height);
        engine = new Game(canvas, width, height);
        engine.addGameListener(() -> {
            if(engine.getGameMode() == GameMode.OFF){
                dispose();
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

    @Override
    public void dispose() {
        super.dispose();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
