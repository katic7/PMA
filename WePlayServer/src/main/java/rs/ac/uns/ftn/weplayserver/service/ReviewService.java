package rs.ac.uns.ftn.weplayserver.service;

public interface ReviewService {

	boolean canReview(Long userId, Long gameRoom);
}
