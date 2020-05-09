package ftn.tim34.weplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.Review;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    public static List<GameRoom> gameRooms = new ArrayList<GameRoom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(gameRooms.size() == 0) {
            gameRooms.add(new GameRoom("GameRoom 1", 100, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 2", 110, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 3", 120, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 4", 130, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 5", 140, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.get(0).getReviews().add(new Review("Nemanja", "Odlicna igraonica1", Float.parseFloat("4.5")));
            gameRooms.get(0).getReviews().add(new Review("Nemanja", "Odlicna igraonica2", Float.parseFloat("4.6")));
            gameRooms.get(1).getReviews().add(new Review("Nemanja", "Odlicna igraonica3", Float.parseFloat("4.7")));
            gameRooms.get(1).getReviews().add(new Review("Nemanja", "Odlicna igraonica4", Float.parseFloat("4.5")));
            gameRooms.get(2).getReviews().add(new Review("Nemanja", "Odlicna igraonica5", Float.parseFloat("4.8")));
            gameRooms.get(2).getReviews().add(new Review("Nemanja", "Odlicna igraonica6", Float.parseFloat("4.9")));
            gameRooms.get(2).getReviews().add(new Review("Nemanja", "Odlicna igraonica7", Float.parseFloat("4.4")));
            gameRooms.get(3).getReviews().add(new Review("Nemanja", "Odlicna igraonica8", Float.parseFloat("4.3")));
            gameRooms.get(3).getReviews().add(new Review("Nemanja", "Odlicna igraonica9", Float.parseFloat("4.54")));

            gameRooms.get(0).getEvents().add(new Event("Event 1", "League Of Legends", 5, 3, gameRooms.get(0), new Date()));
            gameRooms.get(0).getEvents().add(new Event("Event 2", "League Of Legends", 5, 3, gameRooms.get(0), new Date()));
            gameRooms.get(0).getEvents().add(new Event("Event 3", "League Of Legends", 5, 3, gameRooms.get(0), new Date()));
            gameRooms.get(1).getEvents().add(new Event("Event 4", "League Of Legends", 5, 3, gameRooms.get(1), new Date()));
            gameRooms.get(1).getEvents().add(new Event("Event 5", "League Of Legends", 5, 3, gameRooms.get(1), new Date()));
            gameRooms.get(1).getEvents().add(new Event("Event 6", "League Of Legends", 5, 3, gameRooms.get(1), new Date()));
            gameRooms.get(2).getEvents().add(new Event("Event 7", "League Of Legends", 5, 3, gameRooms.get(2), new Date()));
        }

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyEventsFragment()).commit();
                break;
            case R.id.nav_favorite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoritesFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.log_out:
                Toast.makeText(this,"Log out action", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
