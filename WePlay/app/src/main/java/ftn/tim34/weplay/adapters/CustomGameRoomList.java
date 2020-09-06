package ftn.tim34.weplay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.activities.GameRoomActivity;
import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.GamingRoom;

public class CustomGameRoomList extends BaseAdapter {

    private List<GamingRoom> gameRooms = new ArrayList<>();
    private Context context;

    public CustomGameRoomList(Context context, List<GamingRoom> list) {
        this.context = context;
        this.gameRooms = list;
    }

    @Override
    public int getCount() {
        return gameRooms.size();
    }

    @Override
    public Object getItem(int position) {
        return gameRooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_game_room_list, null);
        }
        TextView roomName = (TextView) row.findViewById(R.id.gameRoomName);
        TextView roomRating = (TextView) row.findViewById(R.id.roomRating);
        roomName.setText(gameRooms.get(position).getName());
        roomRating.setText("Rating: " + gameRooms.get(position).getRating());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameRoomActivity.class);
                intent.putExtra("gameRoomId",gameRooms.get(position).getId());
                context.startActivity(intent);
            }
        });
        return row;
    }
}