package com.facebook.cupid.models;

import com.facebook.cupid.CupidApplication;

/**
 * Created by amusipatla on 11/12/17.
 */

public class Suggestion {

    public long friend1;
    public long friend2;
    public boolean friend1accept;
    public boolean friend2accept;
    public long matchmakerId;
    public String message;

    public Suggestion(){}

    public Suggestion(User friend1, User friend2, String message){
        this.friend1 = friend1.getFbUserID();
        this.friend2 = friend2.getFbUserID();
        friend1accept = false;
        friend2accept = false;
        this.message = message;
        matchmakerId = Long.parseLong(CupidApplication.getmUser().getProviderId());
    }

    public Suggestion(long friend1, long friend2, boolean f1, boolean f2, String message, long id){
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.friend1accept = f1;
        this.friend2accept = f2;
        this.message = message;
        this.matchmakerId = id;
    }

}
