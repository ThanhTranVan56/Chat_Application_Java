package com.app.model;

import com.app.apps.MessageType;
import java.sql.Timestamp;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Load_Data {
    
    public Model_Receive_File getDataFile() {
        return dataFile;
    }

    public void setDataFile(Model_Receive_File dataFile) {
        this.dataFile = dataFile;
    }

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
    
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
    
    public Model_Load_Data(MessageType messageType, int fromUserID, int toUserID, String text){
        this.messageType = messageType;
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.text = text;
    }
    
    public Model_Load_Data(Object json) throws ParseException{
        JSONObject obj = (JSONObject) json;
        try {
            messageType = MessageType.toMessageType(obj.getInt("messageType"));
            fromUserID = obj.getInt("fromUserID");
            text = obj.getString("text");
            long timestamp = obj.getLong("dateTime");
            dateTime = new Timestamp(timestamp);
            if(!obj.isNull("dataImage")){
                dataImage = new Model_Receive_Image(obj.get("dataImage"));
            }
            if(!obj.isNull("dataFile")){
                dataFile = new Model_Receive_File(obj.get("dataFile"));
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
          
    private MessageType messageType;
    private int fromUserID;
    private int toUserID;
    private String text;
    private Timestamp  dateTime;
    private Model_Receive_File dataFile;
    private Model_Receive_Image dataImage;
    
//    public JSONObject toJsonObject() {
//        try {
//            JSONObject json = new JSONObject();
//            json.put("messageType", messageType.getValue());
//            json.put("fromUserID", fromUserID);
//            json.put("toUserID", toUserID);
//            json.put("text", text);
//            if(messageType == MessageType.FILE || messageType == MessageType.IMAGE){
//                json.put("text", dataFile.getFileName());
//            } else{
//                json.put("text", text);
//            }
//            return json;
//        } catch (JSONException e) {
//            return null;
//        }
//    }



    


}

