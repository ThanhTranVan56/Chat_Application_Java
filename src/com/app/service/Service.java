package com.app.service;

import com.app.event.EventFileReceiver;
import com.app.event.PublicEvent;
import com.app.model.Model_File_Receiver;
import com.app.model.Model_File_Sender;
import com.app.model.Model_Load_Data;
import com.app.model.Model_Receive_Message;
import com.app.model.Model_Send_Message;
import com.app.model.Model_User_Account;
import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

            client.on("receive_ms", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    Model_Receive_Message message = new Model_Receive_Message(os[0]);
                    PublicEvent.getInstance().getEventChat().receiveMessage(message);
                }

            });
            client.on("list_data_user", new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    for (Object o : os) {
                        Model_Load_Data d = new Model_Load_Data(o);
                        if (d.getFromUserID() == user.getUserID()) {
                            PublicEvent.getInstance().getEventChat().loadData(d, true);
                            //System.out.println("ok" + d.getToUserID() + user.getUserID());
                        } else {
                            PublicEvent.getInstance().getEventChat().loadData(d, false);
                            //System.out.println("not" + d.getToUserID() + user.getUserID());
                        }
                    }
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

    public void fileSendFinish(Model_File_Sender data) throws IOException {
        fileSender.remove(data);
        if (!fileSender.isEmpty()) {
            //start send new file when old file sendng finish
            fileSender.get(0).initSend();
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
