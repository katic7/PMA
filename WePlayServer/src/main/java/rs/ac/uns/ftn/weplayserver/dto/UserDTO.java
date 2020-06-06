package rs.ac.uns.ftn.weplayserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}