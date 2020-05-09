package ftn.tim34.weplay.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ftn.tim34.weplay.FragmentGameRoomList;
import ftn.tim34.weplay.GameRoomMapFragment;

public class HomeTabPagerAdapter extends FragmentStatePagerAdapter {

    public HomeTabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String retVal = "";
        switch (position) {
            case 0:
                retVal = "List View";
                break;
            case 1:
                retVal = "Map View";
                break;
        }
        return  retVal;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new FragmentGameRoomList();
                break;
            case 1:
                fragment=new GameRoomMapFragment();
                break;
        }

        return  fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
