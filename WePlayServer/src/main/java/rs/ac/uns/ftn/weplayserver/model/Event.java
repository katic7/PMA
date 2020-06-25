package rs.ac.uns.ftn.weplayserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Event{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date dateTime;

    private Date joinDeadline;

    private short numbOfPlayers;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    @ManyToMany(mappedBy = "participantEvents")
    private List<User> participants = new ArrayList<>();
    
    @ManyToMany(mappedBy = "subscriberEvents")
    private List<User> subscribers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    protected GamingRoom gamingRoom;
    
    @OneToMany
    private List<Notification> notifications;

}