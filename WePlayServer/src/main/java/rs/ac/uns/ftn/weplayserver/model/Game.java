package rs.ac.uns.ftn.weplayserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String type;

    @ManyToMany(mappedBy = "favouriteGames")
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "gamesInGamingRoom")
    private List<GamingRoom> gamingRoomsWithThisGame = new ArrayList<>();
}
