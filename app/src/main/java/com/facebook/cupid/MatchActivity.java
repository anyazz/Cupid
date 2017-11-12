package com.facebook.cupid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.cupid.models.Suggestion;
import com.facebook.cupid.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MatchActivity extends AppCompatActivity {
    public ImageView ivFirst;
    public TextView tvFirstName;
    public  TextView tvFirstBio;
    public ImageView ivSecond;
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
        ivFirst = (ImageView) findViewById(R.id.ivFirst);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvFirstBio = (TextView) findViewById(R.id.tvFirstBio);
        ivSecond = (ImageView) findViewById(R.id.ivSecond);
        tvSecondName = (TextView) findViewById(R.id.tvSecondName);
        tvSecondBio = (TextView) findViewById(R.id.tvSecondBio);
        etComment = (EditText) findViewById(R.id.etComment);
        btComment = (Button) findViewById(R.id.btComment);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        User user_1 = Parcels.unwrap(getIntent().getParcelableExtra("friend1"));
        User user_2 = Parcels.unwrap(getIntent().getParcelableExtra("friend2"));
        Glide.with(this)
                .load(user_1.pictureUrl)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(ivFirst);
        Glide.with(this)
                .load(user_2.pictureUrl)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(ivSecond);
        tvFirstName.setText(user_1.name);
        tvFirstBio.setText(user_1.school + user_1.age);
        tvSecondName.setText(user_2.name);
        tvSecondBio.setText(user_2.school + user_2.age);

        btComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etComment.getText().toString();
                Suggestion suggestion = new Suggestion(friend1, friend2, message);
                mDatabase.child("suggestions").push().setValue(suggestion);
            }
        });

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View v = super.onCreateView(name, context, attrs);

        return v;
    }
}
