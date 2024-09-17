package com.mca.landmarketproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.ContactUsDto;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.ContactService;
import com.mca.landmarketproject.service.GoogleCaptchaVerificationService;


@RestController
@RequestMapping("/contact")
public class ContactUsController {
	
	@Autowired
	private GoogleCaptchaVerificationService googleCaptchaVerificationService;
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping
	public LandMarketRespones addContactForm(@RequestBody ContactUsDto contactUsDto) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			if(googleCaptchaVerificationService.isvalidCaptcha(contactUsDto.getToken())) {
				contactService.saveContactDetails(contactUsDto);
			}
			status = HttpStatus.OK;
		} catch (Exception exception) {
			message = "Failed to send contact data !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}
}