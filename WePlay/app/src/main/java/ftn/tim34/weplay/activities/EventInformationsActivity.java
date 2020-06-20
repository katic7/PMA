package ftn.tim34.weplay.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ftn.tim34.weplay.R;
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
        Button mShowDialog = (Button) findViewById(R.id.btnShowDialog);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EventInformationsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_event_message, null);
                Button btnAddReview = (Button) mView.findViewById(R.id.btnAddReview);
                btnAddReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vi) {
                        Toast.makeText(vi.getContext(), "News Successfuly added!", Toast.LENGTH_SHORT).show();

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }
}
