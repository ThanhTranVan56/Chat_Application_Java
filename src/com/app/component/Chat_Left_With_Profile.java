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

public class Chat_Left_With_Profile extends javax.swing.JLayeredPane {

    public Chat_Left_With_Profile() {
        initComponents();
        txt.setBackground(new Color(242, 242, 242));
    }

    public void setUserProfile(String user) {
        txt.setUserProfile(user);
    }

    public void setImageProfile(Icon image) {
        Iaimage.setImage(image);
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        Iaimage = new com.app.swing.ImageAvatar();
        txt = new com.app.component.Chat_Item();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        Iaimage.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/testing/test-1.jpg"))); // NOI18N
        Iaimage.setMaximumSize(new java.awt.Dimension(25, 25));
        Iaimage.setMinimumSize(new java.awt.Dimension(25, 25));
        Iaimage.setPreferredSize(new java.awt.Dimension(25, 25));

        jLayeredPane1.setLayer(Iaimage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(Iaimage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Iaimage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jLayeredPane1);

        txt.setBackground(new java.awt.Color(79, 79, 79));
        txt.setText("<Not Set>");
        add(txt);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.app.swing.ImageAvatar Iaimage;
    private javax.swing.JLayeredPane jLayeredPane1;
    private com.app.component.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
