package com.mca.landmarketproject.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.PropertyRepository;
import com.mca.landmarketproject.dao.ReviewRepository;
import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.ReviewDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Property;
import com.mca.landmarketproject.model.Review;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.ReviewService;
import com.mca.landmarketproject.util.ReviewUtil;

@Service
public class ReviewServiceImplementation implements ReviewService {
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	
	public List<ReviewDto> getAllReview() throws LandMarketException {
		try {
			List<ReviewDto> listOfDto = repository.findAll().stream()
					.map(ReviewUtil::convertReviewEntityToDto).collect(Collectors.toList());
			return listOfDto;
		} catch (Exception exception) {
			throw new LandMarketException(exception.getLocalizedMessage());
		}
	}

	public String addNewReview(Review review,Integer userId,Integer propertyId) throws LandMarketException {
		//ReviewDto review = ReviewUtil.convertReviewEntityToDto(review);
		try {
			 User user = userRepository.findById(userId).orElseThrow(() -> new LandMarketException("User not found"));
			 Property property=propertyRepository.findById(propertyId).orElseThrow(() -> new LandMarketException("Property not found"));
	        review.setUser(user);
	        review.setProperties(property);

			repository.save(review);
			return "Review Posted By You successfully";
		} catch (Exception exception) {
			throw new LandMarketException("Transaction has been done already.." + exception.getLocalizedMessage());
			
		}

	}
}
