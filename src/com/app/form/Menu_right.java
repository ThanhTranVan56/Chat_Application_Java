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
import java.awt.Color;
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
    private Model_User_Account userAccount;
    private boolean isMenuOptionVisible = false;
    private boolean mouseOver;
    private boolean isjoinaddVisible;
    private boolean iseditaccount;

    public Menu_right() {
        initComponents();
        init();
    }
    private final String PATH_FILE = "client_data/avata/";

    public void init() {

        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "1[]1"));
        memAccount = new ArrayList<>();
        join_add_group.setVisible(false);
        menuOption.setVisible(false);
        lblMemberIn.setVisible(false);
        edit_Account.setVisible(false);
        //groupAdminOption2.setVisible(false);
        PublicEvent.getInstance().addEventMenuRight(new EventMenuRight() {
            @Override
            public void setOpton(boolean option) {
                isjoinaddVisible = option;
                if (isMenuOptionVisible) {
                    join_add_group.setVisible(false);
                } else {
                    join_add_group.setVisible(option);
                }

                if (!option) {
                    lblMemberIn.setVisible(false);
                    menuList.removeAll();
                }

            }

            @Override
            public void deleteAdminOpton(GroupAdminOption admin) {
                menuList.remove(admin);
                refreshMenuList();
            }

            @Override
            public void setMemberIn(boolean option, String groupName) {
                lblMemberIn.setVisible(option);
                lblgroupName.setText(groupName);
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
                join_add_group.setSendJoin(message);
            }

            @Override
            public void sendMessage(String message) {
                join_add_group.sendMessage(message);
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
        addEventSetAvata(plAvata);
        setGroupOption(plGroupOption);
        editAccount(plProfile);
        setLogOut(plLogOut);
        //setDropDown(menuOption);

    }

    public void setProfile(Model_User_Account user) {
        userAccount = user;
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

    private void setLogOut(Component com) {
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                com.setBackground(new Color(229, 229, 229));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                com.setBackground(new Color(242, 242, 242));
                mouseOver = false;
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                //System.exit(0);
                Service.getInstance().getClient().emit("user_logout", new Ack() {
                    @Override
                    public void call(Object... os) {
                        boolean isaction = (boolean) os[0];
                        if (isaction) {
                            PublicEvent.getInstance().getEventMenuLeft().logout();
                            PublicEvent.getInstance().getEventMain().reLogin();
                            isMenuOptionVisible = false;
                            menuOption.setVisible(false);
                        } else {
                            lblLogout.setText("Lỗi hệ thống!!!");
                        }
                    }
                });
            }
        });
    }

    private void setGroupOption(Component com) {
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                isMenuOptionVisible = false;
                menuOption.setVisible(false);
                isjoinaddVisible = true;
                join_add_group.setVisible(true);
                if (!iseditaccount) {
                    edit_Account.setVisible(iseditaccount);
                } else {
                    edit_Account.setVisible(false);
                    iseditaccount = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                com.setBackground(new Color(229, 229, 229));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                com.setBackground(new Color(242, 242, 242));
                mouseOver = false;
            }
        ;

    }

    );
    }
    private void editAccount(Component com) {
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                Service.getInstance().getClient().emit("get_username_pass", userAccount.getUserID(), new Ack() {
                    @Override
                    public void call(Object... os) {
                        String namepass = (String) os[0];
                        String[] parts = namepass.split("@");
                        String username = parts[0];
                        String password = parts[1];
                        edit_Account.setAccount(username, password);
                    }
                });
                iseditaccount = true;
                edit_Account.setVisible(true);
                isMenuOptionVisible = false;
                menuOption.setVisible(false);
                if (!isjoinaddVisible) {
                    join_add_group.setVisible(isjoinaddVisible);
                } else {
                    join_add_group.setVisible(false);
                    isjoinaddVisible = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                com.setBackground(new Color(229, 229, 229));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                com.setBackground(new Color(242, 242, 242));
                mouseOver = false;
            }
        });
    }

    private void addEventSetAvata(Component com) {
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
                isMenuOptionVisible = false;
                menuOption.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                com.setBackground(new Color(229, 229, 229));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                com.setBackground(new Color(242, 242, 242));
                mouseOver = false;
            }

        });
    }

    private void addEvent(Component com) {
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));

        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //isUserOptionVisible
                if (!isMenuOptionVisible) {
                    isMenuOptionVisible = true;
                    menuOption.setVisible(true);
                } else {
                    isMenuOptionVisible = false;
                    menuOption.setVisible(false);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //lblSetImage.setText("My project");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                //UserOption.setVisible(false);
                //lblSetImage.setText("");
                //repaint();
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        menuOption = new javax.swing.JPanel();
        plProfile = new javax.swing.JPanel();
        lblProfile = new javax.swing.JLabel();
        plAvata = new javax.swing.JPanel();
        lblAvata = new javax.swing.JLabel();
        plGroupOption = new javax.swing.JPanel();
        lblGroupOption = new javax.swing.JLabel();
        plLogOut = new javax.swing.JPanel();
        lblLogOut = new javax.swing.JLabel();
        edit_Account = new com.app.component.Edit_Account();
        join_add_group = new com.app.component.Join_Add_Group();
        jPanel1 = new javax.swing.JPanel();
        imageAvatar1 = new com.app.swing.ImageAvatar();
        lblUserName = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        lblMemberIn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblgroupName = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));
        setForeground(new java.awt.Color(242, 242, 242));

        backgroud.setBackground(new java.awt.Color(249, 249, 249));
        backgroud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBackground(new java.awt.Color(221, 221, 221));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuOption.setBackground(new java.awt.Color(0, 255, 204));
        menuOption.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(246, 243, 243), new java.awt.Color(246, 244, 244)));
        menuOption.setForeground(new java.awt.Color(0, 255, 204));
        menuOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfile.setBackground(new java.awt.Color(0, 0, 0));
        lblProfile.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblProfile.setForeground(new java.awt.Color(0, 0, 0));
        lblProfile.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProfile.setText("Cập nhật tài khoản");

        javax.swing.GroupLayout plProfileLayout = new javax.swing.GroupLayout(plProfile);
        plProfile.setLayout(plProfileLayout);
        plProfileLayout.setHorizontalGroup(
            plProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plProfileLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblProfile)
                .addGap(10, 10, 10))
        );
        plProfileLayout.setVerticalGroup(
            plProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plProfileLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuOption.add(plProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 120, 20));

        lblAvata.setBackground(new java.awt.Color(0, 0, 0));
        lblAvata.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblAvata.setForeground(new java.awt.Color(0, 0, 0));
        lblAvata.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAvata.setText("Cập nhật avata");

        javax.swing.GroupLayout plAvataLayout = new javax.swing.GroupLayout(plAvata);
        plAvata.setLayout(plAvataLayout);
        plAvataLayout.setHorizontalGroup(
            plAvataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plAvataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAvata, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        plAvataLayout.setVerticalGroup(
            plAvataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plAvataLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAvata, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuOption.add(plAvata, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 20));

        lblGroupOption.setBackground(new java.awt.Color(0, 0, 0));
        lblGroupOption.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblGroupOption.setForeground(new java.awt.Color(0, 0, 0));
        lblGroupOption.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGroupOption.setText("Group");

        javax.swing.GroupLayout plGroupOptionLayout = new javax.swing.GroupLayout(plGroupOption);
        plGroupOption.setLayout(plGroupOptionLayout);
        plGroupOptionLayout.setHorizontalGroup(
            plGroupOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plGroupOptionLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(lblGroupOption, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        plGroupOptionLayout.setVerticalGroup(
            plGroupOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plGroupOptionLayout.createSequentialGroup()
                .addComponent(lblGroupOption, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menuOption.add(plGroupOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 120, 20));

        lblLogOut.setBackground(new java.awt.Color(0, 0, 0));
        lblLogOut.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLogOut.setForeground(new java.awt.Color(0, 0, 0));
        lblLogOut.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblLogOut.setText("Đăng xuất");

        javax.swing.GroupLayout plLogOutLayout = new javax.swing.GroupLayout(plLogOut);
        plLogOut.setLayout(plLogOutLayout);
        plLogOutLayout.setHorizontalGroup(
            plLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plLogOutLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(lblLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        plLogOutLayout.setVerticalGroup(
            plLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLogOutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuOption.add(plLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 120, -1));

        jLayeredPane1.add(menuOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 120, -1));
        jLayeredPane1.add(edit_Account, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 210, -1));
        jLayeredPane1.add(join_add_group, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-test-account-45.png"))); // NOI18N
        imageAvatar1.setMinimumSize(new java.awt.Dimension(35, 35));

        lblUserName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(0, 0, 0));
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserName.setText("User Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblUserName)
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLayeredPane1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 50));

        lblLogout.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblLogout.setForeground(new java.awt.Color(255, 0, 0));
        jLayeredPane1.add(lblLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 140, 20));

        backgroud.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 220));

        lblMemberIn.setBackground(new java.awt.Color(249, 249, 249));
        lblMemberIn.setForeground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Member in");

        lblgroupName.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblgroupName.setForeground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout lblMemberInLayout = new javax.swing.GroupLayout(lblMemberIn);
        lblMemberIn.setLayout(lblMemberInLayout);
        lblMemberInLayout.setHorizontalGroup(
            lblMemberInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblMemberInLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblgroupName, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        lblMemberInLayout.setVerticalGroup(
            lblMemberInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblMemberInLayout.createSequentialGroup()
                .addGroup(lblMemberInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblgroupName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        backgroud.add(lblMemberIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 210, 20));

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

        backgroud.add(sp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 236, 210, 290));

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
    private com.app.component.Edit_Account edit_Account;
    private com.app.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private com.app.component.Join_Add_Group join_add_group;
    private javax.swing.JLabel lblAvata;
    private javax.swing.JLabel lblGroupOption;
    private javax.swing.JLabel lblLogOut;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JPanel lblMemberIn;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblgroupName;
    private javax.swing.JLayeredPane menuList;
    private javax.swing.JPanel menuOption;
    private javax.swing.JPanel plAvata;
    private javax.swing.JPanel plGroupOption;
    private javax.swing.JPanel plLogOut;
    private javax.swing.JPanel plProfile;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
