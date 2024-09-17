package com.mca.landmarketproject.service;

import java.util.List;

import com.mca.landmarketproject.dto.UserRequestDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.UserRequest;


public interface UserRequestService {

	List<UserRequestDto> getAllUserRequests() throws LandMarketException;

	String addNewUserRequest(UserRequestDto userRequest,Integer buyerId,Integer ownerId) throws LandMarketException;

	UserRequest getUserRequestById(Integer id) throws LandMarketException;
}


