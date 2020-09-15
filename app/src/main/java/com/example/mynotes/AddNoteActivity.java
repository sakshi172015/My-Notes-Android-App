package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynotes.models.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

public class AddNoteActivity extends AppCompatActivity {

    TextInputEditText title_et, content_et;
    ImageView add_btn;
    String title, content;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title_et = findViewById(R.id.add_title_et);
        content_et = findViewById(R.id.add_content_et);
        add_btn = findViewById(R.id.add_add_btn);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEditText();
            }
        });

    }
    void checkEditText() {
        title = title_et.getText().toString();
        content = content_et.getText().toString();
        if(title.length() == 0){
            title_et.setError("cannot be empty");
        }
        if(content.length() == 0){
            content_et.setError("cannot be empty");
        }
        if(title.length() !=0 && content.length() != 0){
            setNote();
        }
    }

    void setNote() {
        Long tsLong = System.currentTimeMillis()/1000;
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setTime(tsLong.toString());

        DocumentReference docRef = db.collection("notes").document(user.getUid()).collection("myNotes").document();
        docRef.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddNoteActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNoteActivity.this, "Error! try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}