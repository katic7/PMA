package ftn.tim34.weplay.service;

import java.util.List;

import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.dto.GamingRoomMap;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GameRoomService {

    @GET(ServiceUtils.GET_ALL_GAMING_ROOMS)
    Call<List<GameRoom>> getAll();

    @GET(ServiceUtils.GET_GAMING_ROOM_MAP)
    Call<List<GamingRoomMap>> getAllForMap();
}
