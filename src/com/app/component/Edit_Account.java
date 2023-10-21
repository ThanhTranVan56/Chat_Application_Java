package com.app.component;

import com.app.event.EventMessage;
import com.app.event.PublicEvent;
import com.app.service.Service;
import io.socket.client.Ack;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Edit_Account extends javax.swing.JPanel {

    private boolean isShowPass = false;

    public Edit_Account() {
        initComponents();
    }

    public void setAccount(String uname, String pass) {
        txtUserName.setText(uname);
        txtPassword.setText(pass);
        txtPassword.setEchoChar('*');
        isShowPass = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnShowPass = new javax.swing.JButton();
        btnUpdateProfile = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cập nhật tài khoản");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("UserName");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Password");

        txtUserName.setBackground(new java.awt.Color(204, 204, 204));

        txtPassword.setBackground(new java.awt.Color(204, 204, 204));

        btnShowPass.setBackground(new java.awt.Color(249, 249, 249));
        btnShowPass.setForeground(new java.awt.Color(249, 249, 249));
        btnShowPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/showpass.png"))); // NOI18N
        btnShowPass.setBorder(null);
        btnShowPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnShowPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPassActionPerformed(evt);
            }
        });

        btnUpdateProfile.setBackground(new java.awt.Color(51, 255, 204));
        btnUpdateProfile.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnUpdateProfile.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdateProfile.setText("Cập nhật");
        btnUpdateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProfileActionPerformed(evt);
            }
        });

        lblMessage.setBackground(new java.awt.Color(249, 249, 249));
        lblMessage.setFont(new java.awt.Font("Times New Roman", 2, 13)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnUpdateProfile)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUserName))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lblMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(btnShowPass)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnShowPass)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProfileActionPerformed
        String userName = txtUserName.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());
        if (userName.equals("")) {
            txtUserName.grabFocus();
        } else if (password.equals("")) {
            txtPassword.grabFocus();
        } else {
            String data = String.valueOf(Service.getInstance().getUser().getUserID()) + "@" + userName + "@" + password;
            Service.getInstance().getClient().emit("update_username_pass", data, new Ack() {
                @Override
                public void call(Object... os) {
                    boolean isaction = (boolean) os[0];
                    if (isaction) {
                        lblMessage.setText("Cập nhật thành công!");
                        Timer timer = new Timer(2000, e -> {
                            lblMessage.setText("");
                        });
                        timer.setRepeats(false);
                        timer.start();
                    } else{
                        {
                        lblMessage.setText("Cập nhật thất bại!");
                        Timer timer = new Timer(2000, e -> {
                            lblMessage.setText("");
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    }
                }
            });
        }
    }//GEN-LAST:event_btnUpdateProfileActionPerformed

    private void btnShowPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassActionPerformed
        if (!isShowPass) {
            txtPassword.setEchoChar((char) 0);
            isShowPass = true;
            btnShowPass.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/notshowpass.png")));
        } else {
            txtPassword.setEchoChar('*');
            isShowPass = false;
            btnShowPass.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/showpass.png")));
        }
    }//GEN-LAST:event_btnShowPassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJoinGroup;
    private javax.swing.JButton btnJoinGroup1;
    private javax.swing.JButton btnJoinGroup2;
    private javax.swing.JButton btnShowPass;
    private javax.swing.JButton btnUpdateProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
