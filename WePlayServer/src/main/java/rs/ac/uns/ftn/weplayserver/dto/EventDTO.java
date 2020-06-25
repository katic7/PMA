package rs.ac.uns.ftn.weplayserver.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.weplayserver.model.Event;
import rs.ac.uns.ftn.weplayserver.model.User;

@Data
@NoArgsConstructor
public class EventDTO {

	private Long id;
	private String name;
	private String game;
    private String joinDeadline;
    private short numbOfPlayers;
    private List<UserDTO> participants = new ArrayList<>();
    private List<UserDTO> subscribers = new ArrayList<>();
    private String description;
    private String creator_email;
    private String gameRoom;
    private double lat;
    private double lon;
    
    public EventDTO(Event e) {
    	this.id = e.getId();
    	this.name = e.getName();
    	this.game = e.getGame().getName();
    	this.joinDeadline = e.getJoinDeadline().toLocaleString();
    	this.numbOfPlayers = e.getNumbOfPlayers();
    	for(User u : e.getParticipants()) {
    		participants.add(new UserDTO(u));
    	}
    	for(User u : e.getSubscribers()) {
    		subscribers.add(new UserDTO(u));
    	}
    	
    	this.description = e.getDescription();
    	this.creator_email = e.getCreator().getEmail();
    	this.gameRoom = e.getGamingRoom().getName();
    	this.lat = e.getGamingRoom().getAddress().getLat();
    	this.lon = e.getGamingRoom().getAddress().getLon();
    }
}
