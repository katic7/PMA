package ftn.tim34.weplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import ftn.tim34.weplay.adapters.TabPagerAdapter;
import ftn.tim34.weplay.model.GameRoom;

public class GameRoomActivity extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_room);

        Bundle extras = getIntent().getExtras();
        GameRoom selected = (GameRoom) extras.get("gameroom");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.game_room_tabs_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_events);
        viewPager.setAdapter(new TabPagerAdapter(this.getSupportFragmentManager(), selected));
        tabLayout.setupWithViewPager(viewPager);

    }
}
