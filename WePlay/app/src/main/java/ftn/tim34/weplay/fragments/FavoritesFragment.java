package ftn.tim34.weplay.fragments;

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

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.activities.GameRoomActivity;
import ftn.tim34.weplay.activities.MainActivity;
import ftn.tim34.weplay.adapters.CustomGameRoomList;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.GamingRoom;
import ftn.tim34.weplay.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesFragment extends Fragment {
    private ListView listView;
    private List<GamingRoom> gameRooms;
    private List<String> arrayOfGameRoomNames = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favorite,container,false);
        //gameRooms = MainActivity.gameRooms;
        getActivity().setTitle("WePlay - Favourites");

        listView = view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<List<GamingRoom>> call = ServiceUtils.gameRoomService.getFavourites("katicmilan7@gmail.com");
        call.enqueue(new Callback<List<GamingRoom>>() {
            @Override
            public void onResponse(Call<List<GamingRoom>> call, Response<List<GamingRoom>> response) {
                CustomGameRoomList adapter = new CustomGameRoomList(getContext(), response.body());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GamingRoom>> call, Throwable t) {

            }
        });
    }
}
