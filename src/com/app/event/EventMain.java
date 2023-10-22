package com.app.event;

import com.app.model.Model_Group;
import com.app.model.Model_User_Account;

public interface EventMain {

    public void showLoading(boolean show);

    public void initChat();
    
    public void selectUser(Model_User_Account user);
    
    public void updateUser(Model_User_Account user);
    
    public void selectGroup(Model_Group group);
    
    public void waitGroup();
    
    public void reLogin();
}
