package ftn.tim34.weplay.service;

import java.util.concurrent.TimeUnit;

import ftn.tim34.weplay.model.GameRoom;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {


    public static final String SERVICE_API_PATH = "http://10.0.2.2:8090/";
    public static final String CREATE_USER = "auth/register";
    public static final String LOGIN = "auth/login";
    public static final String GET_ALL_GAMING_ROOMS = "gamingroom/getAll";
    public static final String GET_GAMING_ROOM_EVENTS = "event/getAll/";
    public static final String GET_GAMING_ROOM_MAP = "gamingroom/getAllForMap";

    public static OkHttpClient test() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        return client;
    }

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(test())
            .build();

    public static UserService userService = retrofit.create(UserService.class);
    public static GameRoomService gameRoomService = retrofit.create(GameRoomService.class);
    public static EventService eventService = retrofit.create(EventService.class);
}
