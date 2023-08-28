package com.travelchatapp.pfe;

import android.graphics.Bitmap;

public class ChatSenderItem {

    private Bitmap bitmap;
    private String senderName;

    public ChatSenderItem(Bitmap bitmap,String senderName){
        this.bitmap=bitmap;
        this.senderName=senderName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getSenderName() {
        return senderName;
    }
}
