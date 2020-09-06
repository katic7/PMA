package rs.ac.uns.ftn.weplayserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.weplayserver.dto.EventDTO;
import rs.ac.uns.ftn.weplayserver.model.Event;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.EventRepository;
import rs.ac.uns.ftn.weplayserver.repository.UserRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	EventRepository eventRepo;
	
	@Override
	public List<EventDTO> getCreated(String email) {
		List<EventDTO> retVal = new ArrayList<EventDTO>();
		User u = userRepo.findByEmail(email);
		List<Event> created = eventRepo.getCreatedEvents(u.getId());
		for(Event e : created) {
			EventDTO edto = new EventDTO(e);
			retVal.add(edto);
		}
		return retVal;
	}

	@Override
	public List<EventDTO> getSubscribed(String email) {
		List<EventDTO> retVal = new ArrayList<EventDTO>();
		User u = userRepo.findByEmail(email);
		List<Event> subs = u.getSubscriberEvents();
		for(Event e : subs) {
			EventDTO edto = new EventDTO(e);
			retVal.add(edto);
		}
		return retVal;
	}

	@Override
	public List<EventDTO> getJoined(String email) {
		List<EventDTO> retVal = new ArrayList<EventDTO>();
		User u = userRepo.findByEmail(email);
		List<Event> joined = u.getParticipantEvents();
		for(Event e : joined) {
			EventDTO edto = new EventDTO(e);
			retVal.add(edto);
		}
		return retVal;
	}

}
