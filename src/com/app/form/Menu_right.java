package com.app.form;

import com.app.component.GroupAdminOption;
import com.app.component.Item_Peoples;
import com.app.event.EventMenuRight;
import com.app.event.EventMessage;
import com.app.event.EventReGroup;
import com.app.event.PublicEvent;
import com.app.main.Main;
import com.app.model.Model_Group;
import com.app.model.Model_Message;
import com.app.model.Model_Register_Group;
import com.app.model.Model_User_Account;
import com.app.service.Service;
import com.app.swing.ScrollBar;
import io.socket.client.Ack;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import com.app.swing.blurhash.FileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import net.miginfocom.swing.MigLayout;

public class Menu_right extends javax.swing.JPanel {

    private List<Model_User_Account> memAccount;

    public Menu_right() {
        initComponents();
        init();
    }
    private final String PATH_FILE = "client_data/avata/";

    public void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "1[]1"));
        memAccount = new ArrayList<>();
        menu_Right_Option1.setVisible(false);
        //groupAdminOption2.setVisible(false);
        PublicEvent.getInstance().addEventMenuRight(new EventMenuRight() {
            @Override
            public void setOpton(boolean option) {
                menu_Right_Option1.setVisible(option);
            }

            @Override
            public void deleteAdminOpton(GroupAdminOption admin) {
                menuList.remove(admin);
                refreshMenuList();
            }
        });
        PublicEvent.getInstance().addEventReGroup(new EventReGroup() {
            @Override
            public void registerGroup(Model_Register_Group data, EventMessage message) {
                Service.getInstance().getClient().emit("registerGroup", data.toJsonObject(), new Ack() {
                    @Override
                    public void call(Object... os) {
                        if (os.length > 0) {
                            Model_Message ms = new Model_Message((boolean) os[0], os[1].toString());
                            if (ms.isAction()) {
                                //Model_Group group = new Model_Group(os[2]);
                                //Service.getInstance().setGroup(group);
                            }
                            message.callMessage(ms);
                            // call message back when don register
                        }
                    }

                });
            }

            @Override
            public void joinGroup(String groupName, EventMessage message) {
                Service.getInstance().getClient().emit("user_join_group", groupName, new Ack() {
                    @Override
                    public void call(Object... os) {
                        if (os.length > 0) {
                            Model_Message ms = new Model_Message((boolean) os[0], os[1].toString());
                            if (ms.isAction()) {
                                //Model_Group group = new Model_Group(os[2]);
                                //Service.getInstance().setGroup(group);
                            }
                            message.callMessage(ms);
                            // call message back when don register
                        }
                    }

                });
            }

            @Override
            public void requestJoinGroup(String reName, String groupName) {
                menuList.add(new GroupAdminOption(reName, groupName), "wrap");
                refreshMenuList();
            }

            @Override
            public void sendJoinGroup(String message) {
                menu_Right_Option1.setSendJoin(message);
            }

            @Override
            public void sendMessage(String message) {
                menu_Right_Option1.sendMessage(message);
            }

            @Override
            public void memberInGroup(List<Model_User_Account> list) {
                menuList.removeAll(); 
                for (Model_User_Account l : list) {
                    memAccount.add(l);
                    menuList.add(new Item_Peoples(l), "wrap");
                    refreshMenuList();
                }

            }

        });

        addEvent(imageAvatar1);
    }

    private void showMessage() {
        menuList.removeAll();
        for (Model_User_Account d : memAccount) {
            menuList.add(new Item_Peoples(d), "wrap");
        }
        refreshMenuList();
    }

    public void setProfile(Model_User_Account user) {
        lblUserName.setText(user.getUserName());
        if ("1".equals(user.getImage())) {
            String filePath = PATH_FILE + user.getUserID() + user.getUserName() + "avata.jpg";
            File file = new File(filePath);
            if (file.exists()) {
                imageAvatar1.setImage(new ImageIcon(file.getAbsolutePath()));
                repaint();
            } else {
                getAvata(user);
            }
        }
    }

    private void getAvata(Model_User_Account user) {
        Service.getInstance().getClient().emit("get_avata_user", user.getUserID(), new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    byte[] data = (byte[]) os[0];
                    String filePath = PATH_FILE + user.getUserID() + user.getUserName() + "avata.jpg";
                    try (FileOutputStream fos = new FileOutputStream(filePath)) {
                        fos.write(data);
                        System.out.println("File saved successfully.");
                    } catch (IOException e) {
                        System.err.println("Error saving file: " + e.getMessage());
                    }
                    imageAvatar1.setImage(new ImageIcon(data));
                    repaint();
                }
            }
        });
    }

    private void addEvent(Component com) {
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));

        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    System.out.println("Set Image click");
                    JFileChooser ch = new JFileChooser();
                    FileChooser preview = new FileChooser();
                    ch.setAccessory(preview);
                    ch.addPropertyChangeListener(preview);
                    ch.setFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File file) {
                            String name = file.getName();
                            return file.isDirectory() || name.endsWith(".png") || name.endsWith(".PNG") || name.endsWith("jpg") || name.endsWith("JPG");
                        }

                        @Override
                        public String getDescription() {
                            return "png,jpg";
                        }
                    });
                    int opt = ch.showOpenDialog(Main.getFrames()[0]);
                    if (opt == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = ch.getSelectedFile();
                            Service.getInstance().addAvata(file);
                        } catch (IOException ex) {
                            Logger.getLogger(Menu_right.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ImageIcon image = new ImageIcon(ch.getSelectedFile().getAbsolutePath());
                        imageAvatar1.setImage(image);
                        repaint();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblSetImage.setText("Set Avata");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblSetImage.setText("");
            }
        });
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroud = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        imageAvatar1 = new com.app.swing.ImageAvatar();
        lblUserName = new javax.swing.JLabel();
        lblSetImage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        menu_Right_Option1 = new com.app.component.Menu_Right_Option();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));
        setForeground(new java.awt.Color(242, 242, 242));

        backgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));
        jPanel1.setForeground(new java.awt.Color(249, 249, 249));
        jPanel1.setLayout(null);

        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-test-account-45.png"))); // NOI18N
        imageAvatar1.setMinimumSize(new java.awt.Dimension(35, 35));
        jPanel1.add(imageAvatar1);
        imageAvatar1.setBounds(0, 0, 70, 50);

        lblUserName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(0, 0, 0));
        lblUserName.setText("User Name");
        jPanel1.add(lblUserName);
        lblUserName.setBounds(70, 20, 130, 20);

        lblSetImage.setBackground(new java.awt.Color(0, 255, 51));
        lblSetImage.setForeground(new java.awt.Color(0, 255, 0));
        jPanel1.add(lblSetImage);
        lblSetImage.setBounds(10, 60, 60, 20);

        backgroud.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 231, 52));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_Right_Option1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(menu_Right_Option1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        backgroud.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 51, 210, 160));

        sp.setBackground(new java.awt.Color(242, 242, 242));
        sp.setBorder(null);
        sp.setForeground(new java.awt.Color(242, 242, 242));
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setBackground(new java.awt.Color(242, 242, 242));
        menuList.setForeground(new java.awt.Color(242, 242, 242));
        menuList.setOpaque(true);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        backgroud.add(sp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 206, 210, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroud, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroud;
    private com.app.swing.ImageAvatar imageAvatar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblSetImage;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLayeredPane menuList;
    private com.app.component.Menu_Right_Option menu_Right_Option1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
