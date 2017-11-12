package com.facebook.cupid;

import android.app.Application;
import android.content.Context;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.cupid.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by gabrielsaruhashi on 11/11/17.
 */

public class CupidApplication extends Application {
    // application context
    private static Context context;
    private static FacebookClient facebookClient;
    private static ArrayList<User> facebookFriends;
    private static FirebaseUser mUser;

    private static boolean isNewUser;

    private static FirebaseAuth mAuth;

    private static FacebookClient fbClient;


    private static DatabaseReference mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("messages");
        myRef.setValue("Hello, World!");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }

    public static ArrayList<User> getFacebookFriends() {
            if (facebookFriends == null) {
                facebookFriends = new ArrayList<>();

                // start a new thread to execute the runnable codeblock
                Thread thread = new Thread() {
                    @Override
                    public void run() {

                        // the code to execute when the runnable is processed by a thread
                        FacebookClient client = CupidApplication.getFacebookRestClient();

                        client.getFriendsUsingApp(new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                // gets friends ids
                                try {
                                    JSONArray friends = response.getJSONObject().getJSONArray("data");
                                    for (int i = 0; i < friends.length(); i++) {
                                        User friend = User.fromJSON(friends.getJSONObject(i));
                                        facebookFriends.add(friend);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                };

                // start thread
                thread.start();
                // set first load to false for future getFacebookFriends() call

                // wait for the thread to return the facebook API request
                try {
                    thread.join(0);
                } catch (InterruptedException i) {
                    i.getMessage();
                }


                // register new user
                //if (isNewUser) {
                /*
                long fbUid;
                String name;
                String profileImageUrl;

                FacebookClient client = getFacebookRestClient();
                client.getMyInfo(new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        JSONObject userJSON = response.getJSONObject();

                        // gets friends ids
                        try {
                            fbUid = userJSON.getLong("id");
                            name = userJSON.getString("name");
                            // fbUid = userJSON.getLong("id");
                            profileImageUrl = userJSON.getJSONObject("picture")
                                    .getJSONObject("data")
                                    .getString("url");


                        } catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }); */

                //FirebaseUser user = getmUser();
                //writeNewUser(user.getProviderId(), user.getDisplayName(), user.getPhotoUrl().toString(),facebookFriends);
                //}
            }
                // return your fb friends' ids
                return facebookFriends;
    }


    // facebook client singleton
    public static FacebookClient getFacebookRestClient() {
        if (facebookClient == null) {
            facebookClient = new FacebookClient(context);
        }
        return facebookClient;
    }

    public static DatabaseReference getDatabase() {
        if (mDatabase == null) {
            mDatabase =  (DatabaseReference) FirebaseDatabase.getInstance().getReference();
        }

        return mDatabase;
    }

    public static void writeNewUser(String userId, String name, String pictureUrl, ArrayList<User> friends) {
        User user = new User(name, pictureUrl, friends);
        DatabaseReference mDatabase = CupidApplication.getDatabase();

        mDatabase.child("users").child(userId).setValue(user);
    }

    public static void setIsNewUser(boolean isNewUser) {
        CupidApplication.isNewUser = isNewUser;
    }

    public static FirebaseUser getmUser() {
        if (mUser == null) {
            getmAuth();
            mUser = mAuth.getCurrentUser();
        }
        return mUser;
    }

    public static FirebaseAuth getmAuth() {
        if (mAuth == null) {
            // Initialize Firebase Auth
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }


}
