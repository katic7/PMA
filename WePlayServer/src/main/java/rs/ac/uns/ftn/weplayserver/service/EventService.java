package rs.ac.uns.ftn.weplayserver.service;

import java.util.List;

import rs.ac.uns.ftn.weplayserver.dto.EventDTO;

public interface EventService {

	public List<EventDTO> getCreated(String email);
	public List<EventDTO> getSubscribed(String email);
	public List<EventDTO> getJoined(String email);
}
