package rs.ac.uns.ftn.weplayserver.service;

import java.util.List;

import rs.ac.uns.ftn.weplayserver.dto.GamingRoomSync;

public interface GameRoomService {
	boolean isFavourite(Long id, String email);
	void changeFavourite(Long id, String email, boolean flag);
	List<GamingRoomSync>getFavourites(String email);
}
