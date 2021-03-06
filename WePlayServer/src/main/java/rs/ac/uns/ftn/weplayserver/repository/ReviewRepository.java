package rs.ac.uns.ftn.weplayserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.ac.uns.ftn.weplayserver.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query(value = "select * from review where gaming_room = ?1", nativeQuery = true)
	List<Review> getAllReviews(Long id);
	
	@Query(value = "select * from review where gaming_room = ?1 and user_id = ?2", nativeQuery = true)
	Review getUserReview(Long grId, Long userId);
}
