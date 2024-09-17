//package com.mca.landmarketproject.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mca.landmarketproject.dto.UserDto;
//import com.mca.landmarketproject.exception.LandMarketException;
//import com.mca.landmarketproject.model.User;
//import com.mca.landmarketproject.response.LandMarketRespones;
//import com.mca.landmarketproject.service.UserService;
//
//@RestController
//@RequestMapping(path = "/user")
//public class UserController {
//
//	@Autowired
//	private UserService services;
//
//	@GetMapping
//	public LandMarketRespones getAllUsers() {
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		String message = "";
//		try {
//			List<UserDto> listOfDto = services.getAllUser();
//			status = HttpStatus.OK;
//			return new LandMarketRespones(listOfDto, status);
//		} catch (LandMarketException exception) {
//			message = "Failed to retrieve Users data !!" + exception.getLocalizedMessage();
//		} catch (Exception exception) {
//			message = "Internal Server Error !!" + exception.getLocalizedMessage();
//		}
//		return new LandMarketRespones(message, status);
//	}
//
//	@GetMapping("/{id}")
//	public LandMarketRespones getUserById(@PathVariable(name = "id") Integer id) {
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		String message = "";
//		try {
//			User user = services.getUserById(id);
//			status = HttpStatus.OK;
//			return new LandMarketRespones(user, status);
//		} catch (LandMarketException exception) {
//			message = "Failed to retrieve Users data !!" + exception.getLocalizedMessage();
//		} catch (Exception exception) {
//			message = "Internal Server Error !!" + exception.getLocalizedMessage();
//		}
//		return new LandMarketRespones(message, status);
//	}
//
//	@PostMapping
//	public LandMarketRespones addUser(@RequestBody User user) {
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		String message = "";
//		try {
//			message = services.addNewUser(user);
//			status = HttpStatus.OK;
//
//		} catch (LandMarketException exception) {
//			message = "Failed to add User !!" + exception.getLocalizedMessage();
//		} catch (Exception exception) {
//			message = "Internal Server Error !!" + exception.getLocalizedMessage();
//		}
//		return new LandMarketRespones(message, status);
//	}
//
//	@PutMapping("/user-id/{userId}")
//	public LandMarketRespones updateUser(@PathVariable(name = "userId") Integer userId, @RequestBody UserDto userDto) {
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		String message = "";
//		try {
//			message = services.updateUser(userId, userDto);
//			status = HttpStatus.OK;
//
//		} catch (LandMarketException exception) {
//			message = "Failed to Update User !!" + exception.getLocalizedMessage();
//		} catch (Exception exception) {
//			message = "Internal Server Error !!" + exception.getLocalizedMessage();
//		}
//		return new LandMarketRespones(message, status);
//	}
//
//	@DeleteMapping("/id/{userId}")
//	public LandMarketRespones deleteUser(@PathVariable(name = "userId") Integer userId) {
//
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		String message = "";
//		try {
//			message = services.deleteUser(userId);
//			status = HttpStatus.OK;
//		} catch (LandMarketException exception) {
//			message = "Failed to add User !!" + exception.getLocalizedMessage();
//		} catch (Exception exception) {
//			message = "Internal Server Error !!" + exception.getLocalizedMessage();
//		}
//		return new LandMarketRespones(message, status);
//
//	}
//}
package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.GoogleUserDto;
import com.mca.landmarketproject.dto.UserDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService services;

	@GetMapping
	public LandMarketRespones getAllUsers() {
		try {
			List<UserDto> listOfDto = services.getAllUser();
			return new LandMarketRespones(listOfDto, HttpStatus.OK);
		} catch (LandMarketException exception) {
			return new LandMarketRespones("Failed to retrieve Users data: " + exception.getLocalizedMessage(),
					HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return new LandMarketRespones("Internal Server Error: " + exception.getLocalizedMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{email}")
	public LandMarketRespones getUserById(@PathVariable(name = "email") String email) {
		try {
			User user = services.getUserById(email);
			return new LandMarketRespones(user, HttpStatus.OK);
		} catch (LandMarketException exception) {
			return new LandMarketRespones("Failed to retrieve User data: " + exception.getLocalizedMessage(),
					HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return new LandMarketRespones("Internal Server Error: " + exception.getLocalizedMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/signUp")
	@ResponseStatus(HttpStatus.CREATED)
	public LandMarketRespones addUser(@RequestBody UserDto userDto) {
		try {
			String message = services.addNewUser(userDto);
			return new LandMarketRespones(message, HttpStatus.CREATED);
		} catch (LandMarketException exception) {
			return new LandMarketRespones("Failed to add User: " + exception.getLocalizedMessage(),
					HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return new LandMarketRespones("Internal Server Error: " + exception.getLocalizedMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/signIn")
	public ResponseEntity<Object> signIn(HttpServletResponse response,@RequestBody UserDto userDto) {
		String statusMessage = "";
		String jwt = services.signIn(userDto);
		
		if (jwt == null) {
			statusMessage = "Error while Signing In.";
			return ResponseEntity.badRequest().body("Invalid Credentials");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("access_token", jwt);
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization, access_token");
		statusMessage = "User Signed In Successfully.";
		return ResponseEntity.ok().headers(headers).body(statusMessage);
	}

	@PutMapping("/user-id/{userId}")
	public LandMarketRespones updateUser(@PathVariable(name = "userId") Integer userId, @RequestBody UserDto userDto) {
		try {
			String message = services.updateUser(userId, userDto);
			return new LandMarketRespones(message, HttpStatus.OK);
		} catch (LandMarketException exception) {
			return new LandMarketRespones("Failed to update User: " + exception.getLocalizedMessage(),
					HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return new LandMarketRespones("Internal Server Error: " + exception.getLocalizedMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/id/{userId}")
	public LandMarketRespones deleteUser(@PathVariable(name = "userId") Integer userId) {
		try {
			String message = services.deleteUser(userId);
			return new LandMarketRespones(message, HttpStatus.OK);
		} catch (LandMarketException exception) {
			return new LandMarketRespones("Failed to delete User: " + exception.getLocalizedMessage(),
					HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			return new LandMarketRespones("Internal Server Error: " + exception.getLocalizedMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/signInWithGoogle")
	public ResponseEntity<Object> signInWithGoogle(HttpServletResponse response, @RequestBody GoogleUserDto dto) {

		try {
			
			String statusMessage = "";
			String jwt = services.signInWithGoogle(dto.getAccessToken());
			
			if (jwt == null) {
				statusMessage = "Error while Signing In.";
				return ResponseEntity.badRequest().body("Invalid Credentials");
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("access_token", jwt);
			headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization, access_token");
			statusMessage = "User Signed In Successfully.";
			return ResponseEntity.ok().headers(headers).body(statusMessage);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Internal server error " + e.getLocalizedMessage());
		}
	}
}
