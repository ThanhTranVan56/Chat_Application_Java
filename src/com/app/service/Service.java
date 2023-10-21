package com.app.service;

import com.app.event.EventFileReceiver;
import com.app.event.PublicEvent;
import com.app.model.Model_Avata_Sender;
import com.app.model.Model_File_Receiver;
import com.app.model.Model_File_Sender;
import com.app.model.Model_Group;
import com.app.model.Model_Load_Data;
import com.app.model.Model_Receive_Message;
import com.app.model.Model_Receive_Message_Group;
import com.app.model.Model_Send_Message;
import com.app.model.Model_User_Account;
import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service {

    private static Service instance;
    private Socket client;
    private final int PORT_NUMBER = 9999;
    private Model_User_Account user;
    private final String IP = "localhost";
    private List<Model_File_Sender> fileSender;
    private List<Model_File_Receiver> fileReceiver;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    private Service() {
        fileSender = new ArrayList<>();
        fileReceiver = new ArrayList<>();
    }

    public void startServer() {
        try {
            client = IO.socket("http://" + IP + ":" + PORT_NUMBER);
            client.on("list_user", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    // list user
                    List<Model_User_Account> users = new ArrayList<>();
                    for (Object o : os) {
                        Model_User_Account u = new Model_User_Account(o);
                        if (u.getUserID() != user.getUserID()) {
                            users.add(u);
                        }
                    }
                    PublicEvent.getInstance().getEventMenuLeft().newUser(users);
                }
            });
            client.on("user_update_avata", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                        int userID = (int) os[0];
                        if (userID != user.getUserID()) {
                            PublicEvent.getInstance().getEventMenuLeft().updateAvataUser(userID);
                        }
                }
            });

            client.on("user_status", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    int userID = (Integer) os[0];
                    boolean status = (Boolean) os[1];
                    if (status) {
                        //connnec
                        PublicEvent.getInstance().getEventMenuLeft().userConnect(userID);
                    } else {
                        //disconnec
                        PublicEvent.getInstance().getEventMenuLeft().userDisconnect(userID);
                    }
                }
            });
            client.on("list_group", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    // list user
                    List<Model_Group> groups = new ArrayList<>();
                    for (Object o : os) {
                        Model_Group u = new Model_Group(o);
                        groups.add(u);
                    }
                    System.out.println("List Group: " + groups.size());
                    PublicEvent.getInstance().getEventMenuLeft().listGroup(groups);
                }
            });

            client.on("group_status", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_Group g = new Model_Group(os[0]);
                    boolean status = (Boolean) os[1];
                    if (status) {
                        PublicEvent.getInstance().getEventMenuLeft().newGroup(g);
                    }
                }
            });

            client.on("receive_ms", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_Receive_Message message = new Model_Receive_Message(os[0]);
                    PublicEvent.getInstance().getEventChat().receiveMessage(message);
                }

            });

            client.on("receive_ms_group", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_Receive_Message message = new Model_Receive_Message(os[0]);
                    int groupID = (int) os[1];
                    PublicEvent.getInstance().getEventChat().receiveMessageGroup(message, groupID);
                }

            });

            client.on("receive_image_group", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println("da nhan duoc tin tu server");
                    Model_Receive_Message_Group message = new Model_Receive_Message_Group(os[0]);
                    byte[] da = (byte[]) os[1];
                    if (da != null) {
                        String filepath = "client_data/" + user.getUserID() + "@" + message.getDataImage().getFileName() + message.getDataImage().getFileExtension();
                        try {
                            Files.write(Paths.get(filepath), da);
                            System.out.println("Lưu tệp tin thành công.");
                        } catch (IOException e) {
                            System.err.println("Lỗi khi lưu tệp tin: " + e.getMessage());
                        }
                        System.out.println("co gia tri: imagedata" + da);

                    }
                    if (message != null) {
                        System.out.println("co gia tri: imagestring" + message.getDataImage().getImage());
                    }
                    PublicEvent.getInstance().getEventChat().receiveImageGroup(message);
                }

            });

