package com.app.component;

import com.app.event.EventFileReceiver;
import com.app.event.EventFileSender;
import com.app.event.PublicEvent;
import com.app.model.Model_File_Sender;
import com.app.model.Model_Receive_File;
import com.app.service.Service;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

public class File_Item extends javax.swing.JLayeredPane {

    public File_Item() {
        initComponents();
    }

    public void setFile(Icon image, Model_File_Sender fileSender) {
        fileSender.addEvent(new EventFileSender() {
            @Override
            public void onSending(double percentage) {
                progress.setValue((int) percentage);
            }

            @Override
            public void onStartSending() {

            }

            @Override
            public void onFinish() {
                progress.setVisible(false);
            }
        });
        pic.setPreferredSize(getAutoSize(image, 200, 200));
        pic.setImage(image);
        lbFileName.setText(fileSender.getFile().getName());
        lbFileSize.setText(fileSender.getFileSizeConvert(fileSender.getGileSize()));
        addEvent(pic, fileSender.getFile());
        addEvent(jPanel2, fileSender.getFile());
    }

    public void setFile(Model_Receive_File dataFile) {
        pic.setImage(new ImageIcon(getClass().getResource("/com/app/icon/file1.png")));
        try {
            Service.getInstance().addFileReceiver(dataFile.getFileID(), new EventFileReceiver() {
                @Override
                public void onReceiving(double percentage) {
                    progress.setValue((int) percentage);
                }

                @Override
                public void onStartReceiving() {

                }

                @Override
                public void onFinish(File file, String fileSize) {
                    progress.setVisible(false);
                    FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                    pic.setImage(fileSystemView.getSystemIcon(file));

                    lbFileName.setText(getFileName(file.getName()));
                    lbFileSize.setText(fileSize);
                    addEvent(pic, file);
                    addEvent(jPanel2, file);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEvent(Component com, File file) {
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    PublicEvent.getInstance().getEventSaveFile().saveFile(file);
                }
            }
        });
    }

    private Dimension getAutoSize(Icon image, int w, int h) {
        if (w > image.getIconWidth()) {
            w = image.getIconWidth();
        }
        if (h > image.getIconHeight()) {
            h = image.getIconHeight();
        }
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        return new Dimension(width, height);
    }

    private String getFileName(String fileName) {
        int atSymbolIndex = fileName.lastIndexOf("@");
        if (atSymbolIndex != -1) {
            return fileName.substring(atSymbolIndex + 1, fileName.length());
        }
        return fileName;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new com.app.swing.PictureBox();
        progress = new com.app.swing.Progress();
        jPanel2 = new javax.swing.JPanel();
        lbFileName = new javax.swing.JLabel();
        lbFileSize = new javax.swing.JLabel();

        progress.setBackground(new java.awt.Color(51, 51, 51));
        progress.setForeground(new java.awt.Color(255, 255, 255));
        progress.setProgessType(com.app.swing.Progress.ProgressType.CANCEL);

        pic.setLayer(progress, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(2, 1));

        lbFileName.setForeground(new java.awt.Color(0, 0, 0));
        lbFileName.setText("My File Name.file");
        jPanel2.add(lbFileName);

        lbFileSize.setForeground(new java.awt.Color(7, 99, 153));
        lbFileSize.setText(" 5 MB");
        jPanel2.add(lbFileSize);

        setLayer(pic, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbFileName;
    private javax.swing.JLabel lbFileSize;
    private com.app.swing.PictureBox pic;
    private com.app.swing.Progress progress;
    // End of variables declaration//GEN-END:variables
}
