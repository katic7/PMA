package rs.ac.uns.ftn.weplayserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.weplayserver.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Query(value = "select * from event where gaming_room_id = ?1", nativeQuery = true)
	List<Event> getAllByGamingRoom(Long id);

}
