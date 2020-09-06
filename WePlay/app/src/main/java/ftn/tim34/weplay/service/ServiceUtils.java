package ftn.tim34.weplay.service;

import java.util.concurrent.TimeUnit;

import ftn.tim34.weplay.model.GameRoom;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {

//10.0.2.2
    public static final String SERVICE_API_PATH = "http://192.168.0.22:8090/";
    public static final String CREATE_USER = "auth/register";
    public static final String LOGIN = "auth/login";
    public static final String GET_ALL_GAMING_ROOMS = "gamingroom/getAll";
    public static final String GET_GAMING_ROOM_EVENTS = "event/getAll/";
    public static final String JOIN_EVENT = "event/join/";
    public static final String SUBSCRIBE_EVENT = "event/subscribe/";
    public static final String GET_GAMING_ROOM_MAP = "gamingroom/getAllForMap";
    public static final String GET_ALL_REVIEWS = "review/getAll/";
    public static final String CREATE_REVIEW = "review/create/";
    public static final String CREATE_EVENT = "event/create/";
    public static final String GET_MY_EVENTS = "event/getMyEvents/";
    public static final String GET_ALL_GR_SYNC = "gamingroom/getAll/";
    public static final String GET_GR_BY_ID = "gamingroom/";
    public static final String CHANGE_FAVOURITE = "gamingroom/changeFavourite/";
    public static final String GET_FAVOURITE = "gamingroom/favourites/";
    public static final String MY_EVENTS_CREATED = "event/created/";
    public static final String MY_EVENTS_JOINED = "event/joined/";
    public static final String MY_EVENTS_SUBSCRIBEDED = "event/subscribed/";

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
    public static ReviewService reviewService = retrofit.create(ReviewService.class);
}
