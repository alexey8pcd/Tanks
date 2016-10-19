package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Алексей
 */
public class MainForm extends javax.swing.JFrame {

    public MainForm() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bSinglePlayer = new javax.swing.JButton();
        bCampaign = new javax.swing.JButton();
        bMapEditor = new javax.swing.JButton();
        bCampaignEditor = new javax.swing.JButton();
        bStats = new javax.swing.JButton();
        bSettings = new javax.swing.JButton();
        bExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        bSinglePlayer.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bSinglePlayer.setText("Одиночная игра");
        bSinglePlayer.setPreferredSize(new java.awt.Dimension(460, 80));
        bSinglePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSinglePlayerActionPerformed(evt);
            }
        });

        bCampaign.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bCampaign.setText("Кампания");
        bCampaign.setPreferredSize(new java.awt.Dimension(460, 80));

        bMapEditor.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bMapEditor.setText("Редактор карт");
        bMapEditor.setPreferredSize(new java.awt.Dimension(460, 80));
        bMapEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMapEditorActionPerformed(evt);
            }
        });

        bCampaignEditor.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bCampaignEditor.setText("Редактор кампаний");
        bCampaignEditor.setPreferredSize(new java.awt.Dimension(460, 80));
        bCampaignEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCampaignEditorActionPerformed(evt);
            }
        });

        bStats.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bStats.setText("Статистика");
        bStats.setPreferredSize(new java.awt.Dimension(460, 80));

        bSettings.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bSettings.setText("Настройки");
        bSettings.setPreferredSize(new java.awt.Dimension(460, 80));
        bSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSettingsActionPerformed(evt);
            }
        });

        bExit.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bExit.setText("Выйти");
        bExit.setPreferredSize(new java.awt.Dimension(460, 80));
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bCampaign, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addComponent(bCampaignEditor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bSettings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bMapEditor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bSinglePlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bSinglePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCampaign, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bMapEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCampaignEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bStats, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bMapEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMapEditorActionPerformed
        new MapEditorForm(this, true).setVisible(true);
    }//GEN-LAST:event_bMapEditorActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void bCampaignEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCampaignEditorActionPerformed
        new CampaignEditorForm(this, true).setVisible(true);
    }//GEN-LAST:event_bCampaignEditorActionPerformed

    private void bSinglePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSinglePlayerActionPerformed
        GameForm gameForm;
        try {
            gameForm = new GameForm(this, true);
            gameForm.singlePlayGame();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }//GEN-LAST:event_bSinglePlayerActionPerformed

    private void bSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSettingsActionPerformed
        new SettingsForm(this, true).setVisible(true);
    }//GEN-LAST:event_bSettingsActionPerformed

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
    private javax.swing.JButton bSettings;
    private javax.swing.JButton bSinglePlayer;
    private javax.swing.JButton bStats;
    // End of variables declaration//GEN-END:variables
}
