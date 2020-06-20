package ftn.tim34.weplay.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.adapters.CustomGameRoomList;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private ListView gameList;

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

        gameList = view.findViewById(R.id.game_room_list);
        return view;
    }

    @Override
    public void onResume() {
        final Call<List<GameRoom>> call = ServiceUtils.gameRoomService.getAll();
        call.enqueue(new Callback<List<GameRoom>>() {
            @Override
            public void onResponse(Call<List<GameRoom>> call, Response<List<GameRoom>> response) {
                if(response.code() == 200){
                    CustomGameRoomList adapter = new CustomGameRoomList(getContext(),response.body());
                    gameList.setAdapter(adapter);
                }else{
                    Toast.makeText(getContext(), "Došlo je do greške.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<GameRoom>> call, Throwable t) {

            }
        });

        super.onResume();
    }

}
