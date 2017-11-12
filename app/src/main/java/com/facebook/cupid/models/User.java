package com.facebook.cupid.models;

import android.os.Parcel;
import android.os.Parcelable;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Emily on 11/11/2017.
 */
// object class
public class User implements Parcelable {
    // name
    // picture
    public long fbUserID;
    public String name;
    public String pictureUrl;
    public String school;
    public int age;
    public ArrayList<User> facebookFriends;


    public User(){}

    protected User(Parcel in) {
        fbUserID = in.readLong();
        name = in.readString();
        pictureUrl = in.readString();
    }

    public User(String username, String pictureUrl, ArrayList<User> facebookFriends) {
        this.name = username;
        this.pictureUrl = pictureUrl;
        this.facebookFriends = facebookFriends;
    }

    // for retrieving facebook data

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

    public static User fromJSON(JSONObject json) {
        Random rand = new Random();
        User user = new User();
        try {
            user.setFbUserID(json.getLong("id"));
            user.setName(json.getString("name"));
            JSONObject pictureData = json.getJSONObject("picture").getJSONObject("data");
            user.setPictureUrl(pictureData.getString("url"));
            JSONObject education = json.getJSONArray("education").getJSONObject(0);
            user.setSchool(education.getJSONObject("school").getString("name"));
            user.setAge(rand.nextInt(3)+18);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getName(){
        return this.name;
    }
    public String getPictureUrl(){
        return this.pictureUrl;
    }

    public long getFbUserID() {
        return fbUserID;
    }

    public void setFbUserID(long fbUserID) {
        this.fbUserID = fbUserID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.fbUserID);
        dest.writeString(this.pictureUrl);
    }
}
