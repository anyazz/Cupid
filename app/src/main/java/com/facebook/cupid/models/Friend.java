package com.facebook.cupid.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emily on 11/11/2017.
 */
// object class
public class Friend implements Parcelable{
    // name
    // picture
    private long fbUserID;
    private String name;
    private String pictureUrl;

    public Friend(){}

    protected Friend(Parcel in) {
        fbUserID = in.readLong();
        name = in.readString();
        pictureUrl = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    // for retrieving facebook data
    public static Friend fromJSON(JSONObject json) {
        Friend fbFriend = new Friend();
        try {
            fbFriend.setFbUserID(json.getLong("id"));
            fbFriend.setName(json.getString("name"));
            JSONObject pictureData = json.getJSONObject("picture").getJSONObject("data");
            fbFriend.setPictureUrl(pictureData.getString("url"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fbFriend;
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