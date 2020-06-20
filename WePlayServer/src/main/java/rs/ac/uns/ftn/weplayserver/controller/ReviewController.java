package rs.ac.uns.ftn.weplayserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.weplayserver.dto.ReviewDTO;
import rs.ac.uns.ftn.weplayserver.model.Review;
import rs.ac.uns.ftn.weplayserver.repository.ReviewRepository;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepo;
	
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
}
