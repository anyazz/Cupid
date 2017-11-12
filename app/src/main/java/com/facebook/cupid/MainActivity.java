package  com.facebook.cupid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    MainActivityAdapter adapter;
    ArrayList<User> friends;
    private TextView mTextMessage;
    RecyclerView rvFriends;
    Context context;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_profile:
//                    Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
//                    startActivity(profile);
                    return true;
                case R.id.navigation_notifications:
//                    Intent profile = new Intent(MainActivity.this, NotifsActivity.class);
//                    startActivity(profile);
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
        friends = CupidApplication.getFacebookFriends();
        adapter = new MainActivityAdapter(friends);

        rvFriends = (RecyclerView) findViewById(R.id.rv_friends_list);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
        rvFriends.setAdapter(adapter);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public ArrayList<User> sortFriends(ArrayList<User> friends) {
        Collections.sort(friends, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getSchool().compareToIgnoreCase(o2.getSchool());
            }
        });
        return friends;
    }

}
