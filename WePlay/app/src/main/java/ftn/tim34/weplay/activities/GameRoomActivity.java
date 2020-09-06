package ftn.tim34.weplay.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.adapters.TabPagerAdapter;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRoomActivity extends AppCompatActivity   {

    private Long grId;
    private GameRoom gr;
    private FragmentManager fm;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);

        Bundle extras = getIntent().getExtras();
        fm = this.getSupportFragmentManager();
        grId = (Long) extras.get("gameRoomId");
        tabLayout = (TabLayout) findViewById(R.id.game_room_tabs_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager_events);
        viewPager.setAdapter(new TabPagerAdapter(fm, grId));
        tabLayout.setupWithViewPager(viewPager);

    }
}
