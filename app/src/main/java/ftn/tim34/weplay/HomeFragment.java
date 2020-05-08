package ftn.tim34.weplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.model.GameRoom;

public class HomeFragment extends Fragment {

    public static List<GameRoom> gameRooms = new ArrayList<GameRoom>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gameRooms.add(new GameRoom("GameRoom 1",100, "0-24", "123 456",Float.parseFloat("4.2")));
        gameRooms.add(new GameRoom("GameRoom 2",110, "0-24", "123 456",Float.parseFloat("4.2")));
        gameRooms.add(new GameRoom("GameRoom 3",120, "0-24", "123 456",Float.parseFloat("4.2")));
        gameRooms.add(new GameRoom("GameRoom 4",130, "0-24", "123 456",Float.parseFloat("4.2")));
        gameRooms.add(new GameRoom("GameRoom 5",140, "0-24", "123 456",Float.parseFloat("4.2")));
        return inflater.inflate(R.layout.fragment_home,container,false);
    }
}
