package main.forms;

import geometry.GeometryMap.Material;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import main.MapEditor;
import persist.GeometryMapPersistance;

/**
 *
 * @author Алексей
 */
public class MapEditorForm extends javax.swing.JDialog {

    private final MapEditor mapEditor;
    private final int minScale = 4;
    private final int defaultScale = 16;
    private final int maxScale = 128;

    public MapEditorForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        sliderForScaling.setValue(defaultScale);
        mapEditor = new MapEditor(paneDraw.getWidth(), paneDraw.getHeight(),
                defaultScale, sliderForScaling, paneDraw.getGraphics());
        mapEditor.setMaxScale(maxScale);
        mapEditor.setMinScale(minScale);
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
        sliderForScaling = new javax.swing.JSlider();
        paneDraw = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Редактор карт");
        setResizable(false);

        toolBarForEditor.setFloatable(false);

        bTerra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/terra.png"))); // NOI18N
        bTerra.setBorder(null);
        bTerra.setDoubleBuffered(true);
        bTerra.setFocusable(false);
        bTerra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bTerra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTerraActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bTerra);

        bPlaceBricks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bricks.png"))); // NOI18N
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

        bPlaceMetal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/metal.png"))); // NOI18N
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

        bPlaceForest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forest.png"))); // NOI18N
        bPlaceForest.setFocusable(false);
        bPlaceForest.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPlaceForest.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPlaceForest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlaceForestActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bPlaceForest);

        bPlaceWater.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/water.png"))); // NOI18N
        bPlaceWater.setFocusable(false);
        bPlaceWater.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPlaceWater.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPlaceWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlaceWaterActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bPlaceWater);

        bPlaceIce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ice.png"))); // NOI18N
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

        bUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/undo.png"))); // NOI18N
        bUndo.setFocusable(false);
        bUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarForEditor.add(bUndo);

        bRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/redo.png"))); // NOI18N
        bRedo.setFocusable(false);
        bRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarForEditor.add(bRedo);

        bClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
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

        bSaveMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        bSaveMap.setFocusable(false);
        bSaveMap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bSaveMap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bSaveMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveMapActionPerformed(evt);
            }
        });
        toolBarForEditor.add(bSaveMap);

        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/scale.png"))); // NOI18N
        toolBarForEditor.add(jLabel1);

        sliderForScaling.setMajorTickSpacing(32);
        sliderForScaling.setMaximum(128);
        sliderForScaling.setMinimum(8);
        sliderForScaling.setMinorTickSpacing(16);
        sliderForScaling.setPaintTicks(true);
        sliderForScaling.setValue(16);
        sliderForScaling.setDoubleBuffered(true);
        sliderForScaling.setPreferredSize(new java.awt.Dimension(100, 31));
        sliderForScaling.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderForScalingStateChanged(evt);
            }
        });
        toolBarForEditor.add(sliderForScaling);

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

    private void paneDrawMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMousePressed
        mapEditor.addTilesOnMap();
    }//GEN-LAST:event_paneDrawMousePressed

    private void paneDrawMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMouseMoved
        mapEditor.handleMouseMove(evt.getX(), evt.getY());
    }//GEN-LAST:event_paneDrawMouseMoved

    private void paneDrawMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_paneDrawMouseWheelMoved
        mapEditor.changeScale(evt, sliderForScaling);
    }//GEN-LAST:event_paneDrawMouseWheelMoved

    private void sliderForScalingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderForScalingStateChanged
        mapEditor.setScaleOfTool(sliderForScaling.getValue());
    }//GEN-LAST:event_sliderForScalingStateChanged

    private void paneDrawMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMouseDragged
        mapEditor.handleMouseDrag(evt.getX(), evt.getY());
    }//GEN-LAST:event_paneDrawMouseDragged

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        dispose();
    }//GEN-LAST:event_bExitActionPerformed

    private void bPlaceBricksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceBricksActionPerformed
        mapEditor.setMaterial(Material.BRICK);
    }//GEN-LAST:event_bPlaceBricksActionPerformed

    private void bPlaceMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceMetalActionPerformed
        mapEditor.setMaterial(Material.ARMOR);
    }//GEN-LAST:event_bPlaceMetalActionPerformed

    private void bPlaceForestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceForestActionPerformed
        mapEditor.setMaterial(Material.WOOD);
    }//GEN-LAST:event_bPlaceForestActionPerformed

    private void bPlaceWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceWaterActionPerformed
        mapEditor.setMaterial(Material.WATER);
    }//GEN-LAST:event_bPlaceWaterActionPerformed

    private void bPlaceIceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceIceActionPerformed
        mapEditor.setMaterial(Material.ICE);
    }//GEN-LAST:event_bPlaceIceActionPerformed

    private void bTerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTerraActionPerformed
        mapEditor.setMaterial(Material.TERRA);
    }//GEN-LAST:event_bTerraActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        mapEditor.clearMap();
    }//GEN-LAST:event_bClearActionPerformed

    private void bSaveMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveMapActionPerformed
        saveMap();
    }//GEN-LAST:event_bSaveMapActionPerformed

    private void saveMap() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("map.dat"));
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            boolean saved = GeometryMapPersistance.saveMapToFile(selectedFile,
                    mapEditor);
            if (saved) {
                JOptionPane.showMessageDialog(this, "Карта сохранена успешно",
                        "Информация", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

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
    private javax.swing.JSlider sliderForScaling;
    private javax.swing.JToolBar toolBarForEditor;
    // End of variables declaration//GEN-END:variables

}
