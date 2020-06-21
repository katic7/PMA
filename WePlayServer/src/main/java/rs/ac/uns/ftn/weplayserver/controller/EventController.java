package rs.ac.uns.ftn.weplayserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.weplayserver.dto.EventDTO;
import rs.ac.uns.ftn.weplayserver.model.Event;
import rs.ac.uns.ftn.weplayserver.model.Game;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;
import rs.ac.uns.ftn.weplayserver.model.Notification;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.EventRepository;
import rs.ac.uns.ftn.weplayserver.repository.GameRepository;
import rs.ac.uns.ftn.weplayserver.repository.GamingRoomRepository;
import rs.ac.uns.ftn.weplayserver.repository.UserRepository;

@RestController
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	EventRepository eventRepo;
	
	@Autowired
	GamingRoomRepository grRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	@GetMapping("/getAll/{id}")
	public ResponseEntity<List<EventDTO>> getAllByGameRoom(@PathVariable Long id){
		List<Event> events = eventRepo.getAllByGamingRoom(id);
		List<EventDTO> retVal = new ArrayList<EventDTO>();
		
		for(Event e : events) {
			EventDTO edto = new EventDTO(e);
			retVal.add(edto);
		}
		return new ResponseEntity<List<EventDTO>>(retVal, HttpStatus.OK);
	}
	
	@PostMapping("create/{id}")
	public ResponseEntity<?> createEvent(@PathVariable Long id, @RequestBody EventDTO e){
		GamingRoom gr = grRepo.getOne(id);
		Event ev = new Event();
		Game game = gameRepo.findByName(e.getName());
		List<Notification> notif = new ArrayList<Notification>();
		List<User> parti = new ArrayList<User>();
		
		User u = userRepo.findByEmail(e.getCreator_email()); 
		
		ev.setCreator(u);
		ev.setDescription(e.getDescription());
		ev.setEventNotifications(notif);
		ev.setGamingRoom(gr);
		ev.setJoinDeadline(e.getJoinDeadline());
		ev.setNumbOfPlayers(e.getNumbOfPlayers());
		ev.setName(e.getName());
		ev.setParticipants(parti);
		ev.setGame(game);
		eventRepo.save(ev);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
