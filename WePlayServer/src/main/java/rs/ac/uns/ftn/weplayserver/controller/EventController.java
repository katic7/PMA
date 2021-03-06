package rs.ac.uns.ftn.weplayserver.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import rs.ac.uns.ftn.weplayserver.dto.StringDTO;
import rs.ac.uns.ftn.weplayserver.model.Event;
import rs.ac.uns.ftn.weplayserver.model.Game;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;
import rs.ac.uns.ftn.weplayserver.model.Notification;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.EventRepository;
import rs.ac.uns.ftn.weplayserver.repository.GameRepository;
import rs.ac.uns.ftn.weplayserver.repository.GamingRoomRepository;
import rs.ac.uns.ftn.weplayserver.repository.UserRepository;
import rs.ac.uns.ftn.weplayserver.service.EventService;

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
	
	@Autowired
	EventService eventService;
	
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
	public ResponseEntity<?> createEvent(@PathVariable Long id, @RequestBody EventDTO e) throws ParseException{
		System.out.println(e.toString());
		GamingRoom gr = grRepo.getOne(id);
		Event ev = new Event();
		//Game game = gameRepo.findByName(e.getGame());
	
		List<Notification> notif = new ArrayList<Notification>();
		List<User> parti = new ArrayList<User>();
		
		User u = userRepo.findByEmail(e.getCreator_email()); 
		
		ev.setCreator(u);
		ev.setDescription(e.getDescription());
		//ev.setEventNotifications(notif);
		ev.setGamingRoom(gr);
		DateFormat d = new SimpleDateFormat("dd.MM.yyyy");
		Date date = d.parse(e.getJoinDeadline());
		ev.setJoinDeadline(date);
		ev.setNumbOfPlayers(e.getNumbOfPlayers());
		ev.setName(e.getName());
		ev.setParticipants(parti);
		ev.setGame(e.getGame());
		ev.setLast_update_date(new Date());
		eventRepo.save(ev);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @PostMapping("join/{id}/{user}")
    public ResponseEntity<?> joinEvent(@PathVariable Long id, @PathVariable String user) throws Exception{

        Event event2join = eventRepo.getOne(id);
        User user2join = userRepo.findByEmail(user);
        if(event2join.getParticipants().size() == event2join.getNumbOfPlayers()) {
            return new ResponseEntity<StringDTO>(new StringDTO("Max players reached.", 400), HttpStatus.OK);
        }
        if(event2join.getJoinDeadline().before(new Date())) {
            return new ResponseEntity<StringDTO>(new StringDTO("Deadline date expired.",400), HttpStatus.OK);
        }
        if(!user2join.getParticipantEvents().contains(event2join)) {
            user2join.getParticipantEvents().add(event2join);
            userRepo.save(user2join);
            return new ResponseEntity<StringDTO>(new StringDTO("Join Success", 200), HttpStatus.OK);
        } else {
            return new ResponseEntity<StringDTO>(new StringDTO("Already Joined", 200), HttpStatus.OK);
        }

    }

    @PostMapping("subscribe/{id}/{user}")
    public ResponseEntity<?> subscribe2Event(@PathVariable Long id, @PathVariable String user) {

        Event event2join = eventRepo.getOne(id);
        User user2join = userRepo.findByEmail(user);
        if(!user2join.getSubscriberEvents().contains(event2join)) {
            user2join.getSubscriberEvents().add(event2join);
            userRepo.save(user2join);
            return new ResponseEntity<StringDTO>(new StringDTO("Subscription Success", 200), HttpStatus.OK);
        } else {
            return new ResponseEntity<StringDTO>(new StringDTO("Already Subscribed", 200), HttpStatus.OK);
        }



    }

    @GetMapping("getMyEvents/{email}")
    public ResponseEntity<?> getMyEvents(@PathVariable String email) {
        User u = userRepo.findByEmail(email);

        List<EventDTO> retVal = new ArrayList<EventDTO>();
        List<Event> createdEvents = eventRepo.getCreatedEvents(u.getId());
        List<Event> joinedEvents = u.getParticipantEvents();
        List<Event> events = new ArrayList<Event>();

        if(createdEvents != null) {
            events.addAll(createdEvents);
        }

        if(joinedEvents != null) {
            events.addAll(joinedEvents);
        }

        for(Event e : events) {
            retVal.add(new EventDTO(e));
        }
        return new ResponseEntity<List<EventDTO>>(retVal, HttpStatus.OK);
    }
    
    @GetMapping("created/{email}")
    public ResponseEntity<?>getCreatedEvents(@PathVariable String email){
    	List<EventDTO> retVal= eventService.getCreated(email);
    	return new ResponseEntity<List<EventDTO>>(retVal,HttpStatus.OK);
    }
    
    @GetMapping("joined/{email}")
    public ResponseEntity<?>getJoinedEvents(@PathVariable String email){
    	List<EventDTO> retVal= eventService.getJoined(email);
    	return new ResponseEntity<List<EventDTO>>(retVal,HttpStatus.OK);
    }
    
    @GetMapping("subscribed/{email}")
    public ResponseEntity<?>getSubscribed(@PathVariable String email){
    	List<EventDTO> retVal= eventService.getSubscribed(email);
    	return new ResponseEntity<List<EventDTO>>(retVal,HttpStatus.OK);
    }
}
