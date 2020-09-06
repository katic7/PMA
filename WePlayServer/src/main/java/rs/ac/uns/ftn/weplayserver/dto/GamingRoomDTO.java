package rs.ac.uns.ftn.weplayserver.dto;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;

@Data
@NoArgsConstructor
public class GamingRoomDTO {
	
	private Long id;
	private String name;
	private int capacity;
    private int price_per_hour;
    private String working_hours;
    private String phoneNumber;
    private float rating;
    private boolean favourite;
	private AddressDTO address;
	
	public GamingRoomDTO(GamingRoom gr) {
		this.id = gr.getId();
		this.capacity = gr.getCapacity();
		this.name = gr.getName();
		this.price_per_hour = gr.getPrice_per_hour();
		this.working_hours = gr.getWorking_hours();
		this.phoneNumber = gr.getPhoneNumber();
		this.rating = gr.getRating();
		this.address = new AddressDTO(gr.getAddress());
	}
	
}
