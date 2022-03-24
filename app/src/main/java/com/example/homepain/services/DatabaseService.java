package com.example.homepain.services;

import androidx.annotation.NonNull;

import com.example.homepain.models.Note;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Date;

public class DatabaseService {
    public static FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance();
    }


    public static Task<Void> remove(DatabaseReference ref) {
        return ref
                .removeValue();
    }
    public static Task<Void> changePriority(DatabaseReference ref,Note note,int priority) {
        note.priority=priority;
        return ref.setValue(note);
    }

    public static FirebaseRecyclerOptions<Note> getNoteOptions() {
        Query query = getDatabase().getReference("notes");
        ClassSnapshotParser<Note> parser = new ClassSnapshotParser<>(Note.class);

        return new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, parser)
                .build();
    }

    public static void createRandomNote() {
        getDatabase()
                .getReference("notes")
                .push()
                .setValue(
                        new Note("","",new Date(),false)
                );
    }
    public static DatabaseReference getNoteRef(Note note){
        return
                getDatabase().getReference("notes/"+note.title+note.description);
    }
}

