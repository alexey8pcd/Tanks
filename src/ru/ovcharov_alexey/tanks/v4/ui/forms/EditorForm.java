package ru.ovcharov_alexey.tanks.v4.ui.forms;

import java.awt.HeadlessException;
import java.io.File;
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
public class EditorForm extends javax.swing.JDialog {

    private LevelCreator levelCreator;
    private Campaign campaign;

    public EditorForm(java.awt.Frame parent, boolean modal, int width, int height) {
        super(parent, modal);
        initComponents();
        init(width, height);
        paneTop.setVisible(false);
    }

    private void init(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
    }

    private void setModel() {
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

        paneTop = new javax.swing.JPanel();
        lCampaignName = new javax.swing.JLabel();
        lLevels = new javax.swing.JLabel();
        spLevels = new javax.swing.JScrollPane();
        listLevels = new javax.swing.JList<>();
        tfCompanyName = new javax.swing.JTextField();
        paneEditLevel = new javax.swing.JPanel();
        lLevelName = new javax.swing.JLabel();
        tfLevelName = new javax.swing.JTextField();
        lMapDescription = new javax.swing.JLabel();
        bCreateMap = new javax.swing.JButton();
        bLoadMap = new javax.swing.JButton();
        lMapName = new javax.swing.JLabel();
        lBonusesCount = new javax.swing.JLabel();
        spinnerBonusesCount = new javax.swing.JSpinner();
        lEnemyUnits = new javax.swing.JLabel();
        spEnemyUnits = new javax.swing.JScrollPane();
        listUnits = new javax.swing.JList<>();
        bAddUnits = new javax.swing.JButton();
        lTypeUnit = new javax.swing.JLabel();
        cbUnitsType = new javax.swing.JComboBox<>();
        bAddLevel = new javax.swing.JButton();
        bRemoveLevel = new javax.swing.JButton();
        lUnitCount = new javax.swing.JLabel();
        spinnerUnitsCountAdd = new javax.swing.JSpinner();
        bRemoveUnits = new javax.swing.JButton();
        mbMain = new javax.swing.JMenuBar();
        menuCampaignEditor = new javax.swing.JMenu();
        miNewCampaign = new javax.swing.JMenuItem();
        miLoadCampaign = new javax.swing.JMenuItem();
        miSaveCampaign = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        miExitNow = new javax.swing.JMenuItem();
        menuMapEditor = new javax.swing.JMenu();
        mEditMap = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Редактор");
        setResizable(false);

        lCampaignName.setText("Название кампании");

        lLevels.setText("Уровни");

        spLevels.setViewportView(listLevels);

        tfCompanyName.setText("Новая кампания");

        paneEditLevel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Редактирование уровня"));

        lLevelName.setText("Название уровня");

        lMapDescription.setText("Карта");

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

        lBonusesCount.setText("Количество бонусов");

        spinnerBonusesCount.setModel(new javax.swing.SpinnerNumberModel(3, 0, 10, 1));

        lEnemyUnits.setText("Боевые единицы");

        spEnemyUnits.setViewportView(listUnits);

        bAddUnits.setText("Добавить боевые единицы");
        bAddUnits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddUnitsActionPerformed(evt);
            }
        });

        lTypeUnit.setText("Тип");

        cbUnitsType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Легкая боевая машина", "Средняя боевая машина", "Тяжелая боевая машина", "Танк", "Фугас", "Двухорудийная машина" }));

        bAddLevel.setText("Добавить уровень");
        bAddLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddLevelActionPerformed(evt);
            }
        });

        bRemoveLevel.setText("Удалить уровень");
        bRemoveLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoveLevelActionPerformed(evt);
            }
        });

        lUnitCount.setText("Количество");

        spinnerUnitsCountAdd.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        bRemoveUnits.setText("Удалить выбранные");
        bRemoveUnits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoveUnitsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneEditLevelLayout = new javax.swing.GroupLayout(paneEditLevel);
        paneEditLevel.setLayout(paneEditLevelLayout);
        paneEditLevelLayout.setHorizontalGroup(
            paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneEditLevelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bAddLevel)
                .addGap(18, 18, 18)
                .addComponent(bRemoveLevel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(paneEditLevelLayout.createSequentialGroup()
                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneEditLevelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneEditLevelLayout.createSequentialGroup()
                                .addComponent(lMapDescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lMapName, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paneEditLevelLayout.createSequentialGroup()
                                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(paneEditLevelLayout.createSequentialGroup()
                                        .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bCreateMap)
                                            .addComponent(lBonusesCount))
                                        .addGap(65, 65, 65)
                                        .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bLoadMap)
                                            .addComponent(spinnerBonusesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lEnemyUnits)
                                    .addGroup(paneEditLevelLayout.createSequentialGroup()
                                        .addComponent(lLevelName)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfLevelName, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(paneEditLevelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(spEnemyUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bAddUnits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(paneEditLevelLayout.createSequentialGroup()
                                .addComponent(lUnitCount)
                                .addGap(36, 36, 36)
                                .addComponent(spinnerUnitsCountAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bRemoveUnits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lTypeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbUnitsType, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        paneEditLevelLayout.setVerticalGroup(
            paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneEditLevelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lLevelName)
                    .addComponent(tfLevelName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lMapName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMapDescription))
                .addGap(12, 12, 12)
                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bLoadMap)
                    .addComponent(bCreateMap))
                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneEditLevelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(spinnerBonusesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneEditLevelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lBonusesCount)))
                .addGap(20, 20, 20)
                .addComponent(lEnemyUnits)
                .addGap(18, 18, 18)
                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spEnemyUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneEditLevelLayout.createSequentialGroup()
                        .addComponent(bAddUnits)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerUnitsCountAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lUnitCount))
                        .addGap(18, 18, 18)
                        .addComponent(lTypeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbUnitsType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bRemoveUnits)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneEditLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAddLevel)
                    .addComponent(bRemoveLevel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout paneTopLayout = new javax.swing.GroupLayout(paneTop);
        paneTop.setLayout(paneTopLayout);
        paneTopLayout.setHorizontalGroup(
            paneTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lCampaignName)
                .addGap(29, 29, 29)
                .addComponent(tfCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneTopLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(paneTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lLevels)
                    .addComponent(spLevels, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(paneEditLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        paneTopLayout.setVerticalGroup(
            paneTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCampaignName)
                    .addComponent(tfCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paneTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneEditLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneTopLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lLevels)
                        .addGap(18, 18, 18)
                        .addComponent(spLevels, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(paneTop, java.awt.BorderLayout.CENTER);

        menuCampaignEditor.setText("Кампания");
        menuCampaignEditor.setActionCommand("Кампания");

        miNewCampaign.setText("Новая кампания");
        miNewCampaign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewCampaignActionPerformed(evt);
            }
        });
        menuCampaignEditor.add(miNewCampaign);

        miLoadCampaign.setText("Загрузить кампанию...");
        miLoadCampaign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLoadCampaignActionPerformed(evt);
            }
        });
        menuCampaignEditor.add(miLoadCampaign);

        miSaveCampaign.setText("Сохранить кампанию...");
        miSaveCampaign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveCampaignActionPerformed(evt);
            }
        });
        menuCampaignEditor.add(miSaveCampaign);

        miExit.setText("Сохранить и выйти");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        menuCampaignEditor.add(miExit);

        miExitNow.setText("Выйти без сохранения");
        miExitNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitNowActionPerformed(evt);
            }
        });
        menuCampaignEditor.add(miExitNow);

        mbMain.add(menuCampaignEditor);

        menuMapEditor.setText("Карта");

        mEditMap.setText("Редактировать карту");
        mEditMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEditMapActionPerformed(evt);
            }
        });
        menuMapEditor.add(mEditMap);

        mbMain.add(menuMapEditor);

        setJMenuBar(mbMain);

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

    private void miSaveCampaignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveCampaignActionPerformed
        saveCampaign();
    }//GEN-LAST:event_miSaveCampaignActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        saveCampaign();
        dispose();
    }//GEN-LAST:event_miExitActionPerformed

    private void miExitNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitNowActionPerformed
        dispose();
    }//GEN-LAST:event_miExitNowActionPerformed

    private void miNewCampaignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewCampaignActionPerformed
        campaign = new Campaign();
        levelCreator = new LevelCreator();
        setModel();
        paneTop.setVisible(true);
    }//GEN-LAST:event_miNewCampaignActionPerformed

    private void miLoadCampaignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLoadCampaignActionPerformed
        loadCampaign();
    }//GEN-LAST:event_miLoadCampaignActionPerformed

    private void bRemoveLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoveLevelActionPerformed
        removeLevel();
    }//GEN-LAST:event_bRemoveLevelActionPerformed

    private void mEditMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEditMapActionPerformed
        new MapEditorForm(null, true).setVisible(true);
    }//GEN-LAST:event_mEditMapActionPerformed

    private static final javax.swing.filechooser.FileFilter CAMPAIGN_FILE_FILTER
            = new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return (f.getName().endsWith(".campaign"));
        }

        @Override
        public String getDescription() {
            return "Файлы кампаний(*.campaign)";
        }
    };

    private void loadCampaign() throws HeadlessException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(CAMPAIGN_FILE_FILTER);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String fileName = fileChooser.getSelectedFile().getCanonicalPath();
                campaign = Campaign.loadFromFile(fileName);
                levelCreator = new LevelCreator();
                setModel();
                paneTop.setVisible(true);
            } catch (IOException | RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Не удалось загрузить "
                        + "файл кампании, причина: " + ex.getMessage());
            }
        }
    }

    private void saveCampaign() throws HeadlessException {
        if (campaign != null) {
            String name = tfCompanyName.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Не задано название кампании!",
                        "Ошибка сохранения", JOptionPane.ERROR_MESSAGE);
            } else if (campaign.getLevels().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Не создано ни одного уровня!",
                        "Ошибка сохранения", JOptionPane.ERROR_MESSAGE);
            } else {
                campaign.setName(name);
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(this);
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

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddLevel;
    private javax.swing.JButton bAddUnits;
    private javax.swing.JButton bCreateMap;
    private javax.swing.JButton bLoadMap;
    private javax.swing.JButton bRemoveLevel;
    private javax.swing.JButton bRemoveUnits;
    private javax.swing.JComboBox<String> cbUnitsType;
    private javax.swing.JLabel lBonusesCount;
    private javax.swing.JLabel lCampaignName;
    private javax.swing.JLabel lEnemyUnits;
    private javax.swing.JLabel lLevelName;
    private javax.swing.JLabel lLevels;
    private javax.swing.JLabel lMapDescription;
    private javax.swing.JLabel lMapName;
    private javax.swing.JLabel lTypeUnit;
    private javax.swing.JLabel lUnitCount;
    private javax.swing.JList<String> listLevels;
    private javax.swing.JList<String> listUnits;
    private javax.swing.JMenuItem mEditMap;
    private javax.swing.JMenuBar mbMain;
    private javax.swing.JMenu menuCampaignEditor;
    private javax.swing.JMenu menuMapEditor;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miExitNow;
    private javax.swing.JMenuItem miLoadCampaign;
    private javax.swing.JMenuItem miNewCampaign;
    private javax.swing.JMenuItem miSaveCampaign;
    private javax.swing.JPanel paneEditLevel;
    private javax.swing.JPanel paneTop;
    private javax.swing.JScrollPane spEnemyUnits;
    private javax.swing.JScrollPane spLevels;
    private javax.swing.JSpinner spinnerBonusesCount;
    private javax.swing.JSpinner spinnerUnitsCountAdd;
    private javax.swing.JTextField tfCompanyName;
    private javax.swing.JTextField tfLevelName;
    // End of variables declaration//GEN-END:variables

    private void removeLevel() {
        int selectedIndex = listLevels.getSelectedIndex();
        if (selectedIndex != -1) {

            campaign.getLevels().remove(selectedIndex);
            listLevels.updateUI();
        }
    }

}
