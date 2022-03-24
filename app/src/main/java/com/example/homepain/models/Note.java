package com.example.homepain.models;

import java.util.Date;

public class Note {
    public String title;
    public String description;
    public Date timeCreated;
    public boolean isChecked;
    public int priority;
    public Note() {
        // очень совсем надо
        }
    public Note(String title, String description, Date timeCreated, boolean isChecked,int priority) {
        this.title = title;
        this.description = description;
        this.timeCreated = timeCreated;
        this.isChecked = isChecked;
        this.priority=priority;
    }
    public Note(String title, String description, Date timeCreated, boolean isChecked) {
        this.title = title;
        this.description = description;
        this.timeCreated = timeCreated;
        this.isChecked = isChecked;
        this.priority=0;
    }

    public Note(String title, Date timeCreated, boolean isChecked) {
        this.title = title;
        this.timeCreated = timeCreated;
        this.description = null;
        this.isChecked = isChecked;
    }

    public String getDescription() {
        if (description == null) {
            return "";
        } else {
            return description;
        }
    }

    public String getCreatedTime() {
        int h = timeCreated.getHours();
        int m = timeCreated.getMinutes();

        return h + ":" + m;
    }
}
