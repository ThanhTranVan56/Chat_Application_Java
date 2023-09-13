package com.app.component;

import com.app.swing.ScrollBar;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;


public class Chat_Body extends javax.swing.JPanel {


    public Chat_Body() {
        initComponents();
        init();
        addItemLeft("That's so weird. Right now I'm running on Ubuntu with Java 1.7, but I get the same problem when I run it on Windows 7 with Java 1.7. Do you have any idea why they would be different?","Thanh");
        
        addItemRight("That's so weird. Right now I'm running on Ubuntu with Java 1.7, but I get the same problem when I run it on Windows 7 with Java 1.7. Do you have any idea why they would be different?");
        addDate("12/09/2023");
        
        addItemLeft("Hello \naaaaa \nbbbbb", "Tran");
        addItemRight("\naaaaa \nbbbbb");
        addItemLeft("Hello \naaaaa \nbbbbb","Van");
        
        addDate("Today");
        addItemLeft("Hello \naaaaa \nbbbbb", "Tr", new ImageIcon(getClass().getResource("/com/app/icon/testing/test-2.jpg")),new ImageIcon(getClass().getResource("/com/app/icon/testing/test-1.jpg"))); 
        addItemLeft("","Tr",new ImageIcon(getClass().getResource("/com/app/icon/testing/test-2.jpg")));
        addItemRight("\naaaaa \nbbbbb",new ImageIcon(getClass().getResource("/com/app/icon/testing/test-2.jpg")));
    }
    
    private void init(){
        body.setLayout(new MigLayout("fillx", "","5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }
    
    public void addItemLeft(String text, String user, Icon ...image){
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        //Chat_Left item = new Chat_Left();
        item.setUserProfile(user);
        item.setImage(image);
        item.setTime();
        item.setText(text);
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
    }
    
    public void addItemRight(String text,Icon ...image){
        Chat_Right item = new Chat_Right();
        item.setText(text);
        item.setImage(image);
        body.add(item, "wrap,al right, w 100::80%");
        body.repaint();
        body.revalidate();
    }
    
    public void addDate(String date){
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item,"wrap, al center");
        body.repaint();
        body.revalidate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBackground(new java.awt.Color(255, 255, 255));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
