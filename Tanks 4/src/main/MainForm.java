package main;

import geometry.GeometryMap;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import units.battle.CombatUnit;

/**
 *
 * @author Алексей
 */
public class MainForm extends javax.swing.JFrame {

    private Engine engine;
    private int width = 768;
    private int height = 512;
    private int blockSize = 8;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        GeometryMap map = GeometryMap.newInstance(blockSize,
                height / blockSize, width / blockSize);
        map.setTileAt(20, 5, GeometryMap.Material.BRICK);
        engine = new Engine(width, height, 60, map);
        engine.addKey(KeyEvent.VK_LEFT);
        engine.addKey(KeyEvent.VK_RIGHT);
        engine.addKey(KeyEvent.VK_UP);
        engine.addKey(KeyEvent.VK_DOWN);
        engine.addKey(KeyEvent.VK_SPACE);
        engine.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                super.mousePressed(evt);
                if (evt.getButton() == 1) {
                    engine.getMap().setTile(evt.getX(), evt.getY(),
                            GeometryMap.Material.BRICK);
                } else if (evt.getButton() == 2) {
                    engine.getMap().setTile(evt.getX(), evt.getY(),
                            GeometryMap.Material.WOOD);
                } else if (evt.getButton() == 3) {
                    engine.getMap().setTile(evt.getX(), evt.getY(),
                            GeometryMap.Material.WATER);
                }
                if (evt.getClickCount() == 2) {
                    if (evt.getButton() == 1) {
                        engine.getMap().setTile(evt.getX(), evt.getY(),
                                GeometryMap.Material.ARMOR);
                    } else if (evt.getButton() == 3) {
                        engine.getMap().setTile(evt.getX(), evt.getY(),
                                GeometryMap.Material.ICE);
                    }
                }
            }
        });
//        engine.addMouseMotionListener(new MouseMotionAdapter() {
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                super.mouseMoved(e);
//                engine.setMouseCursor(e.getX(), e.getY(), blockSize, Color.BLACK);
//            }
//
//        });
        this.setSize(width, height + 32);

        this.setLocation(screenSize.width / 2 - width / 2,
                screenSize.height / 2 - height / 2);
        this.add(engine);
        engine.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
