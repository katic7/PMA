package rs.ac.uns.ftn.weplayserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.weplayserver.model.Review;
import rs.ac.uns.ftn.weplayserver.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepo;
	
	
	@Override
	public boolean canReview(Long userId, Long gameRoom) {
		Review r = reviewRepo.getUserReview(gameRoom, userId);
		if(r == null) {
			return true;
		}else {
			return false;
		}
	}

}
