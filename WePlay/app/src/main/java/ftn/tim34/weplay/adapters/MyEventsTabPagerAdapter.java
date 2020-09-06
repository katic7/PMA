package ftn.tim34.weplay.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ftn.tim34.weplay.fragments.MyEventsCreated;
import ftn.tim34.weplay.fragments.MyEventsJoined;
import ftn.tim34.weplay.fragments.MyEventsSubscirbed;

public class MyEventsTabPagerAdapter extends FragmentStatePagerAdapter {

    @SuppressLint("WrongConstant")
    public MyEventsTabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String retVal = "";
        switch (position) {
            case 0:
                retVal = "Created";
                break;
            case 1:
                retVal = "Subscribed";
                break;
            case 2:
                retVal = "Joined";
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
                fragment=new MyEventsCreated();
                break;
            case 1:
                fragment=new MyEventsSubscirbed();
                break;
            case 2:
                fragment=new MyEventsJoined();
                break;
        }

        return  fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
