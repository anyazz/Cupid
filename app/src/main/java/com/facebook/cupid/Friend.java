package com.facebook.cupid;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emily on 11/11/2017.
 */
// object class
public class Friend {
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
}
