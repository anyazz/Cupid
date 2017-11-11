package com.facebook.cupid;

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
    String name;
    String pictureUrl;

    public Friend(){}

    public static Friend fromJSON(JSONObject object) throws JSONException{
        Friend friend = new Friend();
        friend.name = object.getString("Name");
        friend.pictureUrl = object.getString("URL");
        return friend;
    }

    public String getName(){
        return this.name;
    }
    public String getPictureUrl(){
        return this.pictureUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
