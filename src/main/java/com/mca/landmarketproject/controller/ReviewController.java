package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.ReviewDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Review;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.ReviewService;

@RestController
@RequestMapping(path ="/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping
	public LandMarketRespones getAllReview() {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<ReviewDto> listOfDto = reviewService.getAllReview();
			status = HttpStatus.OK;
			return new LandMarketRespones(listOfDto, status);
		} catch (LandMarketException exception) {
			message = "Failed to retrieve reviews data !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}

	@PostMapping("user-id/{userId}/property-id/{propertyId}")
	public LandMarketRespones addNewReview(@PathVariable(name = "userId") Integer userId,
			@PathVariable(name = "propertyId") Integer propertyId, @RequestBody Review review) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = reviewService.addNewReview(review,userId,propertyId);
			status = HttpStatus.OK;

		} catch (LandMarketException exception) {
			message = "Failed to add Review !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}

}

