package com.example.homepain.models;

import java.util.Date;

public class User {
    public String name;
    public String email;
    public String description;
    public Date creationDate;

    public User() {
        //do not remove
    }

    public User(String name, String email, long creationDate, String description) {
        this.name = name;
        this.email = email;
        this.description=description;
        this.creationDate = new Date(creationDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
