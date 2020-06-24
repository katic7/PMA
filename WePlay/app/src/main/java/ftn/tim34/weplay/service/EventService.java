package ftn.tim34.weplay.service;

import java.util.List;

import ftn.tim34.weplay.model.Event;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventService {

    @GET(ServiceUtils.GET_GAMING_ROOM_EVENTS+"{id}")
    Call<List<Event>> getAllGamingRoomEvents(@Path("id") Long id);

    @POST(ServiceUtils.CREATE_EVENT + "{id}")
    Call<ResponseBody> createEvent(@Path("id") Long id, @Body Event ev);

    @POST(ServiceUtils.JOIN_EVENT + "{id}" + "/" + "{user}")
    Call<ResponseBody> joinEvent(@Path("id") Long id, @Path("user") String user);

    @POST(ServiceUtils.SUBSCRIBE_EVENT + "{id}" + "/" + "{user}")
    Call<ResponseBody> subscribeEvent(@Path("id") Long id, @Path("user") String user);
}
