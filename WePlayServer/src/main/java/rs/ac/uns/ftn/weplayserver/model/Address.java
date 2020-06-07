package rs.ac.uns.ftn.weplayserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String city;

    private String country;

    private String postalCode;
    
    private double lat;
    
    private double lon;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gaming_room_id", nullable = false)
    private GamingRoom gamingRoom;

}
