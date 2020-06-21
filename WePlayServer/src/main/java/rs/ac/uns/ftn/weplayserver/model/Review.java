package rs.ac.uns.ftn.weplayserver.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private float rating;

    @ManyToOne
    protected User user;

    @ManyToOne
    @JoinColumn(name = "gamingRoom")
    protected GamingRoom gamingRoom;
}