package rs.ac.uns.ftn.weplayserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.weplayserver.model.GamingRoom;

@Repository
public interface GamingRoomRepository extends JpaRepository<GamingRoom, Long>{

}
