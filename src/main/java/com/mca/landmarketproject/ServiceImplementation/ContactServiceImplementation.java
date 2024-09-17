package com.mca.landmarketproject.ServiceImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.ContactRepository;
import com.mca.landmarketproject.dto.ContactUsDto;
import com.mca.landmarketproject.model.ContactUs;
import com.mca.landmarketproject.service.ContactService;


@Service
public class ContactServiceImplementation implements ContactService {

	Logger logger = LoggerFactory.getLogger(ContactServiceImplementation.class);

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContactDetails(ContactUsDto contactDto) {
		try {
			ContactUs contactUs = new ContactUs();
			BeanUtils.copyProperties(contactDto, contactUs);

			contactRepository.save(contactUs);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
	}

}