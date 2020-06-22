package rs.ac.uns.ftn.weplayserver.dto;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.weplayserver.model.Event;
import rs.ac.uns.ftn.weplayserver.model.User;

@Data
@NoArgsConstructor
public class EventDTO {

	private String name;
	private String game;
    private String joinDeadline;
    private short numbOfPlayers;
    private short participants;
    private String description;
    private String creator_email;
    private String gameRoom;
    private double lat;
    private double lon;
    
    public EventDTO(Event e) {
    	this.name = e.getName();
    	this.game = e.getGame().getName();
    	this.joinDeadline = e.getJoinDeadline().toLocaleString();
    	this.numbOfPlayers = e.getNumbOfPlayers();
    	this.participants = (short) e.getParticipants().size();
    	this.description = e.getDescription();
    	this.creator_email = e.getCreator().getEmail();
    	this.gameRoom = e.getGamingRoom().getName();
    	this.lat = e.getGamingRoom().getAddress().getLat();
    	this.lon = e.getGamingRoom().getAddress().getLon();
    }
}
