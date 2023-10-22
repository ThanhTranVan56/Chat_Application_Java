package com.app.component;

import com.app.model.Model_Receive_File;
import com.app.model.Model_Receive_Image;
import com.app.model.Model_Receive_Image_Group;
import java.awt.Color;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Icon;

public class Chat_Left extends javax.swing.JLayeredPane {

    public Chat_Left() {
        initComponents();
        txt.setBackground(new Color(242, 242, 242));
    }

    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }
    }

    public void setImage(Model_Receive_Image dataImage) {
        txt.setImage(false, dataImage);
    }

    public void setImage(Model_Receive_Image_Group dataImage) {
        txt.setImage(false, dataImage);
    }

    public void setImage(String... image) {
        //txt.setImage(false, image);
    }

    public void setFile(Model_Receive_File dataFile) {
        txt.setFile(false, dataFile);
    }

    public void setFile(String fileName, String fileSize) {
        txt.setFile(false, fileName, fileSize);
    }

    public void setEmoji(Icon icon) {
        txt.hideText();
        txt.setEmoji(false, icon);
    }

    public void setTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);
        txt.setTime(formattedTime);
    }

    public void setTime(Timestamp datetime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String formattedTime = dateFormat.format(datetime);
        txt.setTime(formattedTime);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.app.component.Chat_Item();

        txt.setBackground(new java.awt.Color(79, 79, 79));
        txt.setText("<Not Set>");

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.app.component.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
