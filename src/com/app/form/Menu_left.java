package com.app.form;

import com.app.component.Item_People;
import net.miginfocom.swing.MigLayout;


public class Menu_left extends javax.swing.JPanel {


    public Menu_left() {
        initComponents();
        init();
    }
    private void init(){
        menuList.setLayout(new MigLayout("fillx","0[]0","1[]1"));
        showPeople();    
    }    
    private void showPeople(){
        for(int i=0;i<6;i++){
            menuList.add(new Item_People("People "+ i), "wrap");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        menu = new javax.swing.JLayeredPane();
        menuButton3 = new com.app.component.MenuButton();
        menuButton1 = new com.app.component.MenuButton();
        menuButton2 = new com.app.component.MenuButton();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));
        setForeground(new java.awt.Color(0, 0, 0));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setLayout(new java.awt.GridLayout());

        menuButton3.setBorder(null);
        menuButton3.setForeground(new java.awt.Color(0, 0, 0));
        menuButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-message-30.png"))); // NOI18N
        menu.add(menuButton3);

        menuButton1.setBorder(null);
        menuButton1.setForeground(new java.awt.Color(0, 0, 0));
        menuButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-group-30.png"))); // NOI18N
        menuButton1.setRequestFocusEnabled(false);
        menu.add(menuButton1);

        menuButton2.setBorder(null);
        menuButton2.setForeground(new java.awt.Color(0, 0, 0));
        menuButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-box-30.png"))); // NOI18N
        menuButton2.setRequestFocusEnabled(false);
        menu.add(menuButton2);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuList)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuList)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLayeredPane menu;
    private com.app.component.MenuButton menuButton1;
    private com.app.component.MenuButton menuButton2;
    private com.app.component.MenuButton menuButton3;
    private javax.swing.JLayeredPane menuList;
    // End of variables declaration//GEN-END:variables
}
