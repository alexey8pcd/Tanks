package ru.ovcharov_alexey.tanks.v4.logic.forms;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.Campaign;

/**
 *
 * @author Алексей
 */
public class CampaignChooseForm extends javax.swing.JDialog {

    private List<Campaign> campaigns = new ArrayList<>();

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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCampaign = new javax.swing.JList<>();
        bChoose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Выберите кампанию");

        listCampaign.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listCampaign);

        bChoose.setText("Выбрать");
        bChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChooseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(bChoose))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bChoose))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChooseActionPerformed
        if (listCampaign.getSelectedIndex() >= 0) {
            dispose();
        }
    }//GEN-LAST:event_bChooseActionPerformed

    public Campaign getChoosenCampaign() {
        if (listCampaign.getSelectedIndex() >= 0) {
            return campaigns.get(listCampaign.getSelectedIndex());
        } else {
            return null;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChoose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listCampaign;
    // End of variables declaration//GEN-END:variables

    private void loadCampaigns() {
        setLocationRelativeTo(null);
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
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
