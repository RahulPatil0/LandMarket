package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.ReviewDto;
import com.mca.landmarketproject.model.Review;

public class ReviewUtil {
	
	public static ReviewDto convertReviewEntityToDto(Review review)
	{
	ReviewDto dto=new ReviewDto();
	BeanUtils.copyProperties(review, dto);
	dto.setUserId(review.getUser().getId());
	dto.setPropertyId(review.getProperties().getId());
	//dto.setReviewDate(review.getReviewDate());
	return dto;
	}
	public static Review converteviewsDtoToEntity(ReviewDto dto)
	{
	Review review=new Review();
	BeanUtils.copyProperties(dto,review);
	return review;
}
}
