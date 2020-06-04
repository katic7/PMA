package ftn.tim34.weplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;

public class EventDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Event selected;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_detail);
        Bundle extras = getIntent().getExtras();
        Event selected = (Event) extras.get("event");
        TextView naslov = findViewById(R.id.temp);
        naslov.setText(selected.getName());

        TextView game = findViewById(R.id.game);
        game.setText(selected.getGame());
        TextView deadline = findViewById(R.id.deadline);
        deadline.setText("25.05.2020.");
        TextView minimum = findViewById(R.id.minimumSkillLevel);
        minimum.setText(selected.getMinimumSkillLevel());
        TextView gameRoom = findViewById(R.id.gamingRoom);
        TextView number = findViewById(R.id.number);
        number.setText("1/5");
        gameRoom.setText(selected.getGameRoom().getName());
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.details_map);
            mapFrag.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        LatLng sydney = new LatLng( 45.207771, 19.716572);
        map.addMarker(new MarkerOptions().position(sydney).title("Beocin"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));

    }
}
