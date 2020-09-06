package ftn.tim34.weplay.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ftn.tim34.weplay.sync.GamingRoomSqlSync;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.adapters.HomeTabPagerAdapter;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.GamingRoom;
import ftn.tim34.weplay.service.ServiceUtils;

public class HomeFragment extends Fragment {

    public static List<GameRoom> gameRooms = new ArrayList<GameRoom>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.game_room_tab);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager_game_rooms);
        viewPager.setAdapter(new HomeTabPagerAdapter(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        sharedPreferences = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
        getActivity().setTitle("WePlay - Home");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Date d = new Date();
        long date = sharedPreferences.getLong("last_sync",0);
        if(date == 0){
            date = d.getTime();
        }
        editor.putLong("last_sync", d.getTime());
        editor.apply();
        Call<List<GamingRoom>> call = ServiceUtils.gameRoomService.getAllSync(date);
        call.enqueue(new Callback<List<GamingRoom>>() {
            @Override
            public void onResponse(Call<List<GamingRoom>> call, Response<List<GamingRoom>> response) {
                Log.d("ODGOVOR", response.body().toString());
                GamingRoomSqlSync.fillDatabase((ArrayList<GamingRoom>) response.body(),getContext());
            }

            @Override
            public void onFailure(Call<List<GamingRoom>> call, Throwable t) {

            }
        });
    }
}
