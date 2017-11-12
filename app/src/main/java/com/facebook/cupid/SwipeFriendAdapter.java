package com.facebook.cupid;

/**
 * Created by Emily on 11/12/2017.
 */


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.facebook.cupid.models.User;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import org.parceler.Parcels;

/**
 * Created by Emily on 11/11/2017.
 */

public class SwipeFriendAdapter extends RecyclerView.Adapter<SwipeFriendAdapter.ViewHolder> {
    ArrayList<User> friends;
    Context context;
    boolean firstFriendPicked;
    User friend1;

    public SwipeFriendAdapter(ArrayList<User> friends, User friend1){this.friends = friends; this.friend1 = friend1;}
    @Override
    public SwipeFriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View SwipeFriendAdapter = inflater.inflate(R.layout.item_view_friend, parent, false);
        ViewHolder viewHolder = new ViewHolder(SwipeFriendAdapter);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SwipeFriendAdapter.ViewHolder holder, int position) {
        final User friend = friends.get(position);
        String name = friend.getName();
        String bio = friend.getSchool();
        if (bio != null) {
            holder.tvBio.setText(bio);
        }
        holder.tvName.setText(name);
        firstFriendPicked = false;

        Glide.with(context)
                .load(friend.getPictureUrl())
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.ivPicture);

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        ImageView ivPicture;
        TextView tvBio;
        public ViewHolder(View itemView){
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivPicture =  (ImageView) itemView.findViewById(R.id.ivPicture);
            tvBio = (TextView) itemView.findViewById(R.id.tvBio);
            itemView.setOnClickListener(this);
        }// constructor

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != android.support.v7.widget.RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                User friend2 = friends.get(position);
                Intent intent = new Intent(context, MatchActivity.class);
                // serialize the country using parceler, use its short name as a key
                intent.putExtra("friend1", Parcels.wrap(friend1));
                intent.putExtra("friend2", Parcels.wrap(friend2));

                // allowed to be colored and go to the next screen
                // now that the thing is true we can go to the activity next!!!
                context.startActivity(intent);
                // create intent for the new activity
                //first friend picked is true
                // show the activity

            }

        }
    }

}
