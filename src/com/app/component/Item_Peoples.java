package com.app.component;

import com.app.event.PublicEvent;
import com.app.model.Model_User_Account;
import com.app.service.Service;
import com.app.swing.blurhash.BlurHash;
import io.socket.client.Ack;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Item_Peoples extends javax.swing.JPanel {

    public Model_User_Account getUser() {
        return user;
    }

    private boolean mouseOver;
    private final Model_User_Account user;
    private final String PATH_FILE = "client_data/avata/";

    public Item_Peoples(Model_User_Account user) {
        initComponents();
        this.user = user;
        lb.setText(user.getUserName());
        activeStatus.setActive(user.isStatus());
        if ("1".equals(user.getImage())) {
            String filePath = PATH_FILE + user.getUserID() + user.getUserName() + "avata.jpg";
            File file = new File(filePath);
            if (file.exists()) {
                imageAvatar1.setImage(new ImageIcon(file.getAbsolutePath()));
                repaint();
            } else {
                getAvata(user.getUserID());
            }
           

        }
        if (user.isStatus()) {
            lbStatus.setForeground(new java.awt.Color(62, 146, 49));
        }
        init();
    }

    private void getAvata(int userID) {
        Service.getInstance().getClient().emit("get_avata_user", userID, new Ack() {
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

    public void updateStatus() {
        activeStatus.setActive(user.isStatus());
        if(user.isStatus())
            lbStatus.setForeground(new java.awt.Color(62, 146, 49));
        else
            lbStatus.setForeground(new java.awt.Color(137,137,137));
    }
    public void updateAvata(int userID) {
        Service.getInstance().getClient().emit("get_avata_user", userID, new Ack() {
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

    private void init() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            //== true && (user.getUserID()!= Service.getInstance().getUser().getUserID())
            @Override
            public void mouseReleased(MouseEvent me) {
                if (mouseOver ) {
                    PublicEvent.getInstance().getEventMain().selectUser(user);
                }
            }
        });
    }

    public static ImageIcon convertToImageIcon(String blurHash) throws IOException {
        int width = 250; // Độ rộng mong muốn của ảnh
        int height = 300; // Chiều cao mong muốn của ảnh
        double punch = 1.0; // Giá trị punch (tùy chọn)
        int bufferedImageType = BufferedImage.TYPE_INT_ARGB; // Loại BufferedImage (tùy chọn)
        System.out.println("duoi:  " + blurHash);
        BufferedImage bufferedImage = BlurHash.decodeAndDraw(blurHash, width, height, punch, bufferedImageType);
        String filePath = "client_data/avata.png";
        saveImage(bufferedImage, filePath);
        return new ImageIcon(bufferedImage);
    }

    public static void saveImage(BufferedImage image, String filePath) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);
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

        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/app/icon/icons8-test-account-45.png"))); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(activeStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
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
