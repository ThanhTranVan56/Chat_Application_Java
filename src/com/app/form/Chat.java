package com.app.form;

import com.app.component.Chat_Body;
import com.app.component.Chat_Bottom;
import com.app.component.Chat_Title;
import com.app.event.EventChat;
import com.app.event.PublicEvent;
import com.app.model.Model_Load_Data;
import com.app.model.Model_Receive_Message;
import com.app.model.Model_Send_Message;
import com.app.model.Model_User_Account;
import net.miginfocom.swing.MigLayout;

public class Chat extends javax.swing.JPanel {

    private Chat_Title chatTitle;
    private Chat_Body chatBody;
    private Chat_Bottom chatBottom;

    public Chat() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, fill]0[shrink 0]"));
        chatTitle = new Chat_Title();
        chatBody = new Chat_Body();
        chatBottom = new Chat_Bottom();
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(Model_Send_Message data) {
                chatBody.addItemRight(data);
            }

            @Override
            public void receiveMessage(Model_Receive_Message data) {
                if (chatTitle.getUser().getUserID() == data.getFromUserID()) {
                    chatBody.addItemLeft(data);
                }
            }

            @Override
            public void loadData(Model_Load_Data data, boolean type) {
               if(type){
                   chatBody.addItemRight(data);
               }
               else{
                   chatBody.addItemLeft(data);
               }
            }
        });
        add(chatTitle, "wrap");
        add(chatBody, "wrap");
        add(chatBottom, "h ::30%");

    }

    public void setUser(Model_User_Account user) {
        chatTitle.setUserName(user);
        chatBottom.setUser(user);
        chatBody.clearChat();  //update chat : neu duoc
        chatBody.loadData(user.getUserID());
    }

    public void updateUser(Model_User_Account user) {
        chatTitle.updateUser(user);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
