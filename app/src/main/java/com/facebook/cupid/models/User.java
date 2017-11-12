package com.facebook.cupid.models;

import java.util.ArrayList;

/**
 * Created by gabrielsaruhashi on 11/11/17.
 */

public class User {

    public String username;
    public String pictureUrl;
    public ArrayList<Friend> facebookFriends;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String pictureUrl, ArrayList<Friend> facebookFriends) {
        this.username = username;
        this.pictureUrl = pictureUrl;
        this.facebookFriends = facebookFriends;
    }

}
