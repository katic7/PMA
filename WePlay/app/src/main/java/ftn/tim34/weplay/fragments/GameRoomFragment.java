package ftn.tim34.weplay.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.service.ServiceUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameRoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameRoomFragment extends Fragment implements OnMapReadyCallback {
    private Long grId;
    private GoogleMap map;
    private ImageView favourite;
    private ImageView nonFavourite;
    private TextView pricePH;
    private TextView workingHours;
    private TextView phone;
    private TextView temp;
    private RatingBar rating;

    private GameRoom selected = new GameRoom();
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

    public GameRoomFragment(Long grId) {
        super();
        this.grId = grId;
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
        favourite = view.findViewById(R.id.favourite);
        nonFavourite = view.findViewById(R.id.nonFavourite);

        temp = (TextView) view.findViewById(R.id.temp);
        rating = (RatingBar) view.findViewById(R.id.ratingBar2);
        pricePH = (TextView) view.findViewById(R.id.pricePerHour);

        phone = (TextView) view.findViewById(R.id.phoneNumber);
        workingHours = (TextView) view.findViewById(R.id.workingHours);

        rating.setIsIndicator(true);
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.details_map);
            mapFrag.getMapAsync(this);
        }
        rating.setClickable(false);

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody>call = ServiceUtils.gameRoomService.changeFavourite(selected.getId(),false);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getContext(),"Igraonica uspešno skinuta sa liste omiljenih!", Toast.LENGTH_SHORT).show();
                        nonFavourite.setVisibility(View.VISIBLE);
                        favourite.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            };
        });

        nonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody>call = ServiceUtils.gameRoomService.changeFavourite(selected.getId(),true);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getContext(),"Igraonica uspešno dodata na listu omiljenih!", Toast.LENGTH_SHORT).show();
                        nonFavourite.setVisibility(View.INVISIBLE);
                        favourite.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            };
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<GameRoom> call = ServiceUtils.gameRoomService.getById(grId);
        call.enqueue(new Callback<GameRoom>() {
            @Override
            public void onResponse(Call<GameRoom> call, Response<GameRoom> response) {
                selected=response.body();
                pricePH.setText(selected.getPrice_per_hour().toString());
                workingHours.setText(selected.getWorking_hours());
                phone.setText(selected.getPhone());
                rating.setRating(selected.getRating());
                temp.setText (selected.getName());
                if(selected.isFavourite()){
                    nonFavourite.setVisibility(View.INVISIBLE);
                }else{
                    favourite.setVisibility(View.INVISIBLE);
                }
                LatLng room = new LatLng( selected.getAddress().getLat(), selected.getAddress().getLon());
                map.addMarker(new MarkerOptions().position(room).title(selected.getName()));
                map.moveCamera(CameraUpdateFactory.newLatLng(room));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(room,16));
            }

            @Override
            public void onFailure(Call<GameRoom> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;


    }
}
