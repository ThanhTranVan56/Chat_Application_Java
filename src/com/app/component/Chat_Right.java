package com.app.component;

import com.app.model.Model_File_Sender;
import com.app.model.Model_Receive_File;
import com.app.model.Model_Receive_Image;
import java.awt.Color;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Icon;

public class Chat_Right extends javax.swing.JLayeredPane {

    public Chat_Right() {
        initComponents();
        txt.setBackground(new Color(129, 251, 251));
    }

    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }
        txt.seen();
    }

    public void setImage(Model_Receive_Image dataImage) {
       txt.setImage(true, dataImage);
    }
    public void setImage(Model_File_Sender fileSender) {
        txt.setImage(true, fileSender);
    }

    public void setImage(String... image) {
        //txt.setImage(false, image);
    }
    
    public void setFile(Model_File_Sender fileSender){
        txt.setFile(true, fileSender); 
    }
    
    public void setFile(Model_Receive_File dataFile){
        txt.setFile(true, dataFile); 
    }
    
    public void setFile(String fileName, String fileSize) {
        
    }

    public void setEmoji(Icon icon) {
        txt.hideText();
        txt.setEmoji(true, icon);
    }
    
    public void setTime() {
        LocalTime currentTime = LocalTime.now(); // Lấy thời gian hiện tại

        // Định dạng thời gian thành chuỗi theo định dạng "hh:mm a"
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
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
