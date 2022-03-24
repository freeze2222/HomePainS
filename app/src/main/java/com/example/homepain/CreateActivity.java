package com.example.homepain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homepain.models.Note;
import com.example.homepain.services.DatabaseService;

import java.util.Date;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        EditText title=findViewById(R.id.name);
        CheckBox checkBox=findViewById(R.id.done);
        EditText description=findViewById(R.id.description);
        Button button=findViewById(R.id.add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatabaseService.createRandomNewUser();
                boolean checked=checkBox.isChecked();
                DatabaseService.getDatabase().getReference("notes/"+title.getText().toString()+description.getText().toString()).setValue(
                                new Note(title.getText().toString(),description.getText().toString(),new Date(),checked)
                        );
                Note note = new Note(
                        title.getText().toString(),
                        description.getText().toString(),
                        new Date(),
                        checked
                );
                MainActivity.notes.add(note);
                MainActivity.adapter.notifyDataSetChanged();
                Intent intent=new Intent(CreateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}