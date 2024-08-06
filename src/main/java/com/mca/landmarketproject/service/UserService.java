package com.mca.landmarketproject.service;

import java.util.List;

import com.mca.landmarketproject.dto.UserDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.User;


public interface UserService {

	List<UserDto> getAllUser() throws LandMarketException;

	String addNewUser(User user) throws LandMarketException;

	String updateUser(Integer userId, UserDto dto) throws LandMarketException;

	String deleteUser(Integer userId) throws LandMarketException;


}