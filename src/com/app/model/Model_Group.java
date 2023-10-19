package com.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Group {

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
    public Model_Group(int groupID, int adminID, String groupName, String image) {
        this.groupID = groupID;
        this.adminID = adminID;
        this.groupName = groupName;
        this.image = image;
    }
    
    public Model_Group(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            groupID = obj.getInt("groupID");
            adminID = obj.getInt("adminID");
            groupName = obj.getString("groupName");
            if(!obj.isNull("image")){
                image = obj.getString("image");
            } else
                image = "";
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
    private int groupID;
    private int adminID;
    private String groupName;
    private String image;
    
    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("groupID", groupID);
            json.put("adminID", adminID);
            json.put("groupName", groupName);
            json.put("image", image);

            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
