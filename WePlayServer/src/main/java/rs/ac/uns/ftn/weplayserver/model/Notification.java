package rs.ac.uns.ftn.weplayserver.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String score;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    protected User user;

    @ManyToMany(mappedBy = "eventNotifications")
    private List<Event> participants = new ArrayList<>();
}