package com.app.model;

import com.app.apps.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Receive_Message_Group {

    public Model_Receive_File getDataFile() {
        return dataFile;
    }

    public void setDataFile(Model_Receive_File dataFile) {
        this.dataFile = dataFile;
    }

    public Model_Receive_Image_Group getDataImage() {
        return dataImage;
    }

    public void setDataImage(Model_Receive_Image_Group dataImage) {
        this.dataImage = dataImage;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public int getToGroupID() {
        return toGroupID;
    }

    public void setToGroupID(int toGroupID) {
        this.toGroupID = toGroupID;
    }
    
    public Model_Receive_Message_Group(MessageType messageType, int fromUserID, int toGroupID, String text){
        this.messageType = messageType;
        this.fromUserID = fromUserID;
        this.toGroupID = toGroupID;
        this.text = text;
    }
    
    public Model_Receive_Message_Group(Object json){
        JSONObject obj = (JSONObject) json;
        try {
            messageType = MessageType.toMessageType(obj.getInt("messageType"));
            fromUserID = obj.getInt("fromUserID");
            toGroupID = obj.getInt("toGroupID");
            text = obj.getString("text");
            if(!obj.isNull("dataImage")){
                dataImage = new Model_Receive_Image_Group(obj.get("dataImage"));
            }
            if(!obj.isNull("dataFile")){
                dataFile = new Model_Receive_File(obj.get("dataFile"));
            }
        } catch (JSONException e) {
            System.err.println(e + "loi o day");
        }
    }
            
    private MessageType messageType;
    private int fromUserID;
    private int toGroupID;
    private String text;
    private Model_Receive_Image_Group dataImage;
    private Model_Receive_File dataFile;
    

    
}
