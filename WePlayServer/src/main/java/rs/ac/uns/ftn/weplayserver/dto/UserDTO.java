package rs.ac.uns.ftn.weplayserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.weplayserver.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private int expiresIn;
    private float gamingSkill;
    
    public UserDTO(User user) {
    	this.id = user.getId();
    	this.password = user.getPassword();
    	this.firstName = user.getFirstName();
    	this.lastName = user.getLastName();
    	this.email = user.getEmail();
    	this.gamingSkill = user.getGamingSkill();
    }

}