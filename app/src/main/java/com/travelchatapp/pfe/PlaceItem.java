package com.travelchatapp.pfe;

import android.graphics.Bitmap;

public class PlaceItem {
    private String postId;
    private String post;
    private String category;
    private String description;
    private String profile;
    private String profileName;
    private String date;

    public PlaceItem(String postId,String post, String category, String description, String profile, String profileName, String date) {
        this.postId = postId;
        this.post = post;
        this.category = category;
        this.description = description;
        this.profile = profile;
        this.profileName = profileName;
        this.date = date;
    }


    public String getPost() {
        return post;
    }

    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }

    public String getProfile() {
        return profile;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getDate() {
        return date;
    }

    public String getPostId() {
        return postId;
    }
}
