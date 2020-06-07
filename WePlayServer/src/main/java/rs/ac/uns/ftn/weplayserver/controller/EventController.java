package rs.ac.uns.ftn.weplayserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.weplayserver.dto.EventDTO;
import rs.ac.uns.ftn.weplayserver.model.Event;
import rs.ac.uns.ftn.weplayserver.repository.EventRepository;

@RestController
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	EventRepository eventRepo;
	
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
}
