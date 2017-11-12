package com.facebook.cupid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.cupid.models.Suggestion;
import com.facebook.cupid.models.User;

import java.util.ArrayList;

/**
 * Created by amusipatla on 11/12/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    ArrayList<Suggestion> suggestions;
    Context context;
    User date;
    User matchmaker;
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
        Suggestion suggestion = suggestions.get(position);
        long myId = Long.parseLong(CupidApplication.getmUser().getProviderId());
        long friendId;

        if(myId == suggestion.friend1){
            friendId = suggestion.friend2;
        }
        else{
            friendId = suggestion.friend1;
        }



        for(User user: friends){
            if(user.getFbUserID() == friendId){
                date = user;
            }
            else if(user.getFbUserID() == suggestion.matchmakerId){
                matchmaker = user;
            }
        }

        holder.notifText.setText(matchmaker.getName() + " thinks you and " + date.getName()
                    + " would be a good match!");

        holder.notifText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle args = new Bundle();
                args.putParcelable("date", date);

            }
        });
    }


    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView notifText;

        public ViewHolder(View itemView){
            super(itemView);
            notifText = (TextView) itemView.findViewById(R.id.notif_text);
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
