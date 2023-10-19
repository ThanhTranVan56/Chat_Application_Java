package com.app.model;

import com.app.event.EventFileSender;
import com.app.service.Service;
import io.socket.client.Ack;
import io.socket.client.Socket;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class Model_File_Sender {

    public Model_Send_Message getMessage() {
        return message;
    }

    public void setMessage(Model_Send_Message message) {
        this.message = message;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public long getGileSize() {
        return fileSize;
    }

    public void setGileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public RandomAccessFile getAccFile() {
        return accFile;
    }

    public void setAccFile(RandomAccessFile accFile) {
        this.accFile = accFile;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Model_File_Sender(File file, Socket socket, Model_Send_Message message) throws IOException {
        accFile = new RandomAccessFile(file, "r");
        this.file = file;
        this.socket = socket;
        this.message = message;
        fileName = file.getName();
        fileSize = accFile.length();
    }

    public Model_File_Sender() {
    }
    private Model_Send_Message message;
    private int fileID;
    private String fileName;
    private File file;
    private long fileSize;
    private RandomAccessFile accFile;
    private Socket socket;
    private EventFileSender event;

    private static final String[] fileSizeUnits = {"bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};

    public synchronized byte[] readFile() throws IOException {
        long filepointer = accFile.getFilePointer();
        if (filepointer != fileSize) {
            int max = 2000;
            long length = filepointer + max >= fileSize ? fileSize - filepointer : max;
            byte[] data = new byte[(int) length];
            accFile.read(data);
            return data;
        } else {
            return null;
        }
    }
    
    public String getFileSizeConvert(double bytes) {
        String sizeToReturn;
        DecimalFormat df = new DecimalFormat("0.#");
        int index;
        for (index = 0; index < fileSizeUnits.length; index++) {
            if (bytes < 1024) {
                break;
            }
            bytes = bytes / 1024;
        }
        System.out.println("Systematic file size: " + bytes + " " + fileSizeUnits[index]);
        sizeToReturn = df.format(bytes) + " " + fileSizeUnits[index];
        return sizeToReturn;
    }
    public void initSend() throws IOException {
        socket.emit("send_to_user", message.toJsonObject(), new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    int fileID = (int) os[0];
                    try {
                        startSend(fileID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void startSend(int fileID) throws IOException {
        this.fileID = fileID;
        if (event != null) {
            event.onStartSending();
        }
        sendingFile();
    }

    private void sendingFile() throws IOException {
        Model_Package_Sender data = new Model_Package_Sender();
        data.setFileID(fileID);
        byte[] bytes = readFile();
        if (bytes != null) {
            data.setData(bytes);
            data.setFinish(false);
        } else {
            data.setFinish(true);
            close();
        }
        socket.emit("send_file", data.toJsonObject(), new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    boolean act = (boolean) os[0];
                    if (act) {
                        try {
                            if (!data.isFinish()) {
                                if (event != null) {
                                    event.onSending(getPercentage());
                                }
                                sendingFile();
                            } else {
                                //File send finish
                                Service.getInstance().fileSendGroupFinish(Model_File_Sender.this);
                                if (event != null) {
                                    event.onFinish();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });
    }
    
    public void initSendGroup() throws IOException {
        socket.emit("send_to_group", message.toJsonObject(), new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    int fileID = (int) os[0];
                    try {
                        System.out.println("server da tra loi - send to group: " + fileID);
                        startSendGroup(fileID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    
    public void startSendGroup(int fileID) throws IOException {
        this.fileID = fileID;
        if (event != null) {
            event.onStartSending();
        }
        sendingFileGroup();
    }

    private void sendingFileGroup() throws IOException {
        Model_Package_Sender data = new Model_Package_Sender();
        data.setFileID(fileID);
        byte[] bytes = readFile();
        if (bytes != null) {
            data.setData(bytes);
            data.setFinish(false);
        } else {
            data.setFinish(true);
            close();
        }
        socket.emit("send_file_group", data.toJsonObject(), new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    boolean act = (boolean) os[0];
                    if (act) {
                        try {
                            if (!data.isFinish()) {
                                if (event != null) {
                                    event.onSending(getPercentage());
                                }
                                sendingFileGroup();
                            } else {
                                //File send finish
                                Service.getInstance().fileSendFinish(Model_File_Sender.this);
                                if (event != null) {
                                    event.onFinish();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });
    }
    public double getPercentage() throws IOException {
        double percentage;
        long filePointer = accFile.getFilePointer();
        percentage = filePointer * 100 / fileSize;
        return percentage;

    }

    public void close() throws IOException {
        accFile.close();
    }

    public void addEvent(EventFileSender event) {
        this.event = event;
    }
}
