package com.app.component;

import com.app.swing.ScrollBar;
import java.awt.Color;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;


public class Chat_Body extends javax.swing.JPanel {


    public Chat_Body() {
        initComponents();
        init();
        addItemLeft("That's so weird. Right now I'm running on Ubuntu with Java 1.7, but I get the same problem when I run it on Windows 7 with Java 1.7. Do you have any idea why they would be different?");
        
        addItemRight("That's so weird. Right now I'm running on Ubuntu with Java 1.7, but I get the same problem when I run it on Windows 7 with Java 1.7. Do you have any idea why they would be different?");
        addItemLeft("Hello \naaaaa \nbbbbb");
        addItemRight("\naaaaa \nbbbbb");
        addItemLeft("Hello \naaaaa \nbbbbb");
        addItemRight("\naaaaa \nbbbbb");
        addItemLeft("Hello \naaaaa \nbbbbb");
        addItemRight("\naaaaa \nbbbbb");
        addItemLeft("Hello \naaaaa \nbbbbb");
        addItemRight("\naaaaa \nbbbbb");
        addItemLeft("Hello \naaaaa \nbbbbb");
        addItemRight("\naaaaa \nbbbbb");
        addItemLeft("Hello \naaaaa \nbbbbb");
        addItemRight("\naaaaa \nbbbbb");
    }
    
    private void init(){
        body.setLayout(new MigLayout("fillx", "","5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }
    
    public void addItemLeft(String text){
        Chat_Left item = new Chat_Left();
        item.setText(text);
        body.add(item, "wrap, w ::80%");
        body.repaint();
        body.revalidate();
    }
    public void addItemRight(String text){
        Chat_Right item = new Chat_Right();
        item.setText(text);
        body.add(item, "wrap,al right, w ::80%");
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
