package com.facebook.cupid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

/**
 * Created by Emily on 11/11/2017.
 */
// object class
public class User {
    // name
    // picture
    public long fbUserID;
    public String name;
    public String pictureUrl;
    public String school;
    public int age;


    public User(){}

    // for retrieving facebook data
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
}
