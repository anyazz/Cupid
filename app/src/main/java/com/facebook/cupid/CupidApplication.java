package com.facebook.cupid;

import android.app.Application;
import android.content.Context;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.auth.FirebaseAuth;

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
    private static ArrayList<Friend> facebookFriends;
    FirebaseAuth mAuth;


    @Override
    public void onCreate() {
        super.onCreate();

        mAuth = FirebaseAuth.getInstance();
    }

    public static ArrayList<Friend> getFacebookFriends() {
            facebookFriends = new ArrayList<>();

        // start a new thread to execute the runnable codeblock
            Thread thread = new Thread( ) {
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
                                    Friend friend = Friend.fromJSON(friends.getJSONObject(i));
                                    facebookFriends.add(friend);
                                }

                            } catch(JSONException e) {
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
            // return your fb friends' ids
            return facebookFriends;

    }

    public static ArrayList<Friend> getFriends() {
        return facebookFriends;
    }

    // facebook client singleton
    public static FacebookClient getFacebookRestClient() {
        if (facebookClient == null) {
            facebookClient = new FacebookClient(context);
        }
        return facebookClient;
    }



}
