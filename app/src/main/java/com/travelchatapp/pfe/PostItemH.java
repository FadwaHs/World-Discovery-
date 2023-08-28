package com.travelchatapp.pfe;

import android.graphics.Bitmap;

public class PostItemH {
    private int post;
    private String category;
    private String description;
    private int profile;
    private String profileName;
    private String date;

    public PostItemH(int post, String category, String description, int profile, String profileName, String date) {
        this.post = post;
        this.category = category;
        this.description = description;
        this.profile = profile;
        this.profileName = profileName;
        this.date = date;
    }


    public int getPost() {
        return post;
    }

    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }

    public int getProfile() {
        return profile;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getDate() {
        return date;
    }
}
