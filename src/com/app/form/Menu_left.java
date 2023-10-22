package com.app.form;

import com.app.component.Item_Group;
import com.app.component.Item_Peoples;
import com.app.event.EventMenuLeft;
import com.app.event.PublicEvent;
import com.app.model.Model_Group;
import com.app.model.Model_User_Account;
import com.app.service.Service;
import com.app.swing.ScrollBar;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;

public class Menu_left extends javax.swing.JPanel {

    private List<Model_User_Account> userAccount;
    private List<Model_Group> group;

    public Menu_left() {
        initComponents();
        init();
    }

    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "1[]1"));
        userAccount = new ArrayList<>();
        group = new ArrayList<>();
        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
            @Override
            public void newUser(List<Model_User_Account> users) {
                for (Model_User_Account d : users) {
                    userAccount.add(d);
                    menuList.add(new Item_Peoples(d), "wrap");
                    refreshMenuList();
                }
            }

            @Override
            public void updateAvataUser(int userID) {
                for (Model_User_Account d : userAccount) {
                    if (d.getUserID() == userID) {
                        for (Component com : menuList.getComponents()) {
                            Item_Peoples item = (Item_Peoples) com;
                            if (item.getUser().getUserID() == d.getUserID()) {
                                item.updateAvata(userID);
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void listGroup(List<Model_Group> groups) {
                for (Model_Group d : groups) {
                    group.add(d);
                }
            }

            @Override
            public void newGroup(Model_Group groups) {
                group.add(groups);
                if (menuGroup.isSelected()) {
                    menuList.add(new Item_Group(groups, false), "wrap");
                    refreshMenuList();
                }
            }

            @Override
            public void userConnect(int userID) {
                for (Model_User_Account u : userAccount) {
                    if (u.getUserID() == userID) {
                        u.setStatus(true);
                        PublicEvent.getInstance().getEventMain().updateUser(u);
                        break;
                    }
                }
                if (menuMessage.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        Item_Peoples item = (Item_Peoples) com;
                        if (item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
            }

            @Override
            public void userDisconnect(int userID) {
                for (Model_User_Account u : userAccount) {
                    if (u.getUserID() == userID) {
                        u.setStatus(false);
                        PublicEvent.getInstance().getEventMain().updateUser(u);
                        break;
                    }
                }
                if (menuMessage.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        Item_Peoples item = (Item_Peoples) com;
                        if (item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
                if (menuGroup.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        Item_Group item = (Item_Group) com;
                        if (item.getGroup().getAdminID() == userID) {
                            item.updateStatus(false);
                        }
                    }
                }
            }

            @Override
            public void checkGroupOnline() {
                Service.getInstance().getClient().emit("check_group_online");
            }

            @Override
            public void statusGroup(List<Integer> groupID) {
                if (menuGroup.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        Item_Group item = (Item_Group) com;
                        for (int groupId : groupID) {
                            if (item.getGroup().getGroupID() == groupId) {
                                item.updateStatus(true);
                                refreshMenuList();
                            }
                        }
                    }
                }
            }

            @Override
            public void logout() {
                userAccount.clear();
                group.clear();
                menuList.removeAll();
                refreshMenuList();
            }

        });
        showMessage();
    }

    private void showMessage() {
        menuList.removeAll();
        for (Model_User_Account d : userAccount) {
            menuList.add(new Item_Peoples(d), "wrap");
        }
        refreshMenuList();
    }

    private void showGroup() {
        menuList.removeAll();
        for (Model_Group d : group) {
            menuList.add(new Item_Group(d, false), "wrap");
        }
        refreshMenuList();
    }

    private void showBox() {
        menuList.removeAll();
        for (int i = 0; i < 10; i++) {
            menuList.add(new Item_Peoples(null), "wrap");
        }
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        menu = new javax.swing.JLayeredPane();
        menuMessage = new com.app.component.Menu_Button();
        menuGroup = new com.app.component.Menu_Button();
        menuBox = new com.app.component.Menu_Button();
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
        menuMessage.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-message-40.png"))); // NOI18N
        menuMessage.setSelected(true);
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menu.add(menuMessage);

        menuGroup.setBorder(null);
        menuGroup.setForeground(new java.awt.Color(0, 0, 0));
        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-group-40.png"))); // NOI18N
        menuGroup.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-group-40-selected.png"))); // NOI18N
        menuGroup.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-group-40.png"))); // NOI18N
        menuGroup.setRequestFocusEnabled(false);
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuGroup);

        menuBox.setBorder(null);
        menuBox.setForeground(new java.awt.Color(0, 0, 0));
        menuBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-box-40 (1).png"))); // NOI18N
        menuBox.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-box-40 (2).png"))); // NOI18N
        menuBox.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-box-40 (1).png"))); // NOI18N
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
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sp)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        if (!menuMessage.isSelected()) {
            menuMessage.setSelected(true);
            menuGroup.setSelected(false);
            menuBox.setSelected(false);
            showMessage();
            PublicEvent.getInstance().getEventMenuRight().setOpton(false);
        }
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
        if (!menuGroup.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(true);
            menuBox.setSelected(false);
            showGroup();
            PublicEvent.getInstance().getEventMenuRight().setOpton(true);
            PublicEvent.getInstance().getEventMenuLeft().checkGroupOnline();
        }
    }//GEN-LAST:event_menuGroupActionPerformed

    private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoxActionPerformed
//        if (!menuBox.isSelected()) {
//            menuMessage.setSelected(false);
//            menuGroup.setSelected(false);
//            menuBox.setSelected(true);
//            showBox();
//            PublicEvent.getInstance().getEventMenuRight().setOpton(false);
//        }
    }//GEN-LAST:event_menuBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLayeredPane menu;
    private com.app.component.Menu_Button menuBox;
    private com.app.component.Menu_Button menuGroup;
    private javax.swing.JLayeredPane menuList;
    private com.app.component.Menu_Button menuMessage;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
