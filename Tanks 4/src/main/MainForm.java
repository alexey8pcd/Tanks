package main;

import geometry.GeometryMap;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Алексей
 */
public class MainForm extends javax.swing.JFrame {

    private Engine engine;
    private final int width = 768;
    private final int height = 512;
    private final int blockSize = 8;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Creates new form MainForm
     * @throws java.io.IOException
     */
    public MainForm() throws IOException {
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
            
            private static final int MOUSE_LEFT_BUTTON = MouseEvent.BUTTON1;
            private static final int MOUSE_MIDDLE_BUTTON = MouseEvent.BUTTON2;
            private static final int MOUSE_RIGHT_BUTTON = MouseEvent.BUTTON3;
            
            @Override
            public void mousePressed(MouseEvent evt) {
                super.mousePressed(evt);
                if (evt.getButton() == MOUSE_LEFT_BUTTON) {
                    engine.getMap().setTile(evt.getX(), evt.getY(),
                            GeometryMap.Material.BRICK);
                } else if (evt.getButton() == MOUSE_MIDDLE_BUTTON) {
                    engine.getMap().setTile(evt.getX(), evt.getY(),
                            GeometryMap.Material.WOOD);
                } else if (evt.getButton() == MOUSE_RIGHT_BUTTON) {
                    engine.getMap().setTile(evt.getX(), evt.getY(),
                            GeometryMap.Material.WATER);
                }
                if (evt.getClickCount() == 2) {
                    if (evt.getButton() == MOUSE_LEFT_BUTTON) {
                        engine.getMap().setTile(evt.getX(), evt.getY(),
                                GeometryMap.Material.ARMOR);
                    } else if (evt.getButton() == MOUSE_RIGHT_BUTTON) {
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
            try {
                new MainForm().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
