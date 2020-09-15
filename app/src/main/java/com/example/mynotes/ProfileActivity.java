package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db;

    TextView name_tv, email_tv, notes_tv;
    CircleImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        name_tv = findViewById(R.id.profile_name);
        email_tv = findViewById(R.id.profile_email);
        notes_tv = findViewById(R.id.profile_notes);
        image = findViewById(R.id.profile_image);

        Uri image_url = user.getProviderData().get(0).getPhotoUrl();

        name_tv.setText(user.getDisplayName());
        email_tv.setText(user.getEmail());
        //TODO: Add appropriate text in notes_tv
        notes_tv.setText(String.valueOf(30));
        Picasso.with(this).load(image_url).into(image);
    }
}