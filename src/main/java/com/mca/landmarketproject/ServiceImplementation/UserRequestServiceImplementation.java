package com.mca.landmarketproject.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dao.UserRequestRepository;
import com.mca.landmarketproject.dto.UserRequestDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.model.UserRequest;
import com.mca.landmarketproject.service.UserRequestService;
import com.mca.landmarketproject.util.UserRequestUtil;

@Service
public class UserRequestServiceImplementation implements UserRequestService {

	Logger logger = LoggerFactory.getLogger(UserRequestServiceImplementation.class);

	@Autowired
	private UserRequestRepository userRequestRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserRequestDto> getAllUserRequests() throws LandMarketException {
		try {
			return userRequestRepository.findAll().stream().map(UserRequestUtil::convertUserRequestEntityToDto)
					.collect(Collectors.toList());
		} catch (Exception exception) {
			throw new LandMarketException(exception.getLocalizedMessage());
		}
	}

	@Override
	public UserRequest getUserRequestById(Integer id) throws LandMarketException {
		try {
			Optional<UserRequest> userRequest = userRequestRepository.findById(id);
			if (userRequest.isPresent()) {
				return userRequest.get();
			} else {
				throw new LandMarketException("User request not found");
			}
		} catch (Exception exception) {
			throw new LandMarketException(exception.getLocalizedMessage());
		}
	}

	@Override
	public String addNewUserRequest(UserRequestDto userRequest,Integer buyerId,Integer ownerId) throws LandMarketException {
		try {
			UserRequest request=UserRequestUtil.convertUserRequestDtoToEntity(userRequest);
			Optional<User> buyerOptional = userRepository.findById(buyerId);
			Optional<User> ownerOptional = userRepository.findById(ownerId);
			 if (buyerOptional.isPresent() && ownerOptional.isPresent()) {
		            // Get the User object from the Optional
		            User buyer = buyerOptional.get();
		            // Set the buyer for the UserRequest
		           request.setBuyer(buyer);
		          
		           User owner = ownerOptional.get();
		           request.setOwner(owner);
		           userRequestRepository.save(request);
		        } 
			 else {
		            throw new LandMarketException("Buyer with ID " + buyerId + " not found.");
		        }
		
			return "User request registered successfully";
		} catch (DataIntegrityViolationException exception) {
			logger.error(exception.getLocalizedMessage());
			throw new LandMarketException(
					"User request data has been registered already: " + exception.getLocalizedMessage());
		
	}
}
}
