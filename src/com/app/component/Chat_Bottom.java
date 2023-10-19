package com.app.component;

import com.app.apps.MessageType;
import com.app.event.PublicEvent;
import com.app.model.Model_Group;
import com.app.model.Model_Send_Message;
import com.app.model.Model_User_Account;
import com.app.service.Service;
import com.app.swing.JIMSendTextPane;
import com.app.swing.ScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Chat_Bottom extends javax.swing.JPanel {

    private JButton cmdMore;

    public void setUser(Model_User_Account user) {
        this.user = user;
        panelMore.setUser(user);
        panelMore.setVisible(false);
        mig.setComponentConstraints(panelMore, "dock south, h 0!");
        cmdMore.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/more-disable.png")));
        revalidate();
    }

    public void setGroup(Model_Group group) {
        this.group = group;
        panelMore.setGroup(group);
        panelMore.setVisible(false);
        mig.setComponentConstraints(panelMore, "dock south, h 0!");
        cmdMore.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/more-disable.png")));
        revalidate();
    }

    public Model_User_Account getUser() {
        return user;
    }

    public Model_Group getGroup() {
        return group;
    }

    private Model_User_Account user;
    private Model_Group group;

    public Chat_Bottom() {
        initComponents();
        cmdMore = new JButton();
        init();

    }

    private void init() {
        mig = new MigLayout("fillx, filly", "0[fill]0[]0[]1", "1[fill]1[]0");
        setLayout(mig);
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
                if (ke.getKeyChar() == 10 && ke.isShiftDown()) {
                    //user press Shift + enter
                    eventSend(txt);
                }
            }
        });
        txt.setBorder(new EmptyBorder(5, 5, 5, 5));
        txt.setHintText("Write Message Here ...");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(242, 242, 242));
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]3[]0", "0[bottom]0"));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(30, 28));
        JButton cmd = new JButton();
        cmd.setBorder(null);

        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/send1.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ar) {
                eventSend(txt);
            }
        });
        cmdMore.setBorder(null);
        cmdMore.setContentAreaFilled(false);
        cmdMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMore.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/more-disable.png")));
        cmdMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ar) {
                if (panelMore.isVisible()) {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/more-disable.png")));
                    panelMore.setVisible(false);
                    mig.setComponentConstraints(panelMore, "dock south, h 0!");
                    revalidate();
                } else {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/more.png")));
                    panelMore.setVisible(true);
                    mig.setComponentConstraints(panelMore, "dock south, h 170!");
                    revalidate();
                }
            }
        });
        panel.add(cmdMore);
        panel.add(cmd);
        add(panel, "wrap");

        panelMore = new Panel_More();
        //panelMore.setBackground(Color.WHITE);
        panelMore.setVisible(false);
        add(panelMore, "dock south, h 0!"); //set height 0
    }

    private void eventSend(JIMSendTextPane txt) {
        String text = txt.getText().trim();
        if (!text.equals("")) {
            if (user != null) {
                Model_Send_Message message = new Model_Send_Message(MessageType.TEXT, Service.getInstance().getUser().getUserID(), user.getUserID(), text);
                send(message);
                PublicEvent.getInstance().getEventChat().sendMessage(message);
                txt.setText("");
                txt.grabFocus();
                refresh();
            } else {
                Model_Send_Message message = new Model_Send_Message(MessageType.TEXT, Service.getInstance().getUser().getUserID(), group.getGroupID(), text);
                sendToGroup(message);
                PublicEvent.getInstance().getEventChat().sendMessage(message);
                txt.setText("");
                txt.grabFocus();
                refresh();
            }

        } else {
            txt.grabFocus();
        }
    }

    private void send(Model_Send_Message data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }
    
    private void sendToGroup(Model_Send_Message data) {
        Service.getInstance().getClient().emit("send_to_group", data.toJsonObject());
    }

    private void refresh() {
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(229, 229, 229));
        setForeground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private MigLayout mig;
    private Panel_More panelMore;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
