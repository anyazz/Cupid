package com.facebook.cupid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Profile;
import com.facebook.cupid.models.Suggestion;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;


public class NotificationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    RecyclerView rvNotifications;
    ArrayList<Suggestion> suggestions;
    NotificationAdapter notificationAdapter;

    public DatabaseReference mDatabase;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        suggestions = new ArrayList<Suggestion>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("suggestions").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map suggestion = (Map) dataSnapshot.getValue();
                long f1 = (long) suggestion.get("friend1");
                long f2 = (long) suggestion.get("friend2");
                boolean f1a = (boolean) suggestion.get("friend1accept");
                boolean f2a = (boolean) suggestion.get("friend2accept");
                long mm = (long) suggestion.get("matchmakerId");
                String message = (String) suggestion.get("message");

                Suggestion temp = new Suggestion(f1, f2, f1a, f2a, message, mm);

                long myId = Long.parseLong(Profile.getCurrentProfile().getId());

                if(myId == mm){
                    suggestions.add(temp);
                }

                notificationAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        rvNotifications = (RecyclerView) v.findViewById(R.id.rv_notification_list);
        rvNotifications.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter(suggestions);
        rvNotifications.setAdapter(notificationAdapter);
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
