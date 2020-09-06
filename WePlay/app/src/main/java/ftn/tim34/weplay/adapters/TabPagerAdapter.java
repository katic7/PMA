package ftn.tim34.weplay.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ftn.tim34.weplay.fragments.GameRoomEventsFragment;
import ftn.tim34.weplay.fragments.GameRoomFragment;
import ftn.tim34.weplay.fragments.GameRoomReviewsFragment;
import ftn.tim34.weplay.model.GameRoom;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private Long grId;

    @SuppressLint("WrongConstant")
    public TabPagerAdapter(@NonNull FragmentManager fm, Long grId) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.grId = grId;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String retVal = "";
        switch (position) {
            case 0:
                retVal = "Game Room";
                break;
            case 1:
                retVal = "Events";
                break;
            case 2:
                retVal = "Reviews";
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
                fragment=new GameRoomFragment(grId);
                break;
            case 1:
                fragment=new GameRoomEventsFragment(grId);
                break;
            case 2:
                fragment = new GameRoomReviewsFragment(grId);
                break;
        }

        return  fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
