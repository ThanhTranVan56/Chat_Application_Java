package com.app.event;

import com.app.model.Model_Login;
import com.app.model.Model_Register;

public interface EventLogin {

    public void login(Model_Login data, EventMessage message);

    public void register(Model_Register data, EventMessage message);

    public void goRegister();

    public void goLogin();
}
