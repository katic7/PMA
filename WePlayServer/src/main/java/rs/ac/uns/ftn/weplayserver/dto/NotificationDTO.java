package rs.ac.uns.ftn.weplayserver.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationDTO {
	
	Long id;
	String text;
	String score;
	Date date;
	UserDTO user;
	EventDTO event;
	

}
