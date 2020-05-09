package ftn.tim34.weplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.Review;

public class HomeFragment extends Fragment {

    public static List<GameRoom> gameRooms = new ArrayList<GameRoom>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(gameRooms.size() == 0) {
            gameRooms.add(new GameRoom("GameRoom 1", 100, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 2", 110, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 3", 120, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 4", 130, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.add(new GameRoom("GameRoom 5", 140, "0-24", "123 456", Float.parseFloat("4.2")));
            gameRooms.get(0).getReviews().add(new Review("Nemanja", "Odlicna igraonica1", Float.parseFloat("4.5")));
            gameRooms.get(0).getReviews().add(new Review("Nemanja", "Odlicna igraonica2", Float.parseFloat("4.6")));
            gameRooms.get(1).getReviews().add(new Review("Nemanja", "Odlicna igraonica3", Float.parseFloat("4.7")));
            gameRooms.get(1).getReviews().add(new Review("Nemanja", "Odlicna igraonica4", Float.parseFloat("4.5")));
            gameRooms.get(2).getReviews().add(new Review("Nemanja", "Odlicna igraonica5", Float.parseFloat("4.8")));
            gameRooms.get(2).getReviews().add(new Review("Nemanja", "Odlicna igraonica6", Float.parseFloat("4.9")));
            gameRooms.get(2).getReviews().add(new Review("Nemanja", "Odlicna igraonica7", Float.parseFloat("4.4")));
            gameRooms.get(3).getReviews().add(new Review("Nemanja", "Odlicna igraonica8", Float.parseFloat("4.3")));
            gameRooms.get(3).getReviews().add(new Review("Nemanja", "Odlicna igraonica9", Float.parseFloat("4.54")));

            gameRooms.get(0).getEvents().add(new Event("Event 1", "League Of Legends", 5, 3, gameRooms.get(0), new Date()));
            gameRooms.get(0).getEvents().add(new Event("Event 2", "League Of Legends", 5, 3, gameRooms.get(0), new Date()));
            gameRooms.get(0).getEvents().add(new Event("Event 3", "League Of Legends", 5, 3, gameRooms.get(0), new Date()));
            gameRooms.get(1).getEvents().add(new Event("Event 4", "League Of Legends", 5, 3, gameRooms.get(1), new Date()));
            gameRooms.get(1).getEvents().add(new Event("Event 5", "League Of Legends", 5, 3, gameRooms.get(1), new Date()));
            gameRooms.get(1).getEvents().add(new Event("Event 6", "League Of Legends", 5, 3, gameRooms.get(1), new Date()));
            gameRooms.get(2).getEvents().add(new Event("Event 7", "League Of Legends", 5, 3, gameRooms.get(2), new Date()));
        }

        return inflater.inflate(R.layout.fragment_home,container,false);
    }
}
