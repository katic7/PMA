package ftn.tim34.weplay;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.Map;

import ftn.tim34.weplay.model.GameRoom;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameRoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameRoomFragment extends Fragment implements OnMapReadyCallback {
    private GameRoom selected;
    private GoogleMap map;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameRoomFragment() {
        // Required empty public constructor
    }

    public GameRoomFragment(GameRoom g) {
        super();
        this.selected = g;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameRoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameRoomFragment newInstance(String param1, String param2) {
        GameRoomFragment fragment = new GameRoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_game_room,container,false);
        TextView temp = (TextView) view.findViewById(R.id.temp);
        RatingBar rating = (RatingBar) view.findViewById(R.id.ratingBar2);
        TextView pricePH = (TextView) view.findViewById(R.id.pricePerHour);
        pricePH.setText(selected.getPrice_per_hour().toString());
        TextView phone = (TextView) view.findViewById(R.id.phoneNumber);
        TextView workingHours = (TextView) view.findViewById(R.id.workingHours);
        workingHours.setText(selected.getWorking_hours());
        rating.setNumStars(5);
        rating.setIsIndicator(true);
        rating.setMax(5);
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.details_map);
            mapFrag.getMapAsync(this);
        }
        rating.setClickable(false);
        phone.setText(selected.getPhone());
        rating.setRating(selected.getRating());
        temp.setText (selected.getName());
        return view;
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