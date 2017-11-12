package com.facebook.cupid;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MatchActivity extends AppCompatActivity {
    public ImageView ivFirst;
    public TextView tvFirstName;
    public  TextView tvFirstBio;
    public ImageView ivSecond;
    public TextView tvSecondName;
    public  TextView tvSecondBio;
    public EditText etComment;

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


    }

}
