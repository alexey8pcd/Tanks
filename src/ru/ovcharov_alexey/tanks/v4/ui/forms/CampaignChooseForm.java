package ru.ovcharov_alexey.tanks.v4.ui.forms;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.AbstractListModel;
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
    private Campaign selectedCampaign;
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
                return Global.getOpenLevels(selectedCampaign, levels).size();
            }

            @Override
            public String getElementAt(int index) {
                return Global.getOpenLevels(selectedCampaign, levels).get(index).getName();
            }
        });
    }

    private Set<Campaign> getCampaigns(File dir) throws IOException {
        String[] campaignFiles = dir.list((File dir1, String s) -> s.endsWith(".campaign"));
        Set<Campaign> campaignsUnique = new TreeSet<>(
                (Campaign o1, Campaign o2) -> o1.getName().compareTo(o2.getName()));
        if (campaignFiles != null) {
            for (String name : campaignFiles) {
                Campaign c = Campaign.loadFromFile(dir + File.separator + name);
                campaignsUnique.add(c);
            }
        }
        return campaignsUnique;
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
            selectedCampaign = campaigns.get(selectedIndex);
            choosen = new LevelAndCampaign(lvlIdx, selectedCampaign);
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
        LoadGameForm.asyncAction(() -> {
            try {
                File[] dirs = new File[]{
                    new File(Global.getPathToCompaniesFolder()),
                    new File(System.getProperty("user.home"))
                };
                Set<Campaign> campaignsSet = new TreeSet<>(
                        (Campaign o1, Campaign o2) -> o1.getName().compareTo(o2.getName()));
                for (File dir : dirs) {
                    if (dir.exists() && dir.isDirectory()) {
                        Set<Campaign> campaigns1 = getCampaigns(dir);
                        campaignsSet.addAll(campaigns1);
                        File[] subdirs = dir.listFiles(File::isDirectory);
                        for (File subdir : subdirs) {
                            Set<Campaign> campaigns2 = getCampaigns(subdir);
                            campaignsSet.addAll(campaigns2);
                        }
                    }
                }
                this.campaigns.clear();
                this.campaigns.addAll(campaignsSet);
                listCampaign.updateUI();
            } catch (Exception ex) {
                Global.logAndShowException(ex);
            }
        });
    }
}
