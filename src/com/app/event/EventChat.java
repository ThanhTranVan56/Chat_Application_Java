package com.app.event;

import com.app.model.Model_Load_Data;
import com.app.model.Model_Receive_Message;
import com.app.model.Model_Receive_Message_Group;
import com.app.model.Model_Send_Message;
import java.sql.Timestamp;

public interface EventChat {

    public void sendMessage(Model_Send_Message data);

    public void receiveMessage(Model_Receive_Message data);
    
    public void receiveMessageGroup(Model_Receive_Message data, int gorupID);
    
    public void receiveImageGroup(Model_Receive_Message_Group data);
    
    public void loadData(Model_Load_Data message, boolean type);
    
    public void loadDateTime(Timestamp datetime, Timestamp nextDateTime);
}
