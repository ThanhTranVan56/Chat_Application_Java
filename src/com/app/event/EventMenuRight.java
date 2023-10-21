package com.app.event;

import com.app.component.GroupAdminOption;

public interface EventMenuRight {

    public void setOpton(boolean option);
    
    public void setMemberIn(boolean option, String groupName);
    
    public void deleteAdminOpton(GroupAdminOption admin);
   
}
