package com.app.event;

import com.app.model.Model_Register_Group;
import com.app.model.Model_User_Account;
import java.util.List;

public interface EventReGroup {
    
     public void registerGroup(Model_Register_Group data, EventMessage message);
     
     public void joinGroup(String groupName, EventMessage message);
     
     public void requestJoinGroup(String reName, String groupName);
     
     public void sendJoinGroup(String message);
     
     public void sendMessage(String message);
     
     public void memberInGroup( List<Model_User_Account> list);
}
