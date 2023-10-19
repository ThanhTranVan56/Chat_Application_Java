package com.app.component;

import javax.swing.Timer;

public class Menu_Right_Option extends javax.swing.JPanel {


    public Menu_Right_Option() {
        initComponents();
        init();
    }
    private void init() {
        sendMessage.setVisible(true);
        addGroup1.setVisible(false);
        joinGroup1.setVisible(false);
    }
    
    public void setSendJoin(String message){
        joinGroup1.setSendJoin(message);
    }
    
    public void sendMessage(String message){
        sendMessage.setVisible(true);
        lblMessage.setText(message);
        Timer timer = new Timer(2000, e -> {
            lblMessage.setText("");
        });
        timer.setRepeats(false);
        timer.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        btnJoinGroup = new javax.swing.JButton();
        btnAddGroup = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        sendMessage = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();
        joinGroup1 = new com.app.component.JoinGroup();
        addGroup1 = new com.app.component.AddGroup();

        setBackground(new java.awt.Color(242, 242, 242));
        setForeground(new java.awt.Color(242, 242, 242));

        btnJoinGroup.setBackground(new java.awt.Color(0, 204, 255));
        btnJoinGroup.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnJoinGroup.setForeground(new java.awt.Color(0, 0, 0));
        btnJoinGroup.setText("Join Group");
        btnJoinGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinGroupActionPerformed(evt);
            }
        });

        btnAddGroup.setBackground(new java.awt.Color(0, 255, 255));
        btnAddGroup.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnAddGroup.setForeground(new java.awt.Color(51, 51, 51));
        btnAddGroup.setText("Add Group");
        btnAddGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGroupActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayout(new java.awt.CardLayout());

        sendMessage.setBackground(new java.awt.Color(249, 249, 249));
        sendMessage.setForeground(new java.awt.Color(249, 249, 249));

        lblMessage.setBackground(new java.awt.Color(255, 255, 255));
        lblMessage.setFont(new java.awt.Font("Times New Roman", 2, 13)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout sendMessageLayout = new javax.swing.GroupLayout(sendMessage);
        sendMessage.setLayout(sendMessageLayout);
        sendMessageLayout.setHorizontalGroup(
            sendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sendMessageLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        sendMessageLayout.setVerticalGroup(
            sendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sendMessageLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jLayeredPane2.add(sendMessage, "card4");
        jLayeredPane2.add(joinGroup1, "card4");

        addGroup1.setBackground(new java.awt.Color(242, 242, 242));
        addGroup1.setForeground(new java.awt.Color(242, 242, 242));
        jLayeredPane2.add(addGroup1, "card3");

        jLayeredPane1.setLayer(btnJoinGroup, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnAddGroup, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnJoinGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddGroup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLayeredPane2)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnJoinGroup)
                    .addComponent(btnAddGroup))
                .addGap(2, 2, 2)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGroupActionPerformed
        addGroup1.setVisible(true);
        joinGroup1.setVisible(false);
        sendMessage.setVisible(false);
    }//GEN-LAST:event_btnAddGroupActionPerformed

    private void btnJoinGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinGroupActionPerformed
        addGroup1.setVisible(false);
        joinGroup1.setVisible(true);
        sendMessage.setVisible(false);
    }//GEN-LAST:event_btnJoinGroupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.app.component.AddGroup addGroup1;
    private javax.swing.JButton btnAddGroup;
    private javax.swing.JButton btnJoinGroup;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private com.app.component.JoinGroup joinGroup1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPanel sendMessage;
    // End of variables declaration//GEN-END:variables
}
