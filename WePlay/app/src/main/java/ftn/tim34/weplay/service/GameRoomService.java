package ftn.tim34.weplay.service;

import java.util.List;

import ftn.tim34.weplay.model.GameRoom;
import ftn.tim34.weplay.dto.GamingRoomMap;
import ftn.tim34.weplay.model.GamingRoom;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GameRoomService {

    @GET(ServiceUtils.GET_ALL_GAMING_ROOMS)
    Call<List<GameRoom>> getAll();

    @GET(ServiceUtils.GET_GAMING_ROOM_MAP)
    Call<List<GamingRoomMap>> getAllForMap();

    @GET(ServiceUtils.GET_ALL_GR_SYNC + "{date}")
    Call<List<GamingRoom>> getAllSync(@Path("date") long date);

    @GET(ServiceUtils.GET_GR_BY_ID + "{id}"+"/katicmilan7@gmail.com")
    Call<GameRoom> getById(@Path("id") Long id);

    @PUT(ServiceUtils.CHANGE_FAVOURITE + "{id}" + "/katicmilan7@gmail.com/" + "{flag}")
    Call<ResponseBody> changeFavourite(@Path("id")Long id, @Path("flag")boolean flag);

    @GET(ServiceUtils.GET_FAVOURITE + "{email}")
    Call<List<GamingRoom>> getFavourites(@Path("email")String email);
}
