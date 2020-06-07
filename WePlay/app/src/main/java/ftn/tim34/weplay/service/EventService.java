package ftn.tim34.weplay.service;

import java.util.List;

import ftn.tim34.weplay.model.Event;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventService {

    @GET(ServiceUtils.GET_GAMING_ROOM_EVENTS+"{id}")
    Call<List<Event>> getAllGamingRoomEvents(@Path("id") Long id);
}
