package com.app.component;

import com.app.model.Model_File_Sender;
import com.app.model.Model_Receive_File;
import com.app.model.Model_Receive_Image;
import com.app.model.Model_Receive_Image_Group;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class Chat_Item extends javax.swing.JLayeredPane {

    private JLabel label;

    public Chat_Item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
    }

    public void setUserProfile(String user) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        layer.setBorder(new EmptyBorder(10, 10, 0, 10));
        JButton cmd = new JButton(user);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.setForeground(new Color(30, 121, 213));
        cmd.setFont(new java.awt.Font("Segoe UI", 1, 13));
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        layer.add(cmd);
        add(layer, 0);
    }

    public void setText(String text) {
        txt.setText(text);
    }

    public void setTime(String time) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        layer.setBorder(new EmptyBorder(0, 5, 2, 5));
        label = new JLabel(time);
        label.setForeground(new Color(110, 110, 110));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(layer);
    }

    public void setImage(boolean right, Model_File_Sender fileSender) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 2, 5));
        Chat_Image chatImage = new Chat_Image(right);
        chatImage.addImage(fileSender);
        layer.add(chatImage);
        add(layer);
    }

    public void setImage(boolean right, Model_Receive_Image dataImage) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 2, 5));
        Chat_Image chatImage = new Chat_Image(right);
        chatImage.addImage(dataImage);
        layer.add(chatImage);
        add(layer);
    }
    
    public void setImage(boolean right, Model_Receive_Image_Group dataImage) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 2, 5));
        Chat_Image chatImage = new Chat_Image(right);
        chatImage.addImage(dataImage);
        layer.add(chatImage);
        add(layer);
    }
    
    public void setFile(boolean right, Model_Receive_File dataFile) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 2, 0, 0));
        Chat_File chatFile = new Chat_File(right);
        chatFile.setFile(dataFile);
        layer.add(chatFile);
        add(layer);
    }
    
    public void setFile(boolean right, Model_File_Sender fileSender) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 2, 0, 0));
        Chat_File chatFile = new Chat_File(right);
        chatFile.setFile(fileSender);
        layer.add(chatFile);
        add(layer);
    }
    
    public void setFile(boolean right,String fileName, String fileSize) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 2, 2, 0));
        Chat_File chatFile = new Chat_File(right);
        chatFile.setFile(fileName, fileSize);
        layer.add(chatFile);
        add(layer);
    }

    public void setEmoji(boolean right, Icon icon) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 2, 5));
        layer.add(new JLabel(icon));
        add(layer);
        setBackground(null);
    }

    public void sendSuccess() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/icons8-tick-15.png")));
        }
    }

    public void seen() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/icons8-double-tick-15.png")));
        }
    }

    public void hideText() {
        txt.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.app.swing.JIMSendTextPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        txt.setForeground(new java.awt.Color(0, 0, 0));
        txt.setToolTipText("");
        txt.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txt.setMinimumSize(new java.awt.Dimension(21, 36));
        txt.setSelectionColor(new java.awt.Color(114, 180, 252));
        add(txt);
        txt.getAccessibleContext().setAccessibleParent(txt);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        if (getBackground() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        }
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.app.swing.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
