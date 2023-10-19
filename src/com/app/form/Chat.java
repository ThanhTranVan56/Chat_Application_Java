package com.app.form;

import com.app.component.Chat_Body;
import com.app.component.Chat_Bottom;
import com.app.component.Chat_Title;
import com.app.event.EventChat;
import com.app.event.PublicEvent;
import com.app.model.Model_Group;
import com.app.model.Model_Load_Data;
import com.app.model.Model_Receive_Message;
import com.app.model.Model_Receive_Message_Group;
import com.app.model.Model_Send_Message;
import com.app.model.Model_User_Account;
import net.miginfocom.swing.MigLayout;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

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
            public void receiveMessageGroup(Model_Receive_Message data, int groupID) {
                chatBody.addItemLeft(data);

            }

            @Override
            public void receiveImageGroup(Model_Receive_Message_Group data) {
                chatBody.addItemLeft(data);
            }

            @Override
            public void loadData(Model_Load_Data data, boolean type) {
                if (type) {
                    chatBody.addItemRight(data);
                } else {
                    chatBody.addItemLeft(data);
                }
            }

            @Override
            public void loadDateTime(Timestamp datetime, Timestamp nextDateTime) {
                datetime = setTimeZone(datetime);
                nextDateTime = setTimeZone(nextDateTime);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date1 = dateFormat.format(datetime);
                String date2 = dateFormat.format(nextDateTime);
                System.out.println(date1);
                System.out.println(date2);
                int comparison = date1.compareTo(date2);
                if (comparison < 0) {
                    chatBody.addDate(date1);
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
        chatBody.clearChat();
        chatBody.loadData(user.getUserID());
    }

    public void updateUser(Model_User_Account user) {
        chatTitle.updateUser(user);
    }

    public void setGroup(Model_Group group) {
        chatTitle.setGroupName(group);
        chatBottom.setGroup(group);
        chatBody.clearChat();
        //chatBody.loadData(user.getUserID());
    }

    private Timestamp setTimeZone(Timestamp Times) {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.setTime(Times);
        Timestamp adjustedTimestamp = new Timestamp(calendar.getTimeInMillis());
        adjustedTimestamp.setNanos(0);
        return adjustedTimestamp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 0, 0));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
