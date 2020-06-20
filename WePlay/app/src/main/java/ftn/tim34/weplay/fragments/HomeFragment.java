package ftn.tim34.weplay.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.adapters.HomeTabPagerAdapter;
import ftn.tim34.weplay.model.GameRoom;

public class HomeFragment extends Fragment {

    public static List<GameRoom> gameRooms = new ArrayList<GameRoom>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.game_room_tab);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager_game_rooms);
        viewPager.setAdapter(new HomeTabPagerAdapter(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


}
