package rs.ac.uns.ftn.weplayserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.weplayserver.dto.GameRoomMap;
import rs.ac.uns.ftn.weplayserver.dto.GamingRoomDTO;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;
import rs.ac.uns.ftn.weplayserver.repository.GamingRoomRepository;

@RestController
@RequestMapping(value = "/gamingroom")
public class GameRoomController {
	
	@Autowired
	GamingRoomRepository grRepo;

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
