package com.app.form;

import com.app.component.Item_People;
import com.app.swing.ScrollBar;
import net.miginfocom.swing.MigLayout;


public class Menu_left extends javax.swing.JPanel {


    public Menu_left() {
        initComponents();
        init();
    }
    private void init(){
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx","0[]0","1[]1"));
        showMessage();    
    }    
    private void showMessage(){
        menuList.removeAll();
        for(int i=0;i<20;i++){
            menuList.add(new Item_People("People "+ i), "wrap");
        }
        refreshMenuList();
    }
    private void showGroup(){
        menuList.removeAll();
        for(int i=0;i<5;i++){
            menuList.add(new Item_People("Group "+ i), "wrap");
        }
        refreshMenuList();
    }
    private void showBox(){
        menuList.removeAll();
        for(int i=0;i<10;i++){
            menuList.add(new Item_People("Box "+ i), "wrap");
        }
        refreshMenuList();
    }
    private void refreshMenuList(){
        menuList.repaint();
        menuList.revalidate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        menu = new javax.swing.JLayeredPane();
        menuMessage = new com.app.component.MenuButton();
        menuGroup = new com.app.component.MenuButton();
        menuBox = new com.app.component.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));
        setForeground(new java.awt.Color(0, 0, 0));

        menu.setBackground(new java.awt.Color(242, 242, 242));
        menu.setOpaque(true);
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMessage.setBackground(new java.awt.Color(242, 242, 242));
        menuMessage.setBorder(null);
        menuMessage.setForeground(new java.awt.Color(0, 0, 0));
        menuMessage.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-message-40-selected.png"))); // NOI18N
        menuMessage.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-message-30.png"))); // NOI18N
        menuMessage.setSelected(true);
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menu.add(menuMessage);

        menuGroup.setBorder(null);
        menuGroup.setForeground(new java.awt.Color(0, 0, 0));
        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-group-30.png"))); // NOI18N
        menuGroup.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-group-40-selected.png"))); // NOI18N
        menuGroup.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-group-30.png"))); // NOI18N
        menuGroup.setRequestFocusEnabled(false);
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuGroup);

        menuBox.setBorder(null);
        menuBox.setForeground(new java.awt.Color(0, 0, 0));
        menuBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-box-30.png"))); // NOI18N
        menuBox.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-box-40-selected.png"))); // NOI18N
        menuBox.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-box-30.png"))); // NOI18N
        menuBox.setRequestFocusEnabled(false);
        menuBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoxActionPerformed(evt);
            }
        });
        menu.add(menuBox);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setBackground(new java.awt.Color(242, 244, 242));
        menuList.setOpaque(true);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        if(!menuMessage.isSelected()){  
            menuMessage.setSelected(true);
            menuGroup.setSelected(false);
            menuBox.setSelected(false); 
            showMessage();
        }
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
        if(!menuGroup.isSelected()){
            menuMessage.setSelected(false);
            menuGroup.setSelected(true);
            menuBox.setSelected(false);
            showGroup();
        }
    }//GEN-LAST:event_menuGroupActionPerformed

    private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoxActionPerformed
        if(!menuBox.isSelected()){
            menuMessage.setSelected(false);
            menuGroup.setSelected(false);
            menuBox.setSelected(true);
            showBox();
        }    
    }//GEN-LAST:event_menuBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLayeredPane menu;
    private com.app.component.MenuButton menuBox;
    private com.app.component.MenuButton menuGroup;
    private javax.swing.JLayeredPane menuList;
    private com.app.component.MenuButton menuMessage;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
