package com.app.event;

public class PublicEvent {

    

    private static PublicEvent instance;
    private EventMain eventMain;
    private EventImageView eventImageView;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventMenuLeft eventMenuLeft;
    private EventSaveFile eventSaveFile;
    private EventReGroup eventReGroup;
    private EventMenuRight eventMenuRight;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventMain(EventMain event) {
        this.eventMain = event;
    }

    public void addEventImageView(EventImageView event) {
        this.eventImageView = event;
    }

    public void addEventChat(EventChat event) {
        this.eventChat = event;
    }

    public void addEventLogin(EventLogin event) {
        this.eventLogin = event;
    }

    public void addEventMenuLeft(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }
    public void addEventSaveFile(EventSaveFile eventSaveFile) {
        this.eventSaveFile = eventSaveFile;
    }
    
    public void addEventReGroup(EventReGroup eventReGroup) {
        this.eventReGroup = eventReGroup;
    }
    public void addEventMenuRight(EventMenuRight eventMenuRight) {
        this.eventMenuRight = eventMenuRight;
    }
    
    public EventMain getEventMain() {
        return eventMain;
    }

    public EventImageView getEventImageView() {
        return eventImageView;
    }

    public EventChat getEventChat() {
        return eventChat;
    }

    public EventLogin getEventLogin() {
        return eventLogin;
    }
        
    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }

    public EventSaveFile getEventSaveFile() {
        return eventSaveFile;
    }

    public EventReGroup getEventReGroup() {
        return eventReGroup;
    }

    public EventMenuRight getEventMenuRight() {
        return eventMenuRight;
    }

    

    

    
}
