package rs.ac.uns.ftn.weplayserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.weplayserver.model.GamingRoom;

@Repository
public interface GamingRoomRepository extends JpaRepository<GamingRoom, Long>{

	@Query(value="select * from gaming_room where last_update_date > ?1", nativeQuery = true)
	List<GamingRoom> getAllForSync(long date);
}
