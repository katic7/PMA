package rs.ac.uns.ftn.weplayserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Event {

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

    @ManyToOne(fetch = FetchType.EAGER)
    protected GamingRoom gamingRoom;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "event_notifications",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<Notification> eventNotifications = new ArrayList<>();


}