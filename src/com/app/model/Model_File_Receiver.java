package com.app.model;

import com.app.event.EventFileReceiver;
import com.app.service.Service;
import io.socket.client.Ack;
import io.socket.client.Socket;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import org.json.JSONException;

public class Model_File_Receiver {

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileExtention() {
        return fileExtention;
    }

    public void setFileExtention(String fileExtention) {
        this.fileExtention = fileExtention;
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

    public EventFileReceiver getEvent() {
        return event;
    }

    public void setEvent(EventFileReceiver event) {
        this.event = event;
    }

    public Model_File_Receiver(int fileID, Socket socket, EventFileReceiver event) {
        this.fileID = fileID;
        this.socket = socket;
        this.event = event;
    }

    public Model_File_Receiver() {
    }
    private int fileID;
    private File file;
    private long fileSize;
    private String fileName;
    private String fileExtention;
    private RandomAccessFile accFile;
    private Socket socket;
    private EventFileReceiver event;
    private final String PATH_FILE = "client_data/";

    public void initReceive() {
        socket.emit("get_file", fileID, new Ack() {
            @Override
            public void call(Object... os) {
                if (os.length > 0) {
                    try {
                        fileName = os[0].toString();
                        fileExtention = os[1].toString();
                        fileSize = (int) os[2];
                        int clientID = Service.getInstance().getUser().getUserID();
                        file = new File(PATH_FILE + fileID + clientID + "@" + fileName + fileExtention);
                        accFile = new RandomAccessFile(file,"rw");
                        event.onStartReceiving();
                        startSaveFile();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    
    public void startSaveFile() throws IOException, JSONException{
        Model_Reques_File data = new Model_Reques_File(fileID, accFile.length());
        socket.emit("reques_file", data.toJsonObject(), new Ack(){
            @Override
            public void call(Object... os) {
                try {
                    if(os.length > 0){
                        byte[] b =(byte[]) os[0];
                        //write
                        writeFile(b);
                        event.onReceiving(getPercentage());
                        startSaveFile();
                    } else {
                        close();
                         int clientID = Service.getInstance().getUser().getUserID();
                        event.onFinish(new File(PATH_FILE + fileID + clientID + "@" + fileName + fileExtention),getFileSizeC());
                        //remove list
                        Service.getInstance().fileReceiveFinish(Model_File_Receiver.this);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private String getFileSizeC(){
        return getFileSizeConvert(fileSize);
    }
    private static final String[] fileSizeUnits = {"bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
    private String getFileSizeConvert(double bytes) {
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
    private synchronized long writeFile(byte[] data) throws IOException{
        accFile.seek(accFile.length());
        accFile.write(data);
        return accFile.length();
    }
    
    public double getPercentage() throws IOException{
        double percentage;
        long filePointer = accFile.getFilePointer();
        percentage = filePointer*100/fileSize;
        return percentage;
    }
    public void close() throws IOException{
        accFile.close();
    }

   
}
