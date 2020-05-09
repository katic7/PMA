package ftn.tim34.weplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.model.GameRoom;

public class FavoritesFragment extends Fragment {
    private ListView listView;
    private List<GameRoom> gameRooms;
    private List<String> arrayOfGameRoomNames = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favorite,container,false);
        gameRooms = HomeFragment.gameRooms;

            for (GameRoom gr : gameRooms) {
                arrayOfGameRoomNames.add(gr.getName());
            }

        listView = view.findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter<>(getContext(),
                                          android.R.layout.simple_list_item_1,
                                          arrayOfGameRoomNames);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                //getActivity().finish();
            }
        });
        return view;
    }
}
