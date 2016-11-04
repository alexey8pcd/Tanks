package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.logging.Level;
import ru.ovcharov_alexey.tanks.v4.engine.Global;

/**
 *
 * @author Алексей
 */
public class LoadGameForm extends javax.swing.JDialog {

    public static Thread asyncAction(Runnable runnable) {
        LoadGameForm loadGameForm = new LoadGameForm(null, true);
        loadGameForm.setLocationRelativeTo(null);
        Thread thread = new Thread(() -> {
            try {
                runnable.run();
            } finally {
                loadGameForm.dispose();
            }
        });
        thread.start();
        loadGameForm.setVisible(true);
        return thread;
    }

    public LoadGameForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 32)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html><center>Выполняется загрузка, <br>пожалуйста подождите...");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jLabel1.setPreferredSize(new java.awt.Dimension(400, 200));
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
