package rs.ac.uns.ftn.weplayserver.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class GamingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;
    
    @Column(nullable = false)
    private int price_per_hour;

    @Column(nullable = false)
    private String working_hours;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private float rating;


    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "gamingRoom")
    private Address address;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "room_game",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> gamesInGamingRoom = new ArrayList<>();

    @OneToMany(mappedBy = "gamingRoom")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "gamingRoom")
    private List<Event> events = new ArrayList<>();


}
