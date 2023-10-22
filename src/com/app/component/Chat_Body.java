package com.app.component;

import com.app.apps.MessageType;
import com.app.emoji.Emogi;
import com.app.model.Model_Load_Data;
import com.app.model.Model_Receive_Message;
import com.app.model.Model_Receive_Message_Group;
import com.app.model.Model_Send_Message;
import com.app.model.Model_User_Account;
import com.app.service.Service;
import com.app.swing.ScrollBar;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import javax.swing.JScrollBar;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Chat_Body extends javax.swing.JPanel {

    private final String PATH_FILE = "client_data/avata/";

    public Chat_Body() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
        repaint();
        revalidate();
    }

    public void addItemLeft(Model_Receive_Message data) {
        if (data.getMessageType() == MessageType.TEXT) {
           Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setText(data.getText());
            item.setTime();
            body.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == MessageType.EMOJI) {
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setEmoji(Emogi.getInstance().getEmoji(Integer.valueOf(data.getText())).getIcon());
            item.setTime();
            body.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == MessageType.IMAGE) {
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setText("");
            item.setImage(data.getDataImage());
            item.setTime();
            body.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == MessageType.FILE) {
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setText("");
            item.setFile(data.getDataFile());
            item.setTime();
            body.add(item, "wrap, w 100::80%");
        }
        repaint();
        revalidate();
    }

    public void addItemLeft(Model_Receive_Message_Group data) {
        if (data.getMessageType() == MessageType.IMAGE) {
           Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setText("");
            item.setImage(data.getDataImage());
            item.setTime();
            body.add(item, "wrap, w 100::80%");
        }
//        else if (data.getMessageType() == MessageType.FILE) {
//            Chat_Left item = new Chat_Left();
//            item.setText("");
//            System.out.println("fileExtension: "+data.getDataFile().getFileExtension());
//            item.setFile(data.getDataFile());
//            item.setTime();
//            body.add(item, "wrap, w 100::80%");
//        }
        repaint();
        revalidate();
    }

    public void addItemLeft(Model_Load_Data data) {
        if (data.getMessageType() == MessageType.TEXT) {
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setText(data.getText());
            item.setTime(data.getDateTime());
            body.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == MessageType.EMOJI) {
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setEmoji(Emogi.getInstance().getEmoji(Integer.parseInt(data.getText())).getIcon());
            item.setTime(data.getDateTime());
            body.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == MessageType.IMAGE) {
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setText("");
            item.setImage(data.getDataImage());
            item.setTime(data.getDateTime());
            body.add(item, "wrap, w 100::80%");
        } else if (data.getMessageType() == MessageType.FILE) {
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            if (!"".equals(getAvataPath(data.getFromUserID()))) {
                File file = new File(getAvataPath(data.getFromUserID()));
                if (file.exists()) {
                    item.setImageProfile(new ImageIcon(file.getAbsolutePath()));
                }
            }
            item.setText("");
            item.setFile(data.getDataFile());
            item.setTime(data.getDateTime());
            body.add(item, "wrap, w 100::80%");
        }
        repaint();
        revalidate();
    }

    public void addItemLeft(String text, String user, String[] image) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        //Chat_Left item = new Chat_Left();
        item.setUserProfile(user);
        item.setImage(image);
        item.setTime();
        item.setText(text);
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
    }

    public void addItemFile(String text, String user, String fileName, String fileSize) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        //Chat_Left item = new Chat_Left();
        item.setUserProfile(user);
        item.setFile(fileName, fileSize);
        item.setTime();
        item.setText(text);
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
    }

    public void addItemRight(Model_Send_Message data) {
        if (data.getMessageType() == MessageType.TEXT) {
            Chat_Right item = new Chat_Right();
            item.setText(data.getText());
            item.setTime();
            body.add(item, "wrap,al right, w 100::80%");
        } else if (data.getMessageType() == MessageType.EMOJI) {
            Chat_Right item = new Chat_Right();
            item.setEmoji(Emogi.getInstance().getEmoji(Integer.valueOf(data.getText())).getIcon());
            item.setTime();
            body.add(item, "wrap,al right, w 100::80%");
        } else if (data.getMessageType() == MessageType.IMAGE) {
            Chat_Right item = new Chat_Right();
            item.setText("");
            item.setImage(data.getFile());
            item.setTime();
            body.add(item, "wrap,al right, w 100::80%");
        } else if (data.getMessageType() == MessageType.FILE) {
            Chat_Right item = new Chat_Right();
            item.setText("");
            item.setFile(data.getFile());
            item.setTime();
            body.add(item, "wrap,al right, w 100::80%");
        }
        repaint();
        revalidate();
        scrollToBottom();
    }

    public void addItemRight(Model_Load_Data data) {
        if (data.getMessageType() == MessageType.TEXT) {
            Chat_Right item = new Chat_Right();
            item.setText(data.getText());
            item.setTime(data.getDateTime());
            body.add(item, "wrap,al right, w 100::80%");
        } else if (data.getMessageType() == MessageType.EMOJI) {
            Chat_Right item = new Chat_Right();
            item.setEmoji(Emogi.getInstance().getEmoji(Integer.valueOf(data.getText())).getIcon());
            item.setTime(data.getDateTime());
            body.add(item, "wrap,al right, w 100::80%");
        } else if (data.getMessageType() == MessageType.IMAGE) {
            Chat_Right item = new Chat_Right();
            item.setText("");
            item.setImage(data.getDataImage());
            item.setTime(data.getDateTime());
            body.add(item, "wrap,al right, w 100::80%");
        } else if (data.getMessageType() == MessageType.FILE) {
            Chat_Right item = new Chat_Right();
            item.setText("");
            item.setFile(data.getDataFile());
            item.setTime(data.getDateTime());
            body.add(item, "wrap,al right, w 100::80%");
        }
        repaint();
        revalidate();
        scrollToBottom();
    }

    public void addItemFileRight(String text, String fileName, String fileSize) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        item.setFile(fileName, fileSize);
        body.add(item, "wrap,al right, w 100::80%");
        body.repaint();
        body.revalidate();
    }

    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    public void addDate(Timestamp dateTime) {
        Chat_Date item = new Chat_Date();
        item.setDate(dateTime);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    public void clearChat() {
        body.removeAll();
        sp.setBackground(Color.WHITE);
        body.setBackground(Color.WHITE);
        repaint();
        revalidate();
    }

    private String getAvataPath(int uID) {

        List<Model_User_Account> listu = Service.getInstance().getListUser();
        for (Model_User_Account u : listu) {
            if (u.getUserID() == uID) {
                return PATH_FILE + uID + u.getUserName() + "avata.jpg";
            }
        }
        return "";
    }

    public void loadData(int UID) {
        String toID = String.valueOf(UID);
        String youID = String.valueOf(Service.getInstance().getUser().getUserID());
        String id = toID + "@" + youID;
        Service.getInstance().getClient().emit("list_data_user", id);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBackground(new java.awt.Color(255, 255, 255));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
