package com.app.component;

import com.app.apps.MessageType;
import com.app.emoji.Emogi;
import com.app.emoji.Model_Emoji;
import com.app.event.PublicEvent;
import com.app.main.Main;
import com.app.model.Model_Group;
import com.app.model.Model_Send_Message;
import com.app.model.Model_User_Account;
import com.app.service.Service;
import com.app.swing.ScrollBar;
import com.app.swing.WrapLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import net.miginfocom.swing.MigLayout;

public class Panel_More extends javax.swing.JPanel {

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    public void setGroup(Model_Group group) {
        this.group = group;
    }

    public Model_User_Account getUser() {
        return user;
    }

    public Model_Group getGroup() {
        return group;
    }

    private Model_User_Account user;
    private Model_Group group;

    public Panel_More() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx"));
        panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.LINE_AXIS));
        panelHeader.add(getButtonImage());
        panelHeader.add(getButtonFile());
        panelHeader.add(getEmoijiStyle1());
        panelHeader.add(getEmoijiStyle2());
        add(panelHeader, "w 100%, h 30!, wrap");

        panelDetail = new JPanel();
        panelDetail.setLayout(new WrapLayout(WrapLayout.LEFT));  //use warp layout
        JScrollPane ch = new JScrollPane(panelDetail);
        ch.setBorder(null);
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ch.setVerticalScrollBar(new ScrollBar());
        add(ch, "w 100%, h 100%");
    }

    private JButton getButtonImage() {
        Option_Button cmd = new Option_Button();
        cmd.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/image.png")));

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(true);
                ch.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isDirectory() || isImageFile(file);
                    }

                    @Override
                    public String getDescription() {
                        return "Image File";
                    }
                });
                int option = ch.showOpenDialog(Main.getFrames()[0]);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File files[] = ch.getSelectedFiles();
                    try {
                        for (File file : files) {
                            if (user != null) {
                                Model_Send_Message message = new Model_Send_Message(MessageType.IMAGE, Service.getInstance().getUser().getUserID(), user.getUserID(), "");
                                Service.getInstance().addFile(file, message);
                                PublicEvent.getInstance().getEventChat().sendMessage(message);
                            } else {
                                System.out.println("send to group: " + group.getGroupName());
                                Model_Send_Message message = new Model_Send_Message(MessageType.IMAGE, Service.getInstance().getUser().getUserID(), group.getGroupID(), "");
                                Service.getInstance().addFileGroup(file, message);
                                PublicEvent.getInstance().getEventChat().sendMessage(message);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }

        });
        return cmd;
    }

    private JButton getButtonFile() {
        Option_Button cmd = new Option_Button();
        cmd.setIcon(new ImageIcon(getClass().getResource("/com/app/icon/link1.png")));

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(true);
                int option = ch.showOpenDialog(Main.getFrames()[0]);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File files[] = ch.getSelectedFiles();
                    try {
                        for (File file : files) {
                            if (user != null) {
                                Model_Send_Message message = new Model_Send_Message(MessageType.FILE, Service.getInstance().getUser().getUserID(), user.getUserID(), "");
                                Service.getInstance().addFile(file, message);
                                PublicEvent.getInstance().getEventChat().sendMessage(message);
                            } else {
                                Model_Send_Message message = new Model_Send_Message(MessageType.FILE, Service.getInstance().getUser().getUserID(), group.getGroupID(), "");
                                Service.getInstance().addFileGroup(file, message);
                                PublicEvent.getInstance().getEventChat().sendMessage(message);
                            }

                        }
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }

        });
        return cmd;
    }

    private JButton getEmoijiStyle1() {
        Option_Button cmd = new Option_Button();
        cmd.setIcon(Emogi.getInstance().getEmoji(1).toSize(25, 25).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for (Model_Emoji d : Emogi.getInstance().getStyle1()) {
                    panelDetail.add(getButton(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }

        });
        return cmd;
    }

    private JButton getEmoijiStyle2() {
        Option_Button cmd = new Option_Button();
        cmd.setIcon(Emogi.getInstance().getEmoji(2).toSize(25, 25).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for (Model_Emoji d : Emogi.getInstance().getStyle21()) {
                    panelDetail.add(getButton(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }

        });
        return cmd;
    }

    private JButton getButton(Model_Emoji data) {
        JButton cmd = new JButton(data.getIcon());
        cmd.setName(data.getId() + "");
        cmd.setBorder(new EmptyBorder(1, 1, 1, 1));
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setContentAreaFilled(false);
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (user != null) {
                    Model_Send_Message message = new Model_Send_Message(MessageType.EMOJI, Service.getInstance().getUser().getUserID(), user.getUserID(), data.getId() + "");
                    sendMessage(message);
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                } else {
                    Model_Send_Message message = new Model_Send_Message(MessageType.EMOJI, Service.getInstance().getUser().getUserID(), group.getGroupID(), data.getId() + "");
                    sendMessageToGroup(message);
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                }

            }

        });
        return cmd;
    }

    private void sendMessage(Model_Send_Message data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }

    private void sendMessageToGroup(Model_Send_Message data) {
        Service.getInstance().getClient().emit("send_to_group", data.toJsonObject());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(244, 244, 244));
        setForeground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearSelected() {
        for (Component c : panelHeader.getComponents()) {
            if (c instanceof Option_Button) {
                ((Option_Button) c).setSelected(false);
            }
        }
    }

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }
    private JPanel panelHeader;
    private JPanel panelDetail;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
