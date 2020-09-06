package ftn.tim34.weplay.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.activities.EventDetailsActivity;
import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.Event;

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
        TextView freeSpace = (TextView) row.findViewById(R.id.numOfPlayers);
        TextView game = (TextView) row.findViewById(R.id.game);
        ImageView imageGame = (ImageView) row.findViewById(R.id.imageGame);
        if(events.get(position).getGame().equals("GTA V")){
            imageGame.setImageResource(R.drawable.gta5);
        }else if(events.get(position).getGame().equals("CS GO")){
            imageGame.setImageResource(R.drawable.cs_go);
        }else if(events.get(position).getGame().equals("Fortnite")){
            imageGame.setImageResource(R.drawable.fortnite);
        }else if(events.get(position).getGame().equals("LOL")){
            imageGame.setImageResource(R.drawable.lol);
        }else if(events.get(position).getGame().equals("Call of Duty")){
            imageGame.setImageResource(R.drawable.cod);
        }else if(events.get(position).getGame().equals("Fifa 2020")){
            imageGame.setImageResource(R.drawable.fifa);
        }
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
        freeSpace.setText("Players joined: " + events.get(position).getParticipants().size() + "/" + events.get(position).getNumberOfPlayers());
        return row;
    }
}

