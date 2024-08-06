package com.mca.landmarketproject.ServiceImplementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.UserDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.UserService;
import com.mca.landmarketproject.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	@Autowired
	private UserRepository userRepository;

	public List<UserDto> getAllUser() throws LandMarketException {
		try {
			List<UserDto> listOfDto = userRepository.findAll().stream().map(UserUtil::convertUserEntityToDto)
					.collect(Collectors.toList());
			return listOfDto;
		} catch (Exception exception) {
			throw new LandMarketException(exception.getLocalizedMessage());
		}
	}

	// Add User
	public String addNewUser(User user) throws LandMarketException {
		//User user = UserUtil.convertUserDtoToEntity(dto);
		try {
			userRepository.save(user);
			return "user Registered successfully";
		} catch (DataIntegrityViolationException exception) {
			logger.error(exception.getLocalizedMessage());
			throw new LandMarketException("User data has been registerd already.." + exception.getLocalizedMessage());
		}
	}

//Update
	@Transactional
	public String updateUser(Integer userId, UserDto dto) throws LandMarketException {
		User user = UserUtil.convertUserDtoToEntity(dto);
		User existingUser = userRepository.findById(userId).get();

		if (existingUser == null) {
			throw new LandMarketException("User not Found");
		}
		// Update Email
		if (user.getEmail() != null && user.getEmail().length() > 0
				&& !Objects.equals(user.getEmail(), existingUser.getEmail())) {
			existingUser.setEmail(user.getEmail());
		}

		// Update PhoneNumber
		if (user.getPhoneNumber() != null && user.getPhoneNumber().length() > 0
				&& !Objects.equals(user.getPhoneNumber(), existingUser.getPhoneNumber())) {
			existingUser.setPhoneNumber(user.getPhoneNumber());
		}
		// Update FirstName
		if (user.getFirstName() != null && user.getFirstName().length() > 0
				&& !Objects.equals(user.getFirstName(), existingUser.getFirstName())) {
			existingUser.setPhoneNumber(user.getFirstName());
		}
		// Update LastName
		if (user.getLastName() != null && user.getLastName().length() > 0
				&& !Objects.equals(user.getLastName(), existingUser.getLastName())) {
			existingUser.setLastName(user.getLastName());
		}
		return "User data updated succesfully";
	}

	// Delete user
	public String deleteUser(Integer userId) throws LandMarketException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.deleteById(userId);
			String name = user.get().getFirstName();
			return "User " + name + " is deleted successfully.";
		} else {
			throw new LandMarketException("User doesn't exist.");
		}
	}

}
