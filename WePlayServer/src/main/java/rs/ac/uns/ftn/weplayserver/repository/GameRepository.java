package rs.ac.uns.ftn.weplayserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.weplayserver.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	Game findByName(String name);
}
