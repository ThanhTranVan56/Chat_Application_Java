package com.app.model;

import com.app.apps.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Load_Data {

    public Model_Receive_Image getDataImage() {
        return dataImage;
    }

    public void setDataImage(Model_Receive_Image dataImage) {
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

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Model_Load_Data(MessageType messageType, int fromUserID, int toUserID, String text){
        this.messageType = messageType;
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.text = text;
    }
    
    public Model_Load_Data(Object json){
        JSONObject obj = (JSONObject) json;
        try {
            messageType = MessageType.toMessageType(obj.getInt("messageType"));
            fromUserID = obj.getInt("fromUserID");
            text = obj.getString("text");
            if(!obj.isNull("dataImage")){
                dataImage = new Model_Receive_Image(obj.get("dataImage"));
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
          
    private MessageType messageType;
    private int fromUserID;
    private int toUserID;
    private String text;
    private Model_File_Sender file;
    private Model_Receive_Image dataImage;
    
    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("messageType", messageType.getValue());
            json.put("fromUserID", fromUserID);
            json.put("toUserID", toUserID);
            json.put("text", text);
            if(messageType == MessageType.FILE || messageType == MessageType.IMAGE){
                json.put("text", file.getFileExtensions());
            } else{
                json.put("text", text);
            }
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}

