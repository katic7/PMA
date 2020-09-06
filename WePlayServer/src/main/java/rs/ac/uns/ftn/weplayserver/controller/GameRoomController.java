package rs.ac.uns.ftn.weplayserver.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.weplayserver.dto.GameRoomMap;
import rs.ac.uns.ftn.weplayserver.dto.GamingRoomDTO;
import rs.ac.uns.ftn.weplayserver.dto.GamingRoomSync;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.GamingRoomRepository;
import rs.ac.uns.ftn.weplayserver.service.GameRoomService;

@RestController
@RequestMapping(value = "/gamingroom")
public class GameRoomController {
	
	@Autowired
	GamingRoomRepository grRepo;
	
	@Autowired
	GameRoomService grService;

	@GetMapping("getAll")
	public List<GamingRoomDTO> getAll(){
		List<GamingRoomDTO> retVal = new ArrayList<GamingRoomDTO>();
		List<GamingRoom> grooms = grRepo.findAll();
		for(GamingRoom gr : grooms) {
			GamingRoomDTO grDto = new GamingRoomDTO(gr);
			retVal.add(grDto);
		}
		return retVal;
	}
	
	@PutMapping("/changeFavourite/{id}/{email}/{flag}")
	public ResponseEntity<?>changeFav(@PathVariable Long id, @PathVariable String email,@PathVariable boolean flag){
		grService.changeFavourite(id, email, flag);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("{id}/{email}")
	public ResponseEntity<?> getOne(@PathVariable Long id,@PathVariable String email){
		GamingRoomDTO gr = new GamingRoomDTO(grRepo.getOne(id));
		gr.setFavourite(grService.isFavourite(id, email));
		return new ResponseEntity<GamingRoomDTO>(gr, HttpStatus.OK);
	}
	
	@GetMapping("favourites/{email}")
	public ResponseEntity<?> getFavourites(@PathVariable String email){
		return new ResponseEntity<List<GamingRoomSync>>(grService.getFavourites(email), HttpStatus.OK);
	}
	
	@GetMapping("getAll/{date}")
	public List<GamingRoomSync> getAll(@PathVariable long date){
		System.out.println("USAO " + date);
		List<GamingRoomSync> retVal = new ArrayList<GamingRoomSync>();
		List<GamingRoom> grooms = grRepo.getAllForSync(date);
		for(GamingRoom gr : grooms) {
			GamingRoomSync grSync = new GamingRoomSync(gr);
			retVal.add(grSync);
		}
		return retVal;
	}
	
	@GetMapping("getAllForMap")
	public ResponseEntity<List<GameRoomMap>> getAllForMap(){
		List<GameRoomMap> retVal = new ArrayList<GameRoomMap>();
		List<GamingRoom> list = grRepo.findAll();
		for(GamingRoom gr:list) {
			GameRoomMap grm = new GameRoomMap(gr);
			retVal.add(grm);
		}
		return new ResponseEntity<List<GameRoomMap>>(retVal,HttpStatus.OK);
	}
}
