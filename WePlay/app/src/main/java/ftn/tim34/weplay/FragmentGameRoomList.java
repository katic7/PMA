package ftn.tim34.weplay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.adapters.CustomGameRoomList;
import ftn.tim34.weplay.model.GameRoom;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGameRoomList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGameRoomList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<GameRoom> gameRooms;
    private List<String> arrayOfGameRoomNames = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;

    public FragmentGameRoomList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGameRoomList.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGameRoomList newInstance(String param1, String param2) {
        FragmentGameRoomList fragment = new FragmentGameRoomList();
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
        View view =inflater.inflate(R.layout.fragment_game_room_list,container,false);

        ListView gameList = view.findViewById(R.id.game_room_list);
        gameRooms = MainActivity.gameRooms;
        for(GameRoom gr : gameRooms) {
            arrayOfGameRoomNames.add(gr.getName());
        }
        CustomGameRoomList adapter = new CustomGameRoomList(getContext(),gameRooms);
        gameList.setAdapter(adapter);
        /*arrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                arrayOfGameRoomNames);
        gameList.setAdapter(arrayAdapter);*/
        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), GameRoomActivity.class);
                GameRoom selected = new GameRoom();
                for(GameRoom g : gameRooms) {
                    if(g.getName().equals(arrayOfGameRoomNames.get(position))) {
                        selected = g;
                    }
                }

                intent.putExtra("gameroom", selected);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        return view;
    }
}
