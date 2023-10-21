package com.app.component;

import com.app.event.EventMessage;
import com.app.event.PublicEvent;
import com.app.model.Model_Message;
import com.app.model.Model_Register_Group;
import com.app.service.Service;

public class Add_Group extends javax.swing.JPanel {

    public Add_Group() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPGroup = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNameGroup = new javax.swing.JTextField();
        btnAddGroup = new javax.swing.JButton();
        lbError = new javax.swing.JLabel();

        JPGroup.setBackground(new java.awt.Color(249, 249, 249));
        JPGroup.setForeground(new java.awt.Color(249, 249, 249));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tạo Nhóm");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Name");

        txtNameGroup.setBackground(new java.awt.Color(221, 221, 221));

        btnAddGroup.setBackground(new java.awt.Color(51, 255, 204));
        btnAddGroup.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnAddGroup.setForeground(new java.awt.Color(0, 0, 0));
        btnAddGroup.setText("Tạo");
        btnAddGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGroupActionPerformed(evt);
            }
        });

        lbError.setBackground(new java.awt.Color(255, 0, 0));
        lbError.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lbError.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout JPGroupLayout = new javax.swing.GroupLayout(JPGroup);
        JPGroup.setLayout(JPGroupLayout);
        JPGroupLayout.setHorizontalGroup(
            JPGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPGroupLayout.createSequentialGroup()
                .addGroup(JPGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPGroupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JPGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddGroup)
                            .addGroup(JPGroupLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNameGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbError, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPGroupLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2)))
                .addGap(21, 21, 21))
        );
        JPGroupLayout.setVerticalGroup(
            JPGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPGroupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNameGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddGroup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JPGroup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JPGroup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGroupActionPerformed
        String groupName = txtNameGroup.getText().trim();
        if (groupName.equals("")) {
            txtNameGroup.grabFocus();
        } else{
            Model_Register_Group data = new Model_Register_Group(groupName, Service.getInstance().getUser().getUserID());
            PublicEvent.getInstance().getEventReGroup().registerGroup(data,new EventMessage(){
                @Override
                public void callMessage(Model_Message message) {
                    if(!message.isAction()){ 
                        lbError.setText(message.getMessage());
                    } else{
                        //PublicEvent.getInstance().getEventMain().initChat();
                    }
                }
            });
        }
    }//GEN-LAST:event_btnAddGroupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPGroup;
    private javax.swing.JButton btnAddGroup;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbError;
    private javax.swing.JTextField txtNameGroup;
    // End of variables declaration//GEN-END:variables
}
