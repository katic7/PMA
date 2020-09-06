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
 * Use the {@link MyEventsSubscirbed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyEventsSubscirbed extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ProgressBar pbSubscribed;
    private ListView subscribed_events;
    private TextView tvEmptySub;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyEventsSubscirbed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyEventsSubscirbed.
     */
    // TODO: Rename and change types and number of parameters
    public static MyEventsSubscirbed newInstance(String param1, String param2) {
        MyEventsSubscirbed fragment = new MyEventsSubscirbed();
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
        View view = inflater.inflate(R.layout.fragment_my_events_subscirbed, container, false);

        pbSubscribed = view.findViewById(R.id.pbSubscribed);
        subscribed_events = view.findViewById(R.id.subscribed_events);
        tvEmptySub = view.findViewById(R.id.tvEmptySub);

        subscribed_events.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<List<Event>> call = ServiceUtils.eventService.getSubEvents("katicmilan7@gmail.com");
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                CustomEventList adapter = new CustomEventList(getContext(),response.body());
                subscribed_events.setAdapter(adapter);
                pbSubscribed.setVisibility(View.GONE);
                subscribed_events.setVisibility(View.VISIBLE);
                if(response.body().size() == 0){
                    tvEmptySub.setVisibility(View.VISIBLE);
                }else{
                    tvEmptySub.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }
}
