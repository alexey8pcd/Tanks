package ru.ovcharov_alexey.tanks.v4.ui.forms;

import java.io.IOException;
import javax.swing.JFileChooser;
import ru.ovcharov_alexey.tanks.v4.engine.Global;

/**
 *
 * @author Алексей
 */
public class SettingsForm extends javax.swing.JDialog {

    public SettingsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        setLocationRelativeTo(null);
        sliderGameSpeed.setValue((int) Global.getSpeed());
        tfCampaignFolderPath.setText(Global.getPathToCompaniesFolder());
        cbFullScreen.setSelected(Global.isFullScreen());
        if (Global.isLoggerEnabled()) {
            if (Global.isLoggingOnlyErrors()) {
                rbLogOnlyCriticalErrors.setSelected(true);
            } else {
                rbLogAllMessages.setSelected(true);
            }
        } else {
            rbNoLog.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        sliderGameSpeed = new javax.swing.JSlider();
        bApply = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfCampaignFolderPath = new javax.swing.JTextField();
        bFindCampaignFolder = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rbNoLog = new javax.swing.JRadioButton();
        rbLogOnlyCriticalErrors = new javax.swing.JRadioButton();
        rbLogAllMessages = new javax.swing.JRadioButton();
        cbFullScreen = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Настройки");

        jLabel2.setText("Скорость игры");

        bApply.setText("Применить");
        bApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApplyActionPerformed(evt);
            }
        });

        jLabel3.setText("Путь к папке, где лежат файлы кампаний:");

        bFindCampaignFolder.setText("Найти...");
        bFindCampaignFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFindCampaignFolderActionPerformed(evt);
            }
        });

        jLabel4.setText("<html>Записывать информацию о игре в журнал<br> отладки(позволяет искать ошибки):");

        buttonGroup1.add(rbNoLog);
        rbNoLog.setText("Нет");
        rbNoLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNoLogActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbLogOnlyCriticalErrors);
        rbLogOnlyCriticalErrors.setText("Только критичные ошибки");
        rbLogOnlyCriticalErrors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLogOnlyCriticalErrorsActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbLogAllMessages);
        rbLogAllMessages.setSelected(true);
        rbLogAllMessages.setText("Полная информация");
        rbLogAllMessages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLogAllMessagesActionPerformed(evt);
            }
        });

        cbFullScreen.setSelected(true);
        cbFullScreen.setText("Полный экран");
        cbFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFullScreenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCampaignFolderPath)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bApply))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bFindCampaignFolder))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbFullScreen)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(44, 44, 44)
                                .addComponent(sliderGameSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbNoLog)
                                .addGap(34, 34, 34)
                                .addComponent(rbLogOnlyCriticalErrors)
                                .addGap(18, 18, 18)
                                .addComponent(rbLogAllMessages)))
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(cbFullScreen)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderGameSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(bFindCampaignFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfCampaignFolderPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNoLog)
                    .addComponent(rbLogOnlyCriticalErrors)
                    .addComponent(rbLogAllMessages))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(bApply)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApplyActionPerformed
        Global.setSpeed(sliderGameSpeed.getValue());
        Global.setPathToCompaniesFolder(tfCampaignFolderPath.getText());
        dispose();
    }//GEN-LAST:event_bApplyActionPerformed

    private void bFindCampaignFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFindCampaignFolderActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                tfCampaignFolderPath.setText(chooser.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                tfCampaignFolderPath.setText(ex.toString());
            }
        }
    }//GEN-LAST:event_bFindCampaignFolderActionPerformed

    private void rbNoLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNoLogActionPerformed
        Global.disableLogger();
    }//GEN-LAST:event_rbNoLogActionPerformed

    private void rbLogOnlyCriticalErrorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLogOnlyCriticalErrorsActionPerformed
        Global.enableLogErrors();
    }//GEN-LAST:event_rbLogOnlyCriticalErrorsActionPerformed

    private void rbLogAllMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLogAllMessagesActionPerformed
        Global.enableLogAllMessages();
    }//GEN-LAST:event_rbLogAllMessagesActionPerformed

    private void cbFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFullScreenActionPerformed
        Global.setFullScreen(cbFullScreen.isSelected());
    }//GEN-LAST:event_cbFullScreenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bApply;
    private javax.swing.JButton bFindCampaignFolder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbFullScreen;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton rbLogAllMessages;
    private javax.swing.JRadioButton rbLogOnlyCriticalErrors;
    private javax.swing.JRadioButton rbNoLog;
    private javax.swing.JSlider sliderGameSpeed;
    private javax.swing.JTextField tfCampaignFolderPath;
    // End of variables declaration//GEN-END:variables

}
