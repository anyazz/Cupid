package com.facebook.cupid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.facebook.cupid.models.Suggestion;
import com.facebook.cupid.models.User;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by amusipatla on 11/12/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    ArrayList<Suggestion> suggestions;
    Context context;
    User date1;
    User date2;
    ArrayList<User> friends;

    public NotificationAdapter(ArrayList<Suggestion> suggestions){this.suggestions = suggestions;}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View friendListView = inflater.inflate(R.layout.item_notification, parent, false);
        ViewHolder viewHolder = new ViewHolder(friendListView);
        friends = CupidApplication.getFacebookFriends();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Suggestion suggestion = suggestions.get(position);
        long myId = Long.parseLong(Profile.getCurrentProfile().getId());
        long friend1Id = suggestion.friend1;
        long friend2Id = suggestion.friend2;

        for(User user: friends){
            if(user.getFbUserID() == friend1Id){
                date1 = user;
            }
            else if(user.getFbUserID() == friend2Id){
                date2 = user;
            }
        }

        if(suggestion.friend1accept && suggestion.friend2accept){
            holder.notifText.setText(date1.getName() + " and " + date2.getName()
                    + " have accepted your match!");
        }


        Glide.with(context)
                .load(date1.getPictureUrl())
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.friend1pic);


        Glide.with(context)
                .load(date2.getPictureUrl())
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.friend2pic);

    }


    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView notifText;
        ImageView friend1pic;
        ImageView friend2pic;

        public ViewHolder(View itemView){
            super(itemView);
            notifText = (TextView) itemView.findViewById(R.id.notif_text);
            friend1pic = (ImageView) itemView.findViewById(R.id.imageView2);
            friend2pic = (ImageView) itemView.findViewById(R.id.imageView3);

            itemView.setOnClickListener(this);
        }// constructor

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != android.support.v7.widget.RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                User friend = friends.get(position);

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
