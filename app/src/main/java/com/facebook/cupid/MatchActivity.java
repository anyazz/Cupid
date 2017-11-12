package com.facebook.cupid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.cupid.models.Suggestion;
import com.facebook.cupid.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MatchActivity extends AppCompatActivity {
    public ImageView imFirst;
    public TextView tvFirstName;
    public  TextView tvFirstBio;
    public ImageView imSecond;
    public TextView tvSecondName;
    public  TextView tvSecondBio;
    public Button btComment;
    public EditText etComment;
    public DatabaseReference mDatabase;
    public User friend1;
    public User friend2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imFirst = (ImageView) findViewById(R.id.imFirst);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvFirstBio = (TextView) findViewById(R.id.tvFirstBio);
        imSecond = (ImageView) findViewById(R.id.imSecond);
        tvSecondName = (TextView) findViewById(R.id.tvSecondName);
        tvSecondBio = (TextView) findViewById(R.id.tvSecondBio);
        etComment = (EditText) findViewById(R.id.etComment);
        btComment = (Button) findViewById(R.id.btComment);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etComment.getText().toString();
                Suggestion suggestion = new Suggestion(friend1, friend2, message);
                mDatabase.child("suggestions").push().setValue(suggestion);
            }
        });

    }

}
