package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Алексей
 */
public class MainForm extends javax.swing.JFrame {

    private final int width = 768;
    private final int height = 512;
    private final Dimension screenSize 
            = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Creates new form MainForm
     *
     * @throws java.io.IOException
     */
    public MainForm() throws IOException {
        initComponents();
        setLocation();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bSinglePlayer = new javax.swing.JButton();
        bCampaign = new javax.swing.JButton();
        bMapEditor = new javax.swing.JButton();
        bCampaignEditor = new javax.swing.JButton();
        bStats = new javax.swing.JButton();
        bExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(6, 3));

        bSinglePlayer.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bSinglePlayer.setText("Одиночная игра");
        getContentPane().add(bSinglePlayer);

        bCampaign.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bCampaign.setText("Кампания");
        getContentPane().add(bCampaign);

        bMapEditor.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bMapEditor.setText("Редактор карт");
        bMapEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMapEditorActionPerformed(evt);
            }
        });
        getContentPane().add(bMapEditor);

        bCampaignEditor.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bCampaignEditor.setText("Редактор кампаний");
        bCampaignEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCampaignEditorActionPerformed(evt);
            }
        });
        getContentPane().add(bCampaignEditor);

        bStats.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bStats.setText("Статистика");
        getContentPane().add(bStats);

        bExit.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bExit.setText("Выйти");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });
        getContentPane().add(bExit);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    final void setLocation(){
        this.setLocation(screenSize.width / 2 - width / 2,
                screenSize.height / 2 - height / 2);
    }

    private void bMapEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMapEditorActionPerformed
        new MapEditor(this, true).setVisible(true);
    }//GEN-LAST:event_bMapEditorActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void bCampaignEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCampaignEditorActionPerformed
        new CampaignEditor(this, true).setVisible(true);
    }//GEN-LAST:event_bCampaignEditorActionPerformed

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
    private javax.swing.JButton bCampaign;
    private javax.swing.JButton bCampaignEditor;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bMapEditor;
    private javax.swing.JButton bSinglePlayer;
    private javax.swing.JButton bStats;
    // End of variables declaration//GEN-END:variables
}
