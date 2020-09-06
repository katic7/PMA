package ftn.tim34.weplay.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.activities.CreateEventActivity;
import ftn.tim34.weplay.adapters.CustomEventList;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameRoomEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameRoomEventsFragment extends Fragment {
    private Long grId;
    private ListView listView;
    private List<Event> events;
    private List<String> arrayEventsName = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameRoomEventsFragment() {
        // Required empty public constructor
    }

    public GameRoomEventsFragment(Long grId) {
        this.grId = grId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameRoomEventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameRoomEventsFragment newInstance(String param1, String param2) {
        GameRoomEventsFragment fragment = new GameRoomEventsFragment();
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
        View view =inflater.inflate(R.layout.fragment_game_room_events,container,false);
        listView = view.findViewById(R.id.list_view_events);
        //      CustomEventList adapter = new CustomEventList(getContext(), events);
//        listView.setAdapter(adapter);

        Button createEvent = (Button) view.findViewById(R.id.addEventbutton);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateEventActivity.class);
                intent.putExtra("roomId", grId);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        final Call<List<Event>> call = ServiceUtils.eventService.getAllGamingRoomEvents(grId);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.code() == 200){
                    CustomEventList adapter = new CustomEventList(getContext(), response.body());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
        super.onResume();
    }
}
