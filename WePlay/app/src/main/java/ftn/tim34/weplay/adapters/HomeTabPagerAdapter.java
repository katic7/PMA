package ftn.tim34.weplay.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ftn.tim34.weplay.fragments.FragmentGameRoomList;
import ftn.tim34.weplay.fragments.GameRoomMapFragment;

public class HomeTabPagerAdapter extends FragmentStatePagerAdapter {

    @SuppressLint("WrongConstant")
    public HomeTabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
