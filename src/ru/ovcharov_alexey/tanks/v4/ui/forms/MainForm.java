package ru.ovcharov_alexey.tanks.v4.ui.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.Global;
import ru.ovcharov_alexey.tanks.v4.logic.campaign.LevelAndCampaign;

/**
 *
 * @author Алексей
 */
public class MainForm extends javax.swing.JFrame {

    private static final String HELP_MESSAGE = "<html>Для перемещения танка игрока используйте<br>"
            + "клавиши курсора, для выстрела - пробел.<br> Для приостановки игры - клавишу Esc<br>"
            + "Автор: Алексей Овчаров, 2016. <br>Сведения об ошибках и недочетах<br>"
            + "отправлять на почту: alexey8rus@mail.ru";

    private static Image backImg;
    private Color defaultButtonBackgroundColor;

    static {
        try {
            backImg = ImageIO.read(Global.class.getResourceAsStream("/images/screens/background.png"));
        } catch (IOException ex) {
            Global.getLogger().log(Level.SEVERE, ex.getMessage());
        }
    }

    public MainForm() throws IOException {
        preInit();
        initComponents();
        init();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pBack.getGraphics().drawImage(backImg, 0, 0, getWidth(), getHeight(), null);
        pMain.updateUI();
    }

    private void init() {
        setLocationRelativeTo(null);
        Enumeration<AbstractButton> elements = buttonGroup1.getElements();
        List<AbstractButton> buttons = new ArrayList<>();
        while (elements.hasMoreElements()) {
            final AbstractButton button = elements.nextElement();
            buttons.add(button);
        }
        AbstractButton b0 = buttons.get(0);
        defaultButtonBackgroundColor = b0.getBackground();
        Map<AbstractButton, Integer> indexes = new ConcurrentHashMap<>();
        for (int i = 0; i < buttons.size(); i++) {
            AbstractButton owner = buttons.get(i);
            indexes.put(owner, i);
            ButtonListener buttonListener = new ButtonListener(owner, i, buttons);
            owner.addKeyListener(buttonListener);
            owner.addFocusListener(buttonListener);
            owner.addMouseListener(buttonListener);
        }
    }

    private void preInit() {
        if (Global.isFullScreen()) {
            this.setUndecorated(true);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            this.setUndecorated(false);
        }
    }

    private class ButtonListener extends KeyAdapter implements FocusListener, MouseListener {

        private final List<AbstractButton> buttons;
        private final int index;
        private final AbstractButton owner;

        private ButtonListener(AbstractButton owner, int index, List<AbstractButton> buttons) {
            this.owner = owner;
            this.index = index;
            this.buttons = buttons;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            owner.setSelected(false);
            owner.setBackground(defaultButtonBackgroundColor);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for (AbstractButton button : buttons) {
                button.setSelected(false);
                button.setBackground(defaultButtonBackgroundColor);
            }
            owner.requestFocus();
            owner.setSelected(true);
            owner.setBackground(Color.BLUE);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            owner.setSelected(false);
            owner.setBackground(defaultButtonBackgroundColor);
        }

