package com.mca.landmarketproject.service;
import java.util.List;

import com.mca.landmarketproject.dto.ReviewDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Review;

public interface ReviewService {
	
List<ReviewDto> getAllReview() throws LandMarketException;

String addNewReview(Review review,Integer userId,Integer propertyId) throws LandMarketException;
}
