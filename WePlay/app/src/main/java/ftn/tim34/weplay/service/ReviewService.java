package ftn.tim34.weplay.service;

import java.util.List;

import ftn.tim34.weplay.dto.Review;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewService {

    @GET(ServiceUtils.GET_ALL_REVIEWS + "{id}")
    Call<List<Review>> getAllReviews(@Path("id") Long id);

    @POST(ServiceUtils.CREATE_REVIEW + "{id}")
    Call<ResponseBody> createReview(@Path("id") Long id, @Body Review review);
}
