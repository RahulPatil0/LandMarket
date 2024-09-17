package com.mca.landmarketproject.service;

import java.util.List;
import java.util.Optional;
import com.mca.landmarketproject.dto.UserDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.User;

public interface UserService {
    List<UserDto> getAllUser() throws LandMarketException;
    String addNewUser(UserDto userDto) throws LandMarketException;
    String updateUser(Integer userId, UserDto dto) throws LandMarketException;
    String deleteUser(Integer userId) throws LandMarketException;
    User getUserById(String email) throws LandMarketException;
    String signIn(UserDto userDto);
    String signInWithGoogle(String code);
    
    // Add this method to retrieve a User by ID
    Optional<User> findById(Integer userId);
}
