package com.mca.landmarketproject.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.ContactOwnerRepository;
import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.ContactOwnerDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.ContactOwner;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.ContactOwnerService;
import com.mca.landmarketproject.util.ContactOwnerUtil;

@Service
public class ContactOwnerServiceImplementation implements ContactOwnerService {

    @Autowired
    private ContactOwnerRepository contactOwnerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<ContactOwnerDto> getAllContactOwners() throws LandMarketException {
        try {
            return contactOwnerRepository.findAll().stream()
                    .map(ContactOwnerUtil::convertContactOwnerEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new LandMarketException("Failed to retrieve contact owners: " + e.getMessage());
        }
    }

    @Override
    public String addNewContactOwner(ContactOwnerDto contactOwnerDto) throws LandMarketException {
        try {
            ContactOwner contactOwner = ContactOwnerUtil.convertContactOwnerDtoToEntity(contactOwnerDto);
            contactOwnerRepository.save(contactOwner);
            Optional<User> user  = userRepository.findById(contactOwnerDto.getOwnerId());
            if(user.isEmpty()) {
            	throw new LandMarketException("Owner not found");
            }
            
            sendSimpleMessage(user.get().getEmail(), "Landmarket Product Notification", contactOwnerDto.getMessage());
            return "Contact Owner added successfully";
        } catch (Exception e) {
            throw new LandMarketException("Failed to add contact owner: " + e.getMessage());
        }
    }
    
    public void sendSimpleMessage(String to, String subject, String text) {
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setTo(to);
    	message.setSubject(subject);
    	message.setText(text);
    	mailSender.send(message);
    }
    
}
