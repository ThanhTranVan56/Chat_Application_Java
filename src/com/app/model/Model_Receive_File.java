package com.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Receive_File {

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }


    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    public Model_Receive_File(int fileID, String fileName, long fileSize, String fileExtension) {
        this.fileID = fileID;
        this.fileSize = fileSize;
        this.fileExtension = fileExtension;
    }

    public Model_Receive_File() {
    }
    public Model_Receive_File(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            fileID = obj.getInt("fileID");
            fileSize = obj.getLong("fileSize");
            fileExtension = obj.getString("fileExtension");
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
    private int fileID;
    private long fileSize;
    private String fileExtension;

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fileID", fileID);
            json.put("fileSize", fileSize);
            json.put("fileExtension", fileExtension);
        } catch (JSONException e) {
            return null;
        }
        return null;
    } 
}
