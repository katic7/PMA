package rs.ac.uns.ftn.weplayserver.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.weplayserver.dto.ReviewDTO;
import rs.ac.uns.ftn.weplayserver.model.GamingRoom;
import rs.ac.uns.ftn.weplayserver.model.Review;
import rs.ac.uns.ftn.weplayserver.model.User;
import rs.ac.uns.ftn.weplayserver.repository.GamingRoomRepository;
import rs.ac.uns.ftn.weplayserver.repository.ReviewRepository;
import rs.ac.uns.ftn.weplayserver.repository.UserRepository;
import rs.ac.uns.ftn.weplayserver.service.ReviewService;
import rs.ac.uns.ftn.weplayserver.service.ReviewServiceImpl;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReviewServiceImpl reviewService;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private GamingRoomRepository grRepo;
	
	@GetMapping("getAll/{id}")
	public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long id){
		List<ReviewDTO> retVal = new ArrayList<ReviewDTO>();
		List<Review> reviews = reviewRepo.getAllReviews(id);
		for(Review r : reviews) {
			ReviewDTO rdto = new ReviewDTO(r);
			retVal.add(rdto);
		}
		return new ResponseEntity<List<ReviewDTO>>(retVal, HttpStatus.ACCEPTED.OK);

	}
	
	@PostMapping("create/{id}")
	public ResponseEntity<?> createReview(@PathVariable Long id, @RequestBody ReviewDTO r){
		GamingRoom gr = grRepo.getOne(id);
		Review review = new Review();
		float total_rating = 0;
		User u = userRepo.findByEmail(r.getUser_email());
		
		if(!reviewService.canReview(u.getId(), gr.getId())) {
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		review.setComment(r.getComment());
		review.setRating(r.getRating());
		review.setGamingRoom(gr);
		review.setUser(u);
		reviewRepo.save(review);
		
		int numberOfReviews = gr.getReviews().size();
		for(Review rev : gr.getReviews()) {
			total_rating = total_rating + rev.getRating();
		}
		gr.setRating(total_rating / numberOfReviews);
		gr.setLast_update_date(new Date().getTime());
		grRepo.save(gr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
