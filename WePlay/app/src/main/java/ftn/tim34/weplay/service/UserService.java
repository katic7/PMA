package ftn.tim34.weplay.service;

import ftn.tim34.weplay.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/register")
    Call<ResponseBody> register(@Body User u);
}
