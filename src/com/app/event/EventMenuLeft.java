package com.app.event;

import com.app.model.Model_Group;
import com.app.model.Model_User_Account;
import java.util.List;

public interface EventMenuLeft {

    public void newUser(List<Model_User_Account> users);
    
    public void updateAvataUser(int userID);
    
    public void listGroup(List<Model_Group> groups);
    
    public void newGroup(Model_Group group);

    public void userConnect(int userID);

    public void userDisconnect(int userID);
    
    public void checkGroupOnline();
    
    public void statusGroup(List<Integer> groupID);
}
