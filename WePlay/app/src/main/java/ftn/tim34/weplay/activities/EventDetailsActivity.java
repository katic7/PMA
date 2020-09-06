package ftn.tim34.weplay.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;
import org.w3c.dom.Text;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.User;
import ftn.tim34.weplay.service.ServiceUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Event selected;
    private GoogleMap map;
    private Button joinButton;
    private Button subscribeButton;
    private ImageView imgNotif;
    private Context context;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_detail);
        Bundle extras = getIntent().getExtras();
        selected = (Event) extras.get("event");
        TextView naslov = findViewById(R.id.temp);
        context = this;
        joinButton = findViewById(R.id.button2);
        subscribeButton = findViewById(R.id.button3);
        imgNotif = findViewById(R.id.imgNotification);
        TextView game = findViewById(R.id.game);
        TextView deadline = findViewById(R.id.deadline);
        TextView minimum = findViewById(R.id.minimumSkillLevel);
        TextView gameRoom = findViewById(R.id.gamingRoom);
        TextView number = findViewById(R.id.number);
        sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        game.setText(selected.getGame());
        deadline.setText(selected.getDeadline().toString());
        gameRoom.setText(selected.getGameRoom());
        naslov.setText(selected.getName());
        number.setText(selected.getParticipants().size() + "/" + selected.getNumberOfPlayers());
        minimum.setText("3");
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.details_map);
            mapFrag.getMapAsync(this);
        }
        final String logged = sharedPreferences.getString("userEmail", "katicmilan7@gmail.com");

        for(User p: selected.getParticipants()) {
            if (p.getEmail().equals(logged)) { //vec ucestujem
                joinButton.setClickable(false);
                joinButton.setTextColor(getResources().getColor(R.color.aprrovedButton));
                joinButton.setText("JOINED");
                break;
            }
        }

        for(User p: selected.getSubscribers()) {
            if (p.getEmail().equals(logged)) { //vec sam sub
                subscribeButton.setClickable(false);
                subscribeButton.setTextColor(getResources().getColor(R.color.aprrovedButton));
                subscribeButton.setText("SUBSCRIBED");
                imgNotif.setVisibility(View.VISIBLE);
                imgNotif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, EventInformationsActivity.class);
                        intent.putExtra("event", selected);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                break;
            }
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

    public void joinButton(View v) {
        final Call<ResponseBody> call = ServiceUtils.eventService.joinEvent(selected.getId(), sharedPreferences.getString("userEmail", "katicmilan7@gmail.com"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject obj = new JSONObject(response.body().string());
                    joinButton.setClickable(false);
                    if(obj.getInt("code") == 200) {
                        joinButton.setBackgroundColor(getResources().getColor(R.color.aprrovedButton));
                        joinButton.setText("JOINED");
                    } else {
                        joinButton.setBackgroundColor(getResources().getColor(R.color.rejectedButton));
                    }

                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + response.body() + "\"");
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void subscribeButton(View v) {
        final Call<ResponseBody> call = ServiceUtils.eventService.subscribeEvent(selected.getId(), sharedPreferences.getString("userEmail", "katicmilan7@gmail.com"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject obj = new JSONObject(response.body().string());
                    subscribeButton.setClickable(false);
                    if(obj.getInt("code") == 200) {
                        subscribeButton.setBackgroundColor(getResources().getColor(R.color.aprrovedButton));
                        subscribeButton.setText("SUBSCRIBED");
                    } else {
                        subscribeButton.setBackgroundColor(getResources().getColor(R.color.rejectedButton));
                    }
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + response.body() + "\"");
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
