package ftn.tim34.weplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ftn.tim34.weplay.adapters.EventNewsAdapter;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.EventInformation;

public class EventInformationsActivity extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_informations);

        Bundle extras = getIntent().getExtras();
        Event e = (Event) extras.get("event");
        List<EventInformation> allNews = e.getNews();
        EventNewsAdapter adapter = new EventNewsAdapter(this, allNews);



        listView = (ListView) findViewById(R.id.event_news_list);
        listView.setAdapter(adapter);

    }
}
