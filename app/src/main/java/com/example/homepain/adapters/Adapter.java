package com.example.homepain.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepain.R;
import com.example.homepain.models.Note;
import com.example.homepain.services.DatabaseService;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.Date;

public class Adapter extends FirebaseRecyclerAdapter<Note, Adapter.NoteViewHolder> {

    public Adapter(@NonNull FirebaseRecyclerOptions<Note> options) {
        super(options);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note model) {
        holder.bind(model);
    }


    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        TextView descriptionTextView;
        TextView createdTextView;
        CheckBox checkBox;
        ImageView priority;
        ImageView del=itemView.findViewById(R.id.delete);
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            createdTextView = itemView.findViewById(R.id.textViewCreated);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
        public void bind(Note note){
            titleTextView.setText(note.title);
            descriptionTextView.setText(note.getDescription());
            createdTextView.setText(note.getCreatedTime());
            priority=itemView.findViewById(R.id.priority);
            checkBox.setChecked(note.isChecked);
            priority.setImageResource(note.priority==0?android.R.drawable.star_off:note.priority==1?android.R.drawable.star_on:android.R.drawable.star_big_on);
            priority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (note.priority){
                        case 0:
                            DatabaseService.changePriority(DatabaseService.getNoteRef(note),note,1);
                            break;
                        case 1:
                            DatabaseService.changePriority(DatabaseService.getNoteRef(note),note,2);
                            break;
                        case 2:
                            DatabaseService.changePriority(DatabaseService.getNoteRef(note),note,0);
                            break;
                    }

                }
            });
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseService.remove(DatabaseService.getNoteRef(note));
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    note.isChecked = !note.isChecked;
                    DatabaseService.getNoteRef(note).setValue(note);

                }
            });

        }
    }
}