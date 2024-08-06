package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mca.landmarketproject.dto.UserDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	private UserService services;
	
	
	@GetMapping
	public LandMarketRespones getAllUsers(){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<UserDto> listOfDto = services.getAllUser();
			status = HttpStatus.OK;
			return new LandMarketRespones(listOfDto, status);
		}
		catch(LandMarketException exception) {
			message = "Failed to retrieve Users data !!" + exception.getLocalizedMessage();
		}
		catch(Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);			
	}

	@PostMapping
	public LandMarketRespones addUser(@RequestBody User user) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = services.addNewUser(user);	
			status = HttpStatus.OK;
			
		}
		catch(LandMarketException exception) {
			message = "Failed to add User !!" + exception.getLocalizedMessage();
		}
		catch(Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);	
	}
	
	@PutMapping("/user-id/{userId}")
	public LandMarketRespones updateUser(@PathVariable(name = "userId")Integer userId, @RequestBody UserDto userDto) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message= services.updateUser(userId, userDto);		
			status = HttpStatus.OK;
			
		}catch(LandMarketException exception) {
			message = "Failed to Update User !!" + exception.getLocalizedMessage();
		}
		catch(Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);	
	}
	@DeleteMapping("/id/{userId}")
	public LandMarketRespones deleteUser(@PathVariable(name = "userId")Integer userId){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = services.deleteUser(userId);		
			status = HttpStatus.OK;
		}catch(LandMarketException exception) {
			message = "Failed to add User !!" + exception.getLocalizedMessage();
		}
		catch(Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
		
	}
}