package com.facebook.cupid;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    ArrayList<User> friends;
    Context context;
    boolean firstFriendPicked;

    public FriendListAdapter(ArrayList<User> friends){this.friends = friends;}
    @Override
    public FriendListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View friendListView = inflater.inflate(R.layout.item_view_friend, parent, false);
        ViewHolder viewHolder = new ViewHolder(friendListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendListAdapter.ViewHolder holder, int position) {
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
                User friend = friends.get(position);
                Bundle args = new Bundle();
                args.putParcelable("friend", friend);
                //SelectFriendFragment fragment = SelectFriendFragment.newInstance(args);
                //FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.friendContainer, fragment);
                //transaction.commit();

                Bundle args2 = new Bundle();
                args2.putParcelable("friend", friend);
                Fragment newFragment = SwipeFriendFragment.newInstance(args2);
                FragmentManager fragmentManager = ((MainActivity)context).getFragmentManager();
                FragmentTransaction transaction2 = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.friendContainer, newFragment);
                transaction2.commit();
                //fragmentManager.beginTransaction()
                //        .replace(R.id.rv_friends_list, newFragment)
                //        .commit();

                //Intent intent = new Intent(context, MatchActivity.class);
                    // serialize the country using parceler, use its short name as a key
                //intent.putExtra("friend1", Parcels.wrap(friend));
                firstFriendPicked = true;

                    // allowed to be colored and go to the next screen
                    // now that the thing is true we can go to the activity next!!!
                //context.startActivity(intent);
                    // create intent for the new activity
                //first friend picked is true
                // show the activity

            }

        }
    }

}
