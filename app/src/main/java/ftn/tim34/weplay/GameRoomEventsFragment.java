package ftn.tim34.weplay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameRoomEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameRoomEventsFragment extends Fragment {
    private GameRoom selected;
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

    public GameRoomEventsFragment(GameRoom g) {
        this.selected = g;
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
        events = selected.getEvents();
        for(Event e : events) {
            arrayEventsName.add(e.getName());
        }
        listView = view.findViewById(R.id.list_view_events);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event selected = new Event();
                for(Event g : events) {
                    if(g.getName().equals(arrayEventsName.get(position))) {
                        selected = g;
                    }
                }
                Intent intent = new Intent(view.getContext(), EventDetailsActivity.class);
                intent.putExtra("event", selected);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        arrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                arrayEventsName);
        listView.setAdapter(arrayAdapter);
        Button createEvent = (Button) view.findViewById(R.id.addEventbutton);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateEventActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
