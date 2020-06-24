package ftn.tim34.weplay.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.activities.EventInformationsActivity;
import ftn.tim34.weplay.activities.MainActivity;
import ftn.tim34.weplay.adapters.CustomEventList;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyEventsFragment extends Fragment {
    private ListView listView;
    private List<Event> events = new ArrayList<Event>();
    private ArrayAdapter arrayAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyEventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyEventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyEventsFragment newInstance(String param1, String param2) {
        MyEventsFragment fragment = new MyEventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        GameRoom selected = MainActivity.gameRooms.get(0); //temp mock podaci
        View view =inflater.inflate(R.layout.fragment_my_events,container,false);
  //      events = selected.getEvents();

        listView = view.findViewById(R.id.list_view_my_events);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event selected = new Event();
                for(Event g : events) {
                    if(g.getName().equals(arrayEventsName.get(position))) {
                        selected = g;
                    }
                }
                Intent intent = new Intent(view.getContext(), EventInformationsActivity.class);
                intent.putExtra("event", selected);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/
        /*arrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                arrayEventsName);*/

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Call<List<Event>> call = ServiceUtils.eventService.getMyEvents("katicmilan7@gmail.com");
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.code() == 200){
                    if(response.body().size() == 0){
                        Toast.makeText(getContext(), "You don't have any events.", Toast.LENGTH_SHORT).show();
                    }else{
                        events.clear();
                        events = response.body();
                        CustomEventList adapter = new CustomEventList(getContext(),events);
                        listView.setAdapter(adapter);
                    }
                }else{
                    Toast.makeText(getContext(), "Greška.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }
}
