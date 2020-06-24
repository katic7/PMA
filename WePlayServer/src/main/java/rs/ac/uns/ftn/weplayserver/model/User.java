package rs.ac.uns.ftn.weplayserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private float gamingSkill;

    private Date lastPasswordResetDate;

    private Boolean enabled;


    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> favouriteGames = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Event> creatorEvents = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "participant_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> participantEvents = new ArrayList<>();
    
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "subscriber_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> subscriberEvents = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}