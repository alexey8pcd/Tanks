package ru.ovcharov_alexey.tanks.v4.ui.forms;

import java.awt.HeadlessException;
import ru.ovcharov_alexey.tanks.v4.logic.controllers.MapEditorController;
import ru.ovcharov_alexey.tanks.v4.engine.physics.Material;
import javax.swing.JOptionPane;
import ru.ovcharov_alexey.tanks.v4.engine.GeometryMap;
import ru.ovcharov_alexey.tanks.v4.engine.Global;

/**
 *
 * @author Алексей
 */
public class MapEditorForm extends javax.swing.JDialog {

    private boolean needConfirm;

    private MapEditorController mapEditorController;
    private boolean saveOnClose;

    public MapEditorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Global.getLogger().info(() -> "Выбран режим редактирования карты");
        try {
            init();
        } catch (Exception ex) {
            Global.logAndShowException(ex);

        }
    }

    private void confirmExit() throws HeadlessException {
        int result = JOptionPane.showConfirmDialog(null,
                "Завершить редактирование? Все несохраненные изменения будут утеряны.",
                "Подтвердите выход", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    private void init() throws Exception {
        saveOnClose = true;
        needConfirm = false;
        mapEditorController = new MapEditorController(paneDraw.getWidth(),
                paneDraw.getHeight(), paneDraw.getGraphics());
        sliderScale.setValue(mapEditorController.getScale());
        setLocationRelativeTo(null);
    }

    private void exit() {
        if (saveOnClose) {
            if (needConfirm) {
                confirmExit();
            } else {
                dispose();
            }
        } else {
            dispose();
        }
    }

    public GeometryMap getMap() {
        return mapEditorController.getMap();
    }

    public void disableSaveOnClose() {
        this.saveOnClose = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBarForEditor = new javax.swing.JToolBar();
        bTerra = new javax.swing.JButton();
        bPlaceBricks = new javax.swing.JButton();
        bPlaceMetal = new javax.swing.JButton();
        bPlaceForest = new javax.swing.JButton();
        bPlaceWater = new javax.swing.JButton();
        bPlaceIce = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        bUndo = new javax.swing.JButton();
        bRedo = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        bSaveMap = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        sliderScale = new javax.swing.JSlider();
        paneDraw = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Редактор карт");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        toolBarForEditor.setFloatable(false);

        bTerra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/terra.png"))); // NOI18N
        bTerra.setToolTipText("Без материала");
        bTerra.setBorder(null);
        bTerra.setDoubleBuffered(true);
        bTerra.setFocusable(false);
        bTerra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bTerra.setMaximumSize(new java.awt.Dimension(32, 32));
        bTerra.setMinimumSize(new java.awt.Dimension(32, 32));
        bTerra.setPreferredSize(new java.awt.Dimension(32, 32));
        bTerra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTerraActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bTerra);

        bPlaceBricks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/bricks.png"))); // NOI18N
        bPlaceBricks.setToolTipText("Кирпич");
        bPlaceBricks.setFocusable(false);
        bPlaceBricks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPlaceBricks.setMaximumSize(new java.awt.Dimension(32, 64));
        bPlaceBricks.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPlaceBricks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlaceBricksActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bPlaceBricks);

        bPlaceMetal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/metal.png"))); // NOI18N
        bPlaceMetal.setToolTipText("Броня");
        bPlaceMetal.setFocusable(false);
        bPlaceMetal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPlaceMetal.setMaximumSize(new java.awt.Dimension(32, 64));
        bPlaceMetal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPlaceMetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlaceMetalActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bPlaceMetal);

        bPlaceForest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/forest.png"))); // NOI18N
        bPlaceForest.setToolTipText("Лес");
        bPlaceForest.setFocusable(false);
        bPlaceForest.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPlaceForest.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPlaceForest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlaceForestActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bPlaceForest);

        bPlaceWater.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/water.png"))); // NOI18N
        bPlaceWater.setToolTipText("Вода");
        bPlaceWater.setFocusable(false);
        bPlaceWater.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPlaceWater.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPlaceWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlaceWaterActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bPlaceWater);

        bPlaceIce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/ice.png"))); // NOI18N
        bPlaceIce.setToolTipText("Лед");
        bPlaceIce.setFocusable(false);
        bPlaceIce.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPlaceIce.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPlaceIce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlaceIceActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bPlaceIce);
        toolBarForEditor.add(jSeparator1);

        bUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/undo.png"))); // NOI18N
        bUndo.setToolTipText("Отмена");
        bUndo.setFocusable(false);
        bUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUndoActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bUndo);

        bRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/redo.png"))); // NOI18N
        bRedo.setToolTipText("Повторить");
        bRedo.setFocusable(false);
        bRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRedoActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bRedo);

        bClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/clear.png"))); // NOI18N
        bClear.setToolTipText("Очистить карту");
        bClear.setFocusable(false);
        bClear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bClear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bClear);
        toolBarForEditor.add(jSeparator2);

        bSaveMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/save.png"))); // NOI18N
        bSaveMap.setToolTipText("Сохранить");
        bSaveMap.setFocusable(false);
        bSaveMap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSaveMap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSaveMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveMapActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bSaveMap);

        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/exit.png"))); // NOI18N
        bExit.setToolTipText("Выход");
        bExit.setFocusable(false);
        bExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bExit);
        toolBarForEditor.add(jSeparator3);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/button_images/scale.png"))); // NOI18N
        toolBarForEditor.add(jLabel1);

        sliderScale.setMajorTickSpacing(32);
        sliderScale.setMaximum(128);
        sliderScale.setMinimum(8);
        sliderScale.setMinorTickSpacing(16);
        sliderScale.setPaintTicks(true);
        sliderScale.setValue(16);
        sliderScale.setDoubleBuffered(true);
        sliderScale.setPreferredSize(new java.awt.Dimension(100, 31));
        sliderScale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderScaleStateChanged(evt);
            }
        });
        toolBarForEditor.add(sliderScale);

        getContentPane().add(toolBarForEditor, java.awt.BorderLayout.PAGE_START);

        paneDraw.setBackground(new java.awt.Color(255, 255, 255));
        paneDraw.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                paneDrawMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                paneDrawMouseMoved(evt);
            }
        });
        paneDraw.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                paneDrawMouseWheelMoved(evt);
            }
        });
        paneDraw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                paneDrawMousePressed(evt);
            }
        });

        javax.swing.GroupLayout paneDrawLayout = new javax.swing.GroupLayout(paneDraw);
        paneDraw.setLayout(paneDrawLayout);
        paneDrawLayout.setHorizontalGroup(
            paneDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        paneDrawLayout.setVerticalGroup(
            paneDrawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );

        getContentPane().add(paneDraw, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderScaleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderScaleStateChanged
        int scale = sliderScale.getValue();
        mapEditorController.setToolSize(scale);
    }//GEN-LAST:event_sliderScaleStateChanged

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        exit();
    }//GEN-LAST:event_bExitActionPerformed

    private void bPlaceBricksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceBricksActionPerformed
        mapEditorController.setToolMaterial(Material.BRICKS);
    }//GEN-LAST:event_bPlaceBricksActionPerformed

    private void bPlaceMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceMetalActionPerformed
        mapEditorController.setToolMaterial(Material.METAL);
    }//GEN-LAST:event_bPlaceMetalActionPerformed

    private void bPlaceForestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceForestActionPerformed
        mapEditorController.setToolMaterial(Material.FOREST);
    }//GEN-LAST:event_bPlaceForestActionPerformed

    private void bPlaceWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceWaterActionPerformed
        mapEditorController.setToolMaterial(Material.WATER);
    }//GEN-LAST:event_bPlaceWaterActionPerformed

    private void bPlaceIceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceIceActionPerformed
        mapEditorController.setToolMaterial(Material.ICE);
    }//GEN-LAST:event_bPlaceIceActionPerformed

    private void bTerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTerraActionPerformed
        mapEditorController.setToolMaterial(Material.TERRA);
    }//GEN-LAST:event_bTerraActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        mapEditorController.clearMap();
    }//GEN-LAST:event_bClearActionPerformed

    private void bSaveMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveMapActionPerformed
        mapEditorController.saveMap();
        needConfirm = false;
    }//GEN-LAST:event_bSaveMapActionPerformed

    private void bUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUndoActionPerformed
        mapEditorController.undo();
        needConfirm = true;
    }//GEN-LAST:event_bUndoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void bRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRedoActionPerformed
        mapEditorController.redo();
        needConfirm = true;
    }//GEN-LAST:event_bRedoActionPerformed

    private void paneDrawMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMousePressed
        mapEditorController.addTilesOnMap();
        needConfirm = true;
    }//GEN-LAST:event_paneDrawMousePressed

    private void paneDrawMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_paneDrawMouseWheelMoved
        mapEditorController.changeScale(evt.getWheelRotation(), evt.getX(), evt.getY());
        sliderScale.setValue(mapEditorController.getScale());
        sliderScale.updateUI();
    }//GEN-LAST:event_paneDrawMouseWheelMoved

    private void paneDrawMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMouseMoved
        mapEditorController.setToolLocation(evt.getX(), evt.getY());
    }//GEN-LAST:event_paneDrawMouseMoved

    private void paneDrawMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMouseDragged
        mapEditorController.setToolLocation(evt.getX(), evt.getY());
        mapEditorController.addTilesOnMap();
    }//GEN-LAST:event_paneDrawMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bPlaceBricks;
    private javax.swing.JButton bPlaceForest;
    private javax.swing.JButton bPlaceIce;
    private javax.swing.JButton bPlaceMetal;
    private javax.swing.JButton bPlaceWater;
    private javax.swing.JButton bRedo;
    private javax.swing.JButton bSaveMap;
    private javax.swing.JButton bTerra;
    private javax.swing.JButton bUndo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPanel paneDraw;
    private javax.swing.JSlider sliderScale;
    private javax.swing.JToolBar toolBarForEditor;
    // End of variables declaration//GEN-END:variables

}
