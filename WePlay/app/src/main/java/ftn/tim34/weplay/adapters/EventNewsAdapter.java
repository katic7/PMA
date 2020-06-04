package ftn.tim34.weplay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.EventInformation;

public class EventNewsAdapter extends ArrayAdapter<EventInformation> {
    public EventNewsAdapter(Context context, List<EventInformation> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EventInformation info = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        TextView rez = (TextView) convertView.findViewById(R.id.rezultat);
        rez.setText(info.getScore());

        tvName.setText(info.getUser());
        tvHome.setText(info.getComment());

        return convertView;
    }
}
