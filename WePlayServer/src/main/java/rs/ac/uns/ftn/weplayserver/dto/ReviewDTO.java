package rs.ac.uns.ftn.weplayserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.weplayserver.model.Review;

@Data
@NoArgsConstructor
public class ReviewDTO {
	
	private String comment;
    private float rating;
    private String user_email;
    private String user;
    
    public ReviewDTO(Review r) {
    	this.comment = r.getComment();
    	this.rating = r.getRating();
    	this.user_email = r.getUser().getEmail();
    	this.user = r.getUser().getFirstName() + " " + r.getUser().getLastName();
    }
}
