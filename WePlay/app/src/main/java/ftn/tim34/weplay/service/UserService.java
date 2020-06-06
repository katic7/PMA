package ftn.tim34.weplay.service;

import ftn.tim34.weplay.dto.CredentialsDTO;
import ftn.tim34.weplay.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST(ServiceUtils.CREATE_USER)
    Call<ResponseBody> register(@Body User u);

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @POST(ServiceUtils.LOGIN)
    Call<ResponseBody> login(@Body User u);
}
