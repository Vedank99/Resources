package com.example.resources;

import android.widget.ImageView;

import java.io.Serializable;

public class contributer implements Serializable {

    private String name;
    private String type;
    private String email;
    private String social;
    private int imageID;

    public contributer(String name, String type, String email, String social,int imageID) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.social = social;
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) { this.social = social; }
}
