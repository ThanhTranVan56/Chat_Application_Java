package com.app.component;

import com.app.event.EventMessage;
import com.app.event.PublicEvent;
import com.app.model.Model_Message;
import java.awt.Color;
import javax.swing.Timer;

public class JoinGroup extends javax.swing.JPanel {

    public JoinGroup() {
        initComponents();
    }

    public void init() {

    }

    public void setSendJoin(String message) {
        if (!"So sorry".equals(message)) {
            lbError.setForeground(Color.GREEN);
            lbError.setText(message);
            txtGroupName.setText("");
            Timer timer = new Timer(5000, e -> {
                lbError.setText("");
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            lbError.setForeground(Color.BLUE);
            lbError.setText(message);
            txtGroupName.setText("");
            Timer timer = new Timer(5000, e -> {
                lbError.setText("");
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtGroupName = new javax.swing.JTextField();
        btnSendYC = new javax.swing.JButton();
        lbError = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));
        setForeground(new java.awt.Color(249, 249, 249));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tham gia Group");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Name");

        txtGroupName.setBackground(new java.awt.Color(221, 221, 221));

        btnSendYC.setBackground(new java.awt.Color(102, 255, 204));
        btnSendYC.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnSendYC.setForeground(new java.awt.Color(0, 0, 0));
        btnSendYC.setText("Gửi yêu cầu");
        btnSendYC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendYCActionPerformed(evt);
            }
        });

        lbError.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSendYC)
                    .addComponent(lbError, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGroupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(btnSendYC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendYCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendYCActionPerformed
        String groupName = txtGroupName.getText().trim();
        if (groupName.equals("")) {
            txtGroupName.grabFocus();
        } else {
            PublicEvent.getInstance().getEventReGroup().joinGroup(groupName, new EventMessage() {
                @Override
                public void callMessage(Model_Message message) {
                    if (!message.isAction()) {
                        lbError.setText(message.getMessage());
                    } else {
                        //PublicEvent.getInstance().getEventMain().initChat();
                    }
                }
            });
        }
    }//GEN-LAST:event_btnSendYCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendYC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbError;
    private javax.swing.JTextField txtGroupName;
    // End of variables declaration//GEN-END:variables
}
