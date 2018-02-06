package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Campaign;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Level;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.LevelAndCampaign;

/**
 *
 * @author Алексей
 */
public class CampaignChooseForm extends javax.swing.JDialog {

    private List<Campaign> campaigns = new ArrayList<>();
    private List<Level> levels = new ArrayList<>();
    private LevelAndCampaign choosen;

    public CampaignChooseForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadCampaigns();
        listCampaign.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return campaigns.size();
            }

            @Override
            public String getElementAt(int index) {
                return campaigns.get(index).getName();
            }
        });
        this.listStartLevel.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return levels.size();
            }

            @Override
            public String getElementAt(int index) {
                return levels.get(index).getName();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCampaign = new javax.swing.JList<>();
        bChoose = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStartLevel = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Выбрать кампанию");

        jLabel1.setText("Выберите кампанию");

        listCampaign.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listCampaign.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listCampaignValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listCampaign);

        bChoose.setText("Выбрать");
        bChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChooseActionPerformed(evt);
            }
        });

        listStartLevel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listStartLevel);

        jLabel2.setText("Стартовый уровень");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bChoose)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bChoose))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChooseActionPerformed
        int selectedIndex = listCampaign.getSelectedIndex();
        int lvlIdx = listStartLevel.getSelectedIndex();
        if (selectedIndex >= 0 && listStartLevel.getModel().getSize() > 0) {
            if (lvlIdx < 0) {
                lvlIdx = 0;
            }
            choosen = new LevelAndCampaign(lvlIdx, campaigns.get(selectedIndex));
            dispose();
        }
    }//GEN-LAST:event_bChooseActionPerformed

    private void listCampaignValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listCampaignValueChanged
        if (listCampaign.getSelectedIndex() >= 0) {
            levels = campaigns.get(listCampaign.getSelectedIndex()).getLevels();
            listStartLevel.updateUI();
        }
    }//GEN-LAST:event_listCampaignValueChanged

    public LevelAndCampaign getChoosenCampaign() {
        return choosen;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChoose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listCampaign;
    private javax.swing.JList<String> listStartLevel;
    // End of variables declaration//GEN-END:variables

    private void loadCampaigns() {
        setLocationRelativeTo(null);
        try {
            LoadGameForm.asyncAction(() -> {
                try {
                    File dir = new File(Global.getPathToCompaniesFolder());
                    if (dir.exists() && dir.isDirectory()) {
                        String[] campaignFiles = dir.list((File dir1, String s)
                                -> s.endsWith(".campaign"));
                        for (String name : campaignFiles) {
                            Campaign c = Campaign.loadFromFile(dir + File.separator + name);
                            campaigns.add(c);
                        }
                        listCampaign.updateUI();
                    }
                } catch (Exception ex) {
                    Global.logAndShowException(ex);
                }
            }).join();
        } catch (InterruptedException ex) {

        }

    }
}
