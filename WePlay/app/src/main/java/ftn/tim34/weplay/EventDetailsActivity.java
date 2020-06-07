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
        selected = (Event) extras.get("event");
        TextView naslov = findViewById(R.id.temp);

        TextView game = findViewById(R.id.game);
        TextView deadline = findViewById(R.id.deadline);
        TextView minimum = findViewById(R.id.minimumSkillLevel);
        TextView gameRoom = findViewById(R.id.gamingRoom);
        TextView number = findViewById(R.id.number);

        game.setText(selected.getGame());
        deadline.setText(selected.getDeadline().toString());
        gameRoom.setText(selected.getGameRoom());
        naslov.setText(selected.getName());
        number.setText(selected.getNumberOfActivePlayers() + "/" + selected.getNumberOfPlayers());
        minimum.setText("3");
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.details_map);
            mapFrag.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        LatLng sydney = new LatLng( selected.getLat(), selected.getLon());
        map.addMarker(new MarkerOptions().position(sydney).title(selected.getGameRoom()));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));

    }
}
