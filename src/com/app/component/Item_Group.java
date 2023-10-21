package com.app.component;

import com.app.event.PublicEvent;
import com.app.model.Model_Group;
import com.app.service.Service;
import io.socket.client.Ack;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Item_Group extends javax.swing.JPanel {

    public Model_Group getGroup() {
        return group;
    }

    private boolean mouseOver;
    private final Model_Group group;
    private final boolean isStatus;

    public Item_Group(Model_Group group, boolean isStatuss) {
        initComponents();
        this.group = group;
        this.isStatus = isStatuss;
        activeStatus.setActive(isStatus);
        lb.setText(group.getGroupName());
        init();
    }

    public void updateStatus(boolean status) {
        activeStatus.setActive(status);
        if (status) {
            lbStatus.setForeground(new java.awt.Color(62, 146, 49));
        } else {
            lbStatus.setForeground(new java.awt.Color(137, 137, 137));
        }
    }

    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(229, 229, 229));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(242, 242, 242));
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (mouseOver) {
                    Service.getInstance().getClient().emit("check_group_member", group.getGroupID(), new Ack() {
                        @Override
                        public void call(Object... os) {
                            boolean isaction = (boolean) os[0];
                            if (isaction) {
                                System.out.println(isaction);
                                PublicEvent.getInstance().getEventMain().selectGroup(group);
                                PublicEvent.getInstance().getEventMenuRight().setMemberIn(true,group.getGroupName());
                            } else {
                                System.out.println(isaction);
                                PublicEvent.getInstance().getEventMain().waitGroup();
                            }
                        }
                    });

                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new com.app.swing.ImageAvatar();
        lb = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        activeStatus = new com.app.swing.ActiveStatus();

        setBackground(new java.awt.Color(242, 242, 242));
        setForeground(new java.awt.Color(242, 242, 242));

        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/menu-group-40-selected.png"))); // NOI18N
        imageAvatar1.setMaximumSize(new java.awt.Dimension(35, 35));
        imageAvatar1.setMinimumSize(new java.awt.Dimension(35, 35));

        lb.setForeground(new java.awt.Color(0, 0, 0));
        lb.setText("Name");

        lbStatus.setBackground(new java.awt.Color(117, 117, 117));
        lbStatus.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(137, 137, 137));
        lbStatus.setText("Active Now");

        activeStatus.setActive(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activeStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.app.swing.ActiveStatus activeStatus;
    private com.app.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
