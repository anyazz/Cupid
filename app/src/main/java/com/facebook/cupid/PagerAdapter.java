package com.facebook.cupid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Emily on 11/11/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter{
    FriendListFragment friendListFragment;
    SuggestionsFragment suggestionsFragment;
    NotificationFragment notificationFragment;
    private String tabTitles[] = new String [] {"Home", "Profile", "Notifications"};
    public PagerAdapter(FragmentManager supportFragmentManager){
        super(supportFragmentManager);
        friendListFragment = new FriendListFragment();
        notificationFragment = new NotificationFragment();
        suggestionsFragment = new SuggestionsFragment();
        notificationFragment = new NotificationFragment();
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return friendListFragment;
            //return new HomeTimelineFragment(); //timeline fragment = getTimelineInstance; return timeline fragment
        }else if(position == 1){
            return suggestionsFragment;
            // return new MentionTimelineFragment();
        }else if(position == 2){
            return notificationFragment;
        }else{
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // generate titles bailed on positoin
        return tabTitles[position];

    }
}
