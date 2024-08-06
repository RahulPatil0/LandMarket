
package com.mca.landmarketproject.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.PropertyRepository;
import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Property;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.PropertyService;
import com.mca.landmarketproject.util.PropertyUtil;

@Service
public class PropertyServiceImplementation implements PropertyService {

    @Autowired
    private PropertyRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PropertyDto> getAllProperty() throws LandMarketException {
        try {
            return repository.findAll().stream()
                    .map(PropertyUtil::convertPropertyEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new LandMarketException(exception.getLocalizedMessage());
        }
    }


    @Override
    public String addNewProperty(Property property, Integer userId) throws LandMarketException {
        try {
            // Fetch the user entity using the userId
            User user = userRepository.findById(userId).orElseThrow(() -> new LandMarketException("User not found"));

            // Set the user to the property
            property.setUser(user);

            // Save the property with the user set
            repository.save(property);

            return "Property added successfully";
        } catch (Exception exception) {
            throw new LandMarketException("Failed to add property: " + exception.getLocalizedMessage());
        }
    }
    
    public String updateProperty(Integer propertyId, PropertyDto propertyDto) throws LandMarketException {
        try {
        	// Fetch the existing property
        	Property existingProperty = repository.findById(propertyId)
        			.orElseThrow(() -> new LandMarketException("Property not found"));
        	
        	// Update the existing property with values from propertyDto
        	
        	existingProperty.setPropertyAddress(propertyDto.getPropertyAddress());
        	existingProperty.setPropertyZipCode(propertyDto.getPropertyZipCode());
        	existingProperty.setPropertyPrice(propertyDto. getPropertyPrice());
        	existingProperty.setPropertyStatus(propertyDto.getPropertyStatus());
        	existingProperty.setPropertyCity(propertyDto.getPropertyCity());
        	existingProperty.setPropertySize(propertyDto.getPropertySize());
        	existingProperty.setPropertyState(propertyDto.getPropertyState());
        	
        	
        	// Set other properties as needed
        	// Update FirstName
        	
        	
        	// Save the updated property
        	repository.save(existingProperty);
        	
        	return "Property updated successfully";
        	
        } catch (Exception e) {
			throw new LandMarketException(e.getMessage()); 
		}
    }
}



