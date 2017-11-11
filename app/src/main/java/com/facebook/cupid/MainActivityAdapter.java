package com.facebook.cupid;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Emily on 11/11/2017.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    ArrayList<Friend> friends;
    public MainActivityAdapter(ArrayList<Friend> friends){this.friends = friends;}
    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ViewHolder(View itemView){
            super(itemView);


        }// constructor

        @Override
        public void onClick(View view) {

        }
    }
}
