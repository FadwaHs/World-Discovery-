package com.travelchatapp.pfe;

import android.graphics.Bitmap;

public class CityItem {
    private String id;
    private int bitmap;
    private String description;

    public CityItem(String id,int bitmap, String description) {
        this.id = id;
        this.bitmap = bitmap;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public int getBitmap() {
        return bitmap;
    }

    public String getDescription() {
        return description;
    }
}
