package com.app.component;

import com.app.model.Model_File_Sender;
import com.app.model.Model_Receive_File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import net.miginfocom.swing.MigLayout;

public class Chat_File extends javax.swing.JLayeredPane {

    public Chat_File(boolean right) {
        initComponents();
        setLayout(new MigLayout("", "0[" + (right ? "right" : "left") + "]0", "3[]3"));
        setOpaque(false);
    }

    public void setFile(Model_File_Sender fileSender) {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        Icon fileIcon = fileSystemView.getSystemIcon(fileSender.getFile());
        ImageIcon imageIcon = (ImageIcon) fileIcon;

        // Phóng to Icon
//        Image scaledImage = imageIcon.getImage().getScaledInstance(53, 53, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        File_Item pic = new File_Item();
        pic.setFile(imageIcon, fileSender);
        add(pic, "wrap");
    }

    public void setFile(Model_Receive_File dataFile) {
        File_Item pic = new File_Item();
        pic.setFile(dataFile);
        //addEvent(pic, image);
        add(pic, "wrap");
    }
    

    public void setFile(String fileName, String size) {
        //lbFileName.setText(fileName);
        //lbFileSize.setText(size);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
