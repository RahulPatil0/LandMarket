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

import com.mca.landmarketproject.dto.UserRequestDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.UserRequest;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.UserRequestService;

@RestController
@RequestMapping(path = "/user-request")
public class UserRequestController {

	@Autowired
	private UserRequestService services;

	@GetMapping
	public LandMarketRespones getAllUserRequests() {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<UserRequestDto> listOfDto = services.getAllUserRequests();
			status = HttpStatus.OK;
			return new LandMarketRespones(listOfDto, status);
		} catch (LandMarketException exception) {
			message = "Failed to retrieve User Requests data !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}

	@GetMapping("/{id}")
	public LandMarketRespones getUserRequestById(@PathVariable(name = "id") Integer id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			UserRequest userRequest = services.getUserRequestById(id);
			status = HttpStatus.OK;
			return new LandMarketRespones(userRequest, status);
		} catch (LandMarketException exception) {
			message = "Failed to retrieve User Request data !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}

	@PostMapping("/buyer-id/{buyerId}/owner-id/{ownerId}")
	public LandMarketRespones addNewUserRequest(@PathVariable(name = "buyerId") Integer buyerId,
			@PathVariable(name = "ownerId") Integer ownerId, @RequestBody UserRequestDto userRequest) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = services.addNewUserRequest(userRequest, buyerId, ownerId);
			status = HttpStatus.OK;

		} catch (LandMarketException exception) {
			message = "Failed to add User Request !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}

}
