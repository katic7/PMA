package ftn.tim34.weplay.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import ftn.tim34.weplay.GameRoomEventsFragment;
import ftn.tim34.weplay.GameRoomFragment;
import ftn.tim34.weplay.GameRoomReviewsFragment;
import ftn.tim34.weplay.model.GameRoom;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private GameRoom selected;

    public TabPagerAdapter(@NonNull FragmentManager fm, GameRoom selected) {
        super(fm);
        this.selected = selected;
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
                fragment=new GameRoomFragment(selected);
                break;
            case 1:
                fragment=new GameRoomEventsFragment(selected);
                break;
            case 2:
                fragment = new GameRoomReviewsFragment(selected);
                break;
        }

        return  fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
