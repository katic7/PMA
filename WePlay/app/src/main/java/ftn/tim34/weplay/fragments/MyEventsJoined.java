package ftn.tim34.weplay.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.adapters.CustomEventList;
import ftn.tim34.weplay.model.Event;
import ftn.tim34.weplay.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyEventsJoined#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyEventsJoined extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ProgressBar pbJoined;
    private TextView tvEmpty;
    private ListView joined_events;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyEventsJoined() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyEventsJoined.
     */
    // TODO: Rename and change types and number of parameters
    public static MyEventsJoined newInstance(String param1, String param2) {
        MyEventsJoined fragment = new MyEventsJoined();
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
        View view = inflater.inflate(R.layout.fragment_my_events_joined, container, false);

        pbJoined = view.findViewById(R.id.pbJoined);
        joined_events = view.findViewById(R.id.joined_events);
        tvEmpty = view.findViewById(R.id.tvEmpty);

        joined_events.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<List<Event>> call = ServiceUtils.eventService.getJoinedEvents("katicmilan7@gmail.com");
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                CustomEventList adapter = new CustomEventList(getContext(),response.body());
                joined_events.setAdapter(adapter);
                pbJoined.setVisibility(View.GONE);
                joined_events.setVisibility(View.VISIBLE);
                if(response.body().size() == 0){
                    tvEmpty.setVisibility(View.VISIBLE);
                }else{
                    tvEmpty.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }
}
