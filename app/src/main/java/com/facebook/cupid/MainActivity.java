package  com.facebook.cupid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.facebook.cupid.models.CustomViewPager;

import com.facebook.cupid.models.User;

public class MainActivity extends AppCompatActivity {
    private User friend1;

    public User getFriend1() {
        return friend1;
    }

    public void setFriend1(User friend1) {
        this.friend1 = friend1;
    }

    private TextView mTextMessage;
    RecyclerView rvFriends;
    Context context;
    FriendListFragment fragment;
    PagerAdapter pagerAdapter;

    public DatabaseReference mDatabase;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        CustomViewPager vpPager = (CustomViewPager) findViewById(R.id.customviewpager);
        vpPager.setPagingEnabled(false);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

}
