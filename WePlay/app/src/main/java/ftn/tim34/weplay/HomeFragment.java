package ftn.tim34.weplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ftn.tim34.weplay.adapters.HomeTabPagerAdapter;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.Review;
import ftn.tim34.weplay.service.ServiceUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
