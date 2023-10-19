package com.app.component;

import com.app.event.PublicEvent;
import com.app.service.Service;

public class GroupAdminOption extends javax.swing.JPanel {
    private final String UserName;
    private final String GroupName;
    public GroupAdminOption(String reName, String groupName) {
        initComponents();
        this.UserName = reName;
        this.GroupName = groupName;
        lblUName.setText(UserName);
        lblGroupName.setText(GroupName) ;       
    }
    
//    init();
    public void setName(String reName, String groupName){
        lblUName.setText(reName);
        lblGroupName.setText(groupName) ;       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblUName = new javax.swing.JLabel();
        btnNotOK = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        lblGroupName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));
        setForeground(new java.awt.Color(249, 249, 249));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Yêu cầu tham gia Group");

        lblUName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblUName.setForeground(new java.awt.Color(0, 255, 51));
        lblUName.setText("UserName");

        btnNotOK.setBackground(new java.awt.Color(255, 0, 0));
        btnNotOK.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnNotOK.setText("Từ chối");
        btnNotOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotOKActionPerformed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(0, 255, 0));
        btnOK.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnOK.setForeground(new java.awt.Color(0, 0, 0));
        btnOK.setText("Đồng ý");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        lblGroupName.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblGroupName.setForeground(new java.awt.Color(0, 255, 51));
        lblGroupName.setText("Name");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Từ :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGroupName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnNotOK)
                                .addGap(18, 18, 18)
                                .addComponent(btnOK)))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblGroupName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUName)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNotOK)
                    .addComponent(btnOK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNotOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotOKActionPerformed
        String message = "NOT@" + UserName + "@" + GroupName;
        Service.getInstance().getClient().emit("send_join_group", message);
        PublicEvent.getInstance().getEventMenuRight().deleteAdminOpton(this);
    }//GEN-LAST:event_btnNotOKActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        String message = "OK@" + UserName + "@" + GroupName;
        Service.getInstance().getClient().emit("send_join_group", message);
        PublicEvent.getInstance().getEventMenuRight().deleteAdminOpton(this);
    }//GEN-LAST:event_btnOKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNotOK;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblGroupName;
    private javax.swing.JLabel lblUName;
    // End of variables declaration//GEN-END:variables
}
