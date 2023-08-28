package com.travelchatapp.pfe;

public class MessageItem {

    private String senderID;
    private String message;

    public MessageItem(String senderID, String message) {
        this.senderID = senderID;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderID() {
        return senderID;
    }
}
