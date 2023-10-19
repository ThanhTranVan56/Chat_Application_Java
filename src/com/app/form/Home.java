package com.app.form;

import com.app.event.PublicEvent;
import com.app.model.Model_Group;
import com.app.model.Model_User_Account;
import net.miginfocom.swing.MigLayout;

public class Home extends javax.swing.JLayeredPane {

    private Chat chat;
    private Menu_right menuRight;

    public Home() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout(" fillx, filly", "0[200!]5[fill, 100%]5[200!]0", "0[fill]0"));
        this.add(new Menu_left());
        chat = new Chat();
        this.add(chat);
        menuRight = new Menu_right();
        this.add(menuRight);
        chat.setVisible(false);
    }

    public void setProfile(Model_User_Account user) {
        menuRight.setProfile(user);
        chat.setVisible(true);
    }

    public void setUser(Model_User_Account user) {
        chat.setUser(user);
        chat.setVisible(true);
    }

    public void updateUser(Model_User_Account user) {
        chat.updateUser(user);
    }

    public void setWaitGroup() {
        chat.setVisible(false);
        PublicEvent.getInstance().getEventReGroup().sendMessage("Join the group first!!!");
    }

    public void setGroup(Model_Group group) {
        chat.setGroup(group);
        chat.setVisible(true);
    }

    public void updateGroup(Model_Group group) {
        //chat.updateUser(user);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 729, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
