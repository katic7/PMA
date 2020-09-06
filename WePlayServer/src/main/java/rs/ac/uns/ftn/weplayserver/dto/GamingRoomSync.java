package rs.ac.uns.ftn.weplayserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;

@Data
@NoArgsConstructor
public class GamingRoomSync {

	private Long id;
	private String name;
	private float rating;
	private double lat;
	private double lon;
	
	public GamingRoomSync(GamingRoom gr) {
		this.id = gr.getId();
		this.name = gr.getName();
		this.lat = gr.getAddress().getLat();
		this.lon = gr.getAddress().getLon();
		this.rating = gr.getRating();
	}
}