//            client.on("receive_ms_group_file", new Emitter.Listener() {
//                @Override
//                public void call(Object... os) {
//                    Model_Receive_Message_Group message = new Model_Receive_Message_Group(os[0]);
//                    PublicEvent.getInstance().getEventChat().receiveMessageGroup(message);
//                }
//
//            });
            client.on("list_data_user", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    for (int i = 0; i < os.length - 1; i++) {
                        Object o = os[i];
                        Object nextO = os[i + 1];

                        Model_Load_Data d;
                        Model_Load_Data nextD;
                        try {
                            d = new Model_Load_Data(o);
                            nextD = new Model_Load_Data(nextO);
                            PublicEvent.getInstance().getEventChat().loadDateTime(d.getDateTime(), nextD.getDateTime());
                            if (d.getFromUserID() == user.getUserID()) {
                                PublicEvent.getInstance().getEventChat().loadData(d, true);
                            } else {
                                PublicEvent.getInstance().getEventChat().loadData(d, false);
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            });

            client.on("join_group", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println("client: " + os[0].toString());
                }
            });
            //request_join_group
            client.on("request_join_group", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    String reName = (String) os[0];
                    String groupName = (String) os[1];
                    System.out.println("Admin: reName  = " + reName + "groupName = " + groupName);
                    PublicEvent.getInstance().getEventReGroup().requestJoinGroup(reName, groupName);
                }
            });
            //send_join_group
            client.on("send_join_group", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    String message = (String) os[0];
                    System.out.println("Message  = " + message);
                    PublicEvent.getInstance().getEventReGroup().sendJoinGroup(message);
                }
            });
            //check_group_online
            client.on("check_group_online", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    List<Integer> groupIDs = new ArrayList<>();
                    for (Object o : os) {
                        int u = (int) o;
                        groupIDs.add(u); 
                    }
                    System.out.println("List GroupID: " + groupIDs.size());
                    PublicEvent.getInstance().getEventMenuLeft().statusGroup(groupIDs);
                }
                
            });
            
            client.open();
        } catch (URISyntaxException e) {
            error(e);
        }
    }

    public Model_File_Sender addFile(File file, Model_Send_Message message) throws IOException {
        Model_File_Sender data = new Model_File_Sender(file, client, message);
        message.setFile(data);
        fileSender.add(data);
        //for send file one by one
        if (fileSender.size() == 1) {
            data.initSend();
        }
        return data;
    }

    public Model_File_Sender addFileGroup(File file, Model_Send_Message message) throws IOException {
        Model_File_Sender data = new Model_File_Sender(file, client, message);
        message.setFile(data);
        fileSender.add(data);
        //for send file one by one
        if (fileSender.size() == 1) {
            data.initSendGroup();
        }
        return data;
    }
    
    public void addAvata(File file) throws IOException {
        Model_Avata_Sender data = new Model_Avata_Sender(file, client);
        System.out.println("day r");
        data.initSend();   
    }

    public void fileSendFinish(Model_File_Sender data) throws IOException {
        fileSender.remove(data);
        if (!fileSender.isEmpty()) {
            //start send new file when old file sendng finish
            fileSender.get(0).initSend();
        }
    }
    //fileSendGroupFinish

    public void fileSendGroupFinish(Model_File_Sender data) throws IOException {
        fileSender.remove(data);
        if (!fileSender.isEmpty()) {
            //start send new file when old file sendng finish
            fileSender.get(0).initSendGroup();
        }
    }

    public void fileReceiveFinish(Model_File_Receiver data) throws IOException {
        fileReceiver.remove(data);
        if (!fileReceiver.isEmpty()) {
            //start send new file when old file sendng finish
            fileReceiver.get(0).initReceive();
        }
    }

    public void addFileReceiver(int fileID, EventFileReceiver event) throws IOException {
        Model_File_Receiver data = new Model_File_Receiver(fileID, client, event);
        fileReceiver.add(data);
        if (fileReceiver.size() == 1) {
            data.initReceive();
        }
    }

    public Socket getClient() {
        return client;
    }

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    private void error(Exception e) {
        System.err.println(e);
    }
}
