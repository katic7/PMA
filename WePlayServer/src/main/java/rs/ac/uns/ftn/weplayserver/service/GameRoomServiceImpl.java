package rs.ac.uns.ftn.weplayserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.weplayserver.dto.GamingRoomSync;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.GameRepository;
import rs.ac.uns.ftn.weplayserver.repository.GamingRoomRepository;
import rs.ac.uns.ftn.weplayserver.repository.UserRepository;

@Service
public class GameRoomServiceImpl implements GameRoomService {

	@Autowired
	GamingRoomRepository grRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public boolean isFavourite(Long id, String email) {
		User u = userRepo.findByEmail(email);
		for(GamingRoom gr:u.getFavouriteGameRooms()) {
			if(gr.getId() == id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void changeFavourite(Long id, String email, boolean flag) {
		GamingRoom gr = grRepo.getOne(id);
		User u = userRepo.findByEmail(email);
		if(flag) {
			u.getFavouriteGameRooms().add(gr);
			userRepo.save(u);
		}else {
			for(GamingRoom gro:u.getFavouriteGameRooms()) {
				if(gro.getId() == id) {
					u.getFavouriteGameRooms().remove(gr);
					userRepo.save(u);
					break;
				}
			}
		}
		
	}

	@Override
	public List<GamingRoomSync> getFavourites(String email) {
		User u = userRepo.findByEmail(email);
		List<GamingRoomSync> retVal = new ArrayList<GamingRoomSync>();
		List<GamingRoom>grooms = u.getFavouriteGameRooms();
		for(GamingRoom gr : grooms) {
			GamingRoomSync gsync = new GamingRoomSync(gr);
			retVal.add(gsync);
		}
		return retVal;
	}

}
