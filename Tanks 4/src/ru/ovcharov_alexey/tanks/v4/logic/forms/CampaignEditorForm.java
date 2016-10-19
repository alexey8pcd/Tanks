package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.AbstractListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Campaign;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.LevelCreator;

/**
 *
 * @author Алексей
 */
public class CampaignEditorForm extends javax.swing.JDialog {

    private LevelCreator levelCreator;
    private Campaign campaign;

    public CampaignEditorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    private void init() {
        setLocationRelativeTo(null);
        campaign = new Campaign();
        levelCreator = new LevelCreator();
        listLevels.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return campaign.getLevels().size();
            }

            @Override
            public String getElementAt(int index) {
                return campaign.getLevels().get(index).getName();
            }
        });
        listUnits.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return levelCreator.getUnitsCount();
            }

            @Override
            public String getElementAt(int index) {
                return levelCreator.getUnitAt(index);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfCompanyName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listLevels = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        bSaveCompany = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfLevelName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bCreateMap = new javax.swing.JButton();
        bLoadMap = new javax.swing.JButton();
        lMapName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spinnerBonusesCount = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listUnits = new javax.swing.JList<>();
        bAddUnits = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbUnitsType = new javax.swing.JComboBox<>();
        bAddLevel = new javax.swing.JButton();
        bRemoveLevel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        spinnerUnitsCountAdd = new javax.swing.JSpinner();
        bRemoveUnits = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbMoveType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Название кампании");

        tfCompanyName.setText("Новая кампания");

        jScrollPane1.setViewportView(listLevels);

        jLabel2.setText("Уровни");

        bSaveCompany.setText("Сохранить кампанию");
        bSaveCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveCompanyActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Редактирование уровня"));

        jLabel3.setText("Название уровня");

        jLabel4.setText("Карта");

        bCreateMap.setText("Создать карту");
        bCreateMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateMapActionPerformed(evt);
            }
        });

        bLoadMap.setText("Загрузить карту");
        bLoadMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadMapActionPerformed(evt);
            }
        });

        lMapName.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Количество бонусов");

        spinnerBonusesCount.setModel(new javax.swing.SpinnerNumberModel(3, 0, 10, 1));

        jLabel7.setText("Боевые единицы");

        jScrollPane2.setViewportView(listUnits);

        bAddUnits.setText("Добавить боевые единицы");
        bAddUnits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddUnitsActionPerformed(evt);
            }
        });

        jLabel8.setText("Тип");

        cbUnitsType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Легкая боевая машина", "Средняя боевая машина", "Тяжелая боевая машина", "Танк", "Фугас", "Двухорудийная машина" }));

        bAddLevel.setText("Добавить уровень");
        bAddLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddLevelActionPerformed(evt);
            }
        });

        bRemoveLevel.setText("Удалить уровень");

        jLabel5.setText("Количество");

        spinnerUnitsCountAdd.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        bRemoveUnits.setText("Удалить выбранные");
        bRemoveUnits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoveUnitsActionPerformed(evt);
            }
        });

        jLabel9.setText("Способ движения");

        cbMoveType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Случайный", "Улучшенный", "Поиск цели" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bAddLevel)
                .addGap(323, 323, 323))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfLevelName, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lMapName, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bCreateMap)
                                            .addComponent(jLabel6))
                                        .addGap(65, 65, 65)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bLoadMap)
                                            .addComponent(spinnerBonusesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(174, 174, 174)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbUnitsType, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(bRemoveLevel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(spinnerUnitsCountAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bAddUnits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bRemoveUnits, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(cbMoveType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfLevelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lMapName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bLoadMap)
                    .addComponent(bCreateMap))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(spinnerBonusesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(cbUnitsType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerUnitsCountAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMoveType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(bAddUnits)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bRemoveUnits)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAddLevel)
                    .addComponent(bRemoveLevel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(bSaveCompany))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(bSaveCompany))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCreateMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateMapActionPerformed
        String mapName = levelCreator.createMap();
        this.lMapName.setText(mapName);
    }//GEN-LAST:event_bCreateMapActionPerformed

    private void bLoadMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadMapActionPerformed
        String mapName = levelCreator.loadMap();
        this.lMapName.setText(mapName);
    }//GEN-LAST:event_bLoadMapActionPerformed

    private void bAddUnitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddUnitsActionPerformed
        levelCreator.addUnits((int) spinnerUnitsCountAdd.getValue(),
                cbUnitsType.getSelectedIndex());
        listUnits.updateUI();
    }//GEN-LAST:event_bAddUnitsActionPerformed

    private void bRemoveUnitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoveUnitsActionPerformed
        levelCreator.removeUnits(listUnits.getSelectedIndices());
        listUnits.updateUI();
    }//GEN-LAST:event_bRemoveUnitsActionPerformed

    private void bAddLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddLevelActionPerformed
        if (!tfLevelName.getText().isEmpty() && levelCreator.isValid()) {
            campaign.addLevel(levelCreator.createLevel(tfLevelName.getText(),
                    (int) spinnerBonusesCount.getValue()));
            listLevels.updateUI();
        }
    }//GEN-LAST:event_bAddLevelActionPerformed

    private void bSaveCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveCompanyActionPerformed
        if (!tfCompanyName.getText().isEmpty() && campaign.getLevels().size() > 0) {
            campaign.setName(tfCompanyName.getText());
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String fileName = fileChooser.getSelectedFile().getCanonicalPath();
                    campaign.save(fileName + ".campaign");
                    JOptionPane.showMessageDialog(null, "Кампания сохранена");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Не удалось сохранить "
                            + "файл кампании, причина: " + ex.getMessage());
                }

            }
        }
    }//GEN-LAST:event_bSaveCompanyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddLevel;
    private javax.swing.JButton bAddUnits;
    private javax.swing.JButton bCreateMap;
    private javax.swing.JButton bLoadMap;
    private javax.swing.JButton bRemoveLevel;
    private javax.swing.JButton bRemoveUnits;
    private javax.swing.JButton bSaveCompany;
    private javax.swing.JComboBox<String> cbMoveType;
    private javax.swing.JComboBox<String> cbUnitsType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lMapName;
    private javax.swing.JList<String> listLevels;
    private javax.swing.JList<String> listUnits;
    private javax.swing.JSpinner spinnerBonusesCount;
    private javax.swing.JSpinner spinnerUnitsCountAdd;
    private javax.swing.JTextField tfCompanyName;
    private javax.swing.JTextField tfLevelName;
    // End of variables declaration//GEN-END:variables

}
