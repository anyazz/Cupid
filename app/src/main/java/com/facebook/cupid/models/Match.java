package com.facebook.cupid.models;

/**
 * Created by brucegatete on 11/12/17.
 */

public class Match {
    public String Friend_01;
    public  String Friend_02;

    public Match () {

    }
    public Match (String name_1, String name_2){
        Friend_01 = name_1;
        Friend_02 = name_2;
    }
}
