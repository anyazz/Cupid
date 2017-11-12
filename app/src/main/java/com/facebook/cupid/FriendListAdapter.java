package com.facebook.cupid;

import android.content.Context;
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

/**
 * Created by Emily on 11/11/2017.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    ArrayList<User> friends;
    Context context;

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
        User friend = friends.get(position);
        String name = friend.getName();
        holder.tvName.setText(name);
        Glide.with(context).load(friend.getPictureUrl()).into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        ImageView ivPicture;
        public ViewHolder(View itemView){
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivPicture =  (ImageView) itemView.findViewById(R.id.ivPicture);
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
                SelectFriendFragment fragment = SelectFriendFragment.newInstance(args);
                FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.commit();

                // create intent for the new activity
                // Intent intent = new Intent(context, OptionsActivity.class);
                // serialize the country using parceler, use its short name as a key
                // intent.putExtra("country", Parcels.wrap(country));
                // show the activity
                // context.startActivity(intent);
            }

        }
    }
}
