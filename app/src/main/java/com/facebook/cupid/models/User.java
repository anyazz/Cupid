package com.facebook.cupid.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by gabrielsaruhashi on 11/11/17.
 */

public class User implements Parcelable{

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

    protected User(Parcel in) {
        username = in.readString();
        pictureUrl = in.readString();
        facebookFriends = in.createTypedArrayList(Friend.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeList(this.facebookFriends);
        dest.writeString(this.pictureUrl);
    }
}
