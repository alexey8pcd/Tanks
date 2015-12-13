package main.forms;

import geometry.Drawable;
import geometry.GeometryMap;
import geometry.GeometryMap.Material;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import persist.GeometryMapPersistance;

/**
 *
 * @author Алексей
 */
public class MapEditor extends javax.swing.JDialog {

    private class Tool implements Drawable {

        Material material;
        int x;
        int y;
        int width;
        int height;

        public Tool(Material material, int startX, int startY) {
            this.material = material;
            this.x = startX;
            this.y = startY;
        }

        public void setSize(int size) {
            this.width = size;
            this.height = size;
        }

        public void setLocationOfCenter(int x, int y) {
            this.x = x - width / 2;
            this.y = y - height / 2;
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawRect(x - 1, y - 1, width + 1, height + 1);
            g.setColor(material.getColor());
            g.fillRect(x, y, width, height);
        }

        @Override
        public boolean isVisible() {
            return true;
        }

    }
    private GeometryMap currentMap;
    private BufferedImage buffer;
    private Graphics rootGraphics;
    private Tool tool;
    private int scale;
    private final int minScale = 4;
    private final int maxScale = 128;

    public MapEditor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buffer = new BufferedImage(paneDraw.getWidth(),
                paneDraw.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        rootGraphics = paneDraw.getGraphics();
        currentMap = GeometryMap.newInstance();
        tool = new Tool(Material.BRICK, 0, 0);
        scale = 16;
        sliderScale.setValue(scale);
        tool.setSize(scale);
    }

    private void draw() {
        Graphics temp = buffer.getGraphics();
        temp.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
        drawObjects(temp);
        rootGraphics.drawImage(buffer, 0, 0, null);
    }

    private void drawObjects(Graphics graphics) {
        currentMap.drawWithGrid(graphics);
        tool.draw(graphics);
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
        jButton9 = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        sliderScale = new javax.swing.JSlider();
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
        bTerra.setMaximumSize(new java.awt.Dimension(32, 32));
        bTerra.setMinimumSize(new java.awt.Dimension(32, 32));
        bTerra.setPreferredSize(new java.awt.Dimension(32, 32));
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

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        toolBarForEditor.add(jButton9);

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

    private void paneDrawMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMousePressed
        addTilesOnMap();
    }//GEN-LAST:event_paneDrawMousePressed

    private void paneDrawMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMouseMoved
        tool.setLocationOfCenter(evt.getX(), evt.getY());
        draw();
    }//GEN-LAST:event_paneDrawMouseMoved

    private void paneDrawMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_paneDrawMouseWheelMoved
        changeScale(evt);
    }//GEN-LAST:event_paneDrawMouseWheelMoved

    private void sliderScaleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderScaleStateChanged
        scale = sliderScale.getValue();
        tool.setSize(scale);
        draw();
    }//GEN-LAST:event_sliderScaleStateChanged

    private void paneDrawMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneDrawMouseDragged
        tool.setLocationOfCenter(evt.getX(), evt.getY());
        addTilesOnMap();
    }//GEN-LAST:event_paneDrawMouseDragged

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        dispose();
    }//GEN-LAST:event_bExitActionPerformed

    private void bPlaceBricksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceBricksActionPerformed
        tool.material = Material.BRICK;
        draw();
    }//GEN-LAST:event_bPlaceBricksActionPerformed

    private void bPlaceMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceMetalActionPerformed
        tool.material = Material.ARMOR;
        draw();
    }//GEN-LAST:event_bPlaceMetalActionPerformed

    private void bPlaceForestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceForestActionPerformed
        tool.material = Material.WOOD;
        draw();
    }//GEN-LAST:event_bPlaceForestActionPerformed

    private void bPlaceWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceWaterActionPerformed
        tool.material = Material.WATER;
        draw();
    }//GEN-LAST:event_bPlaceWaterActionPerformed

    private void bPlaceIceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlaceIceActionPerformed
        tool.material = Material.ICE;
        draw();
    }//GEN-LAST:event_bPlaceIceActionPerformed

    private void bTerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTerraActionPerformed
        tool.material = Material.TERRA;
        draw();
    }//GEN-LAST:event_bTerraActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        currentMap.clear();
        draw();
    }//GEN-LAST:event_bClearActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        saveMap();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void saveMap() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("map.dat"));
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            boolean saved = GeometryMapPersistance.saveMapToFile(selectedFile,
                    currentMap);
            if (saved) {
                JOptionPane.showMessageDialog(null, "Карта сохранена успешно",
                        "Информация", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void addTilesOnMap() {
        for (int x = tool.x; x < tool.x + tool.width; x++) {
            for (int y = tool.y; y < tool.y + tool.height; y++) {
                currentMap.setTile(x, y, tool.material);
            }
        }
        draw();
    }

    private void changeScale(MouseWheelEvent evt) {
        if (evt.getWheelRotation() > 0) {
            scale -= 4;
            if (scale < minScale) {
                scale = minScale;
            }
        } else {
            scale += 4;
            if (scale > maxScale) {
                scale = maxScale;
            }
        }
        sliderScale.setValue(scale);
        sliderScale.updateUI();
        tool.setSize(scale);
        tool.setLocationOfCenter(evt.getX(), evt.getY());
        draw();
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
    private javax.swing.JButton bTerra;
    private javax.swing.JButton bUndo;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPanel paneDraw;
    private javax.swing.JSlider sliderScale;
    private javax.swing.JToolBar toolBarForEditor;
    // End of variables declaration//GEN-END:variables

}
