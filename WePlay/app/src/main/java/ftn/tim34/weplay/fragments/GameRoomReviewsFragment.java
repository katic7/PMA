package ftn.tim34.weplay.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.adapters.CustomReviewList;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.dto.Review;
import ftn.tim34.weplay.service.ServiceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameRoomReviewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameRoomReviewsFragment extends Fragment {
    private GameRoom selected;
    private List<Review> reviews;
    private ListView listView;
    private List<String> arrayReviews = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameRoomReviewsFragment() {
        // Required empty public constructor
    }

    public GameRoomReviewsFragment(GameRoom g) {
        this.selected = g;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameRoomReviewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameRoomReviewsFragment newInstance(String param1, String param2) {
        GameRoomReviewsFragment fragment = new GameRoomReviewsFragment();
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
        View view =inflater.inflate(R.layout.fragment_game_room_reviews,container,false);
        reviews = selected.getReviews();
        for(Review e : reviews) {
            arrayReviews.add(e.getComment() + "\tOcena:" + e.getStars());
        }
        listView = view.findViewById(R.id.list_view_reviews);



        Button mShowDialog = (Button) view.findViewById(R.id.btnShowDialog);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_new_review, null);
                Button btnAddReview = (Button) mView.findViewById(R.id.btnAddReview);
                btnAddReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vi) {
                        Toast.makeText(vi.getContext(), "Review Successfuly added!", Toast.LENGTH_SHORT).show();

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        final Call<List<Review>> call = ServiceUtils.reviewService.getAllReviews(selected.getId());
        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.code() == 200){
                    CustomReviewList adapter = new CustomReviewList(getContext(), response.body());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });
    }
}
