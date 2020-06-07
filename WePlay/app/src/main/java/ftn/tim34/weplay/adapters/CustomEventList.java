package ftn.tim34.weplay.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.EventDetailsActivity;
import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;

public class CustomEventList extends BaseAdapter {

    private List<Event> events = new ArrayList<>();
    private Context context;

    public CustomEventList(Context context, List<Event> list) {
        this.context = context;
        this.events = list;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        Log.d("Adapter", "USAO");
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_event_list, null);
        }
        TextView roomName = (TextView) row.findViewById(R.id.gameRoomName);
        TextView game = (TextView) row.findViewById(R.id.game);
        game.setText(events.get(position).getName());
        roomName.setText("Game room: " + events.get(position).getGameRoom());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailsActivity.class);
                intent.putExtra("event", events.get(position));
                context.startActivity(intent);
            }
        });
        return row;
    }
}

