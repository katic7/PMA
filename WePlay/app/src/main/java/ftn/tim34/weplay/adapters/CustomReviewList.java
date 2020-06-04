package ftn.tim34.weplay.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.R;
import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.model.Review;

public class CustomReviewList extends BaseAdapter {

    private List<Review> reviews = new ArrayList<>();
    private Context context;

    public CustomReviewList(Context context, List<Review> list) {
        this.context = context;
        this.reviews = list;
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        Log.d("Adapter", "USAO");
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_rating_list, null);
        }
        TextView reviewer = (TextView) row.findViewById(R.id.reviewer_name);
        RatingBar roomRating = (RatingBar) row.findViewById(R.id.rating_bar);
        TextView ratingText = (TextView) row.findViewById(R.id.review_text);
        reviewer.setText(reviews.get(position).getUser());
        roomRating.setRating(reviews.get(position).getStars());
        ratingText.setText(reviews.get(position).getComment());
        return row;
    }
}
