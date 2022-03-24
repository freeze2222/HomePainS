package com.example.homepain.services;

import com.example.homepain.models.User;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;

public class UserService {
    public static User createUser(AuthResult authResult) {
        return new User(
                "",
                authResult.getUser().getEmail(),
                authResult.getUser().getMetadata().getCreationTimestamp(),
                "desk"
        );
    }

    public static void storeUser(User user) {
        FirebaseDatabase.getInstance()
                .getReference("users")
                .push()
                .setValue(user);
    }
}
