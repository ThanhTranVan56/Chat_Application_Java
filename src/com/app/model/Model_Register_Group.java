package com.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Register_Group {
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
    private String groupName;
    private int adminID;

    public Model_Register_Group(String groupName, int adminID){
        this.groupName = groupName;
        this.adminID = adminID;
    }
    
    public Model_Register_Group() {

    }

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("groupName", groupName);
            json.put("adminID", adminID);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