        @Override
        public void focusGained(FocusEvent e) {
            owner.setSelected(true);
            owner.setBackground(Color.BLUE);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    int prevIndex = index == 0 ? buttons.size() - 1 : index - 1;
                    AbstractButton prev = buttons.get(prevIndex);
                    prev.setSelected(true);
                    prev.setBackground(Color.BLUE);
                    prev.requestFocus();
                    owner.setSelected(false);
                    owner.setBackground(defaultButtonBackgroundColor);
                    break;
                case KeyEvent.VK_DOWN:
                    int nextIndex = index == buttons.size() - 1 ? 0 : index + 1;
                    AbstractButton next = buttons.get(nextIndex);
                    next.setSelected(true);
                    next.setBackground(Color.BLUE);
                    next.requestFocus();
                    owner.setSelected(false);
                    owner.setBackground(defaultButtonBackgroundColor);
                    break;
                case KeyEvent.VK_ENTER:
                    for (ActionListener listener : owner.getActionListeners()) {
                        listener.actionPerformed(new ActionEvent(owner, ActionEvent.ACTION_PERFORMED, "action"));
                    }
                    break;
                default:
                    break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        pBack = new javax.swing.JPanel();
        pMain = new javax.swing.JPanel();
        bSinglePlayer = new javax.swing.JButton();
        bCampaign = new javax.swing.JButton();
        bAchievements = new javax.swing.JButton();
        bCampaignEditor = new javax.swing.JButton();
        bStats = new javax.swing.JButton();
        bSettings = new javax.swing.JButton();
        bHelp = new javax.swing.JButton();
        bExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pBack.setLayout(new java.awt.GridBagLayout());

        pMain.setBackground(new java.awt.Color(0,0,0,255));
        pMain.setPreferredSize(new java.awt.Dimension(400, 710));
        pMain.setLayout(new java.awt.GridLayout(8, 1, 0, 10));

        bSinglePlayer.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bSinglePlayer.setText("Одиночная игра");
        buttonGroup1.add(bSinglePlayer);
        bSinglePlayer.setPreferredSize(new java.awt.Dimension(460, 80));
        bSinglePlayer.setSelected(true);
        bSinglePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSinglePlayerActionPerformed(evt);
            }
        });
        pMain.add(bSinglePlayer);

        bCampaign.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bCampaign.setText("Кампания");
        buttonGroup1.add(bCampaign);
        bCampaign.setPreferredSize(new java.awt.Dimension(460, 80));
        bCampaign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCampaignActionPerformed(evt);
            }
        });
        pMain.add(bCampaign);

        bAchievements.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bAchievements.setText("Достижения");
        buttonGroup1.add(bAchievements);
        bAchievements.setPreferredSize(new java.awt.Dimension(460, 80));
        bAchievements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAchievementsActionPerformed(evt);
            }
        });
        pMain.add(bAchievements);

        bCampaignEditor.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bCampaignEditor.setText("Редактор");
        buttonGroup1.add(bCampaignEditor);
        bCampaignEditor.setPreferredSize(new java.awt.Dimension(460, 80));
        bCampaignEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCampaignEditorActionPerformed(evt);
            }
        });
        pMain.add(bCampaignEditor);

        bStats.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bStats.setText("Статистика");
        buttonGroup1.add(bStats);
        bStats.setPreferredSize(new java.awt.Dimension(460, 80));
        bStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStatsActionPerformed(evt);
            }
        });
        pMain.add(bStats);

        bSettings.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bSettings.setText("Настройки");
        buttonGroup1.add(bSettings);
        bSettings.setPreferredSize(new java.awt.Dimension(460, 80));
        bSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSettingsActionPerformed(evt);
            }
        });
        pMain.add(bSettings);

        bHelp.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bHelp.setText("Помощь");
        buttonGroup1.add(bHelp);
        bHelp.setPreferredSize(new java.awt.Dimension(460, 80));
        bHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHelpActionPerformed(evt);
            }
        });
        pMain.add(bHelp);

        bExit.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        bExit.setText("Выйти");
        buttonGroup1.add(bExit);
        bExit.setPreferredSize(new java.awt.Dimension(460, 80));
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });
        pMain.add(bExit);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pBack.add(pMain, gridBagConstraints);

        getContentPane().add(pBack, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAchievementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAchievementsActionPerformed
        new AcheivementsForm(this, true).setVisible(true);
    }//GEN-LAST:event_bAchievementsActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        exit();
    }//GEN-LAST:event_bExitActionPerformed

    private void exit() {
        try {
            Global.save();
            Global.getLogger().info("Завершаю игру");
        } finally {
            LoadGameForm.releaseResources();
            dispose();
            System.exit(0);
        }

    }

    private void bCampaignEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCampaignEditorActionPerformed
        EditorForm editorForm = new EditorForm(this, true, getWidth(), getHeight());
        editorForm.setVisible(true);
    }//GEN-LAST:event_bCampaignEditorActionPerformed

    private void bSinglePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSinglePlayerActionPerformed
        GameForm gameForm;
        try {
            gameForm = new GameForm(this, true, getWidth(), getHeight());
            gameForm.singlePlayGame();
        } catch (IOException ex) {
            Global.logAndShowException(ex);
        }
    }//GEN-LAST:event_bSinglePlayerActionPerformed

    private void bSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSettingsActionPerformed
        SettingsForm settingsForm = new SettingsForm(this, true);
        settingsForm.setVisible(true);
        if (Global.isFullScreen()) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            setExtendedState(JFrame.NORMAL);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.getWidth() * 0.8);
            int height = (int) (screenSize.getHeight() * 0.8);
            setSize(width, height);
            setLocationRelativeTo(null);
        }

    }//GEN-LAST:event_bSettingsActionPerformed

    private void bStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStatsActionPerformed
        new StatisticsForm(this, true).setVisible(true);
    }//GEN-LAST:event_bStatsActionPerformed

    private void bCampaignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCampaignActionPerformed
        startCampaignAction();
    }//GEN-LAST:event_bCampaignActionPerformed

    private void startCampaignAction() throws HeadlessException {
        CampaignChooseForm campaignChooseForm = new CampaignChooseForm(this, true);
        campaignChooseForm.setVisible(true);
        LevelAndCampaign choosenCampaign = campaignChooseForm.getChoosenCampaign();
        if (choosenCampaign != null) {
            GameForm gameForm;
            try {
                gameForm = new GameForm(this, true, getWidth(), getHeight());
                gameForm.campaign(choosenCampaign);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void bHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHelpActionPerformed
        JOptionPane.showMessageDialog(null, HELP_MESSAGE,
                "Об игре", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_bHelpActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exit();
    }//GEN-LAST:event_formWindowClosing

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
                Global.load();
                MainForm mainForm = new MainForm();
                Global.getLogger().info("Игра запущена");
                mainForm.setVisible(true);
            } catch (IOException ex) {
                Global.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAchievements;
    private javax.swing.JButton bCampaign;
    private javax.swing.JButton bCampaignEditor;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bHelp;
    private javax.swing.JButton bSettings;
    private javax.swing.JButton bSinglePlayer;
    private javax.swing.JButton bStats;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel pBack;
    private javax.swing.JPanel pMain;
    // End of variables declaration//GEN-END:variables
}
