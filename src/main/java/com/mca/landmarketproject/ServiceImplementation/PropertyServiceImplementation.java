
package com.mca.landmarketproject.ServiceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.PropertyImageRepository;
import com.mca.landmarketproject.dao.PropertyRepository;
import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.dto.PropertyImageDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Property;
import com.mca.landmarketproject.model.PropertyImage;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.PropertyService;
import com.mca.landmarketproject.util.PropertyImageUtil;
import com.mca.landmarketproject.util.PropertyUtil;

@Service
public class PropertyServiceImplementation implements PropertyService {

	Logger logger = LoggerFactory.getLogger(PropertyServiceImplementation.class);
    @Autowired
    private PropertyRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyImageRepository propertyImageRepository;
    
    @Override
    public List<PropertyDto> getAllProperty() throws LandMarketException {
        try {
            return repository.findAll().stream()
                    .map(PropertyUtil::convertPropertyEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new LandMarketException("Failed to fetch properties: " + exception.getLocalizedMessage());
        }
    }

    @Override
    public String addNewProperty(PropertyDto propertyDto, Integer userId) throws LandMarketException {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new LandMarketException("User not found with ID: " + userId));
            Property property=PropertyUtil.convertPropertyDtoToEntity(propertyDto);
            property.setUser(user);
            property.setDateAdded(LocalDateTime.now());
            repository.save(property);
            
            for (PropertyImageDto dto : propertyDto.getPropertyImages()) {
				PropertyImage propertyImage = PropertyImageUtil.convertPropertyImagesDtoToEntity(dto);
				propertyImage.setProperty(property);
	            propertyImageRepository.save(propertyImage);
			}
            return "Property added successfully";
        } catch (Exception exception) {
        	logger.error(exception.getLocalizedMessage());
            throw new LandMarketException("Failed to add property: " + exception.getLocalizedMessage());
        }
    }

    @Override
    public String updateProperty(Integer propertyId, PropertyDto propertyDto) throws LandMarketException {
        try {
            Property existingProperty = repository.findById(propertyId)
                    .orElseThrow(() -> new LandMarketException("Property not found with ID: " + propertyId));

            existingProperty.setPropertyAddress(propertyDto.getPropertyAddress());
            existingProperty.setPropertyZipCode(propertyDto.getPropertyZipCode());
            existingProperty.setPropertyPrice(propertyDto.getPropertyPrice());
            existingProperty.setPropertyStatus(propertyDto.getPropertyStatus());
            existingProperty.setPropertyCity(propertyDto.getPropertyCity());
            existingProperty.setPropertySize(propertyDto.getPropertySize());
            existingProperty.setPropertyState(propertyDto.getPropertyState());

            repository.save(existingProperty);
            return "Property updated successfully";
        } catch (Exception e) {
            throw new LandMarketException("Failed to update property: " + e.getMessage());
        }
    }

    @Override
    public PropertyDto getPropertyById(Integer propertyId) throws LandMarketException {
        try {
            Property property = repository.findById(propertyId)
                    .orElseThrow(() -> new LandMarketException("Property not found with ID: " + propertyId));

            return PropertyUtil.convertPropertyEntityToDto(property);
        } catch (Exception exception) {
            throw new LandMarketException("Failed to fetch property: " + exception.getLocalizedMessage());
        }
    }

	@Override
	public void deleteProperty(String id) {
		// TODO Auto-generated method stub
		
	}

	
/*	public void deleteProperty(String id) {
		// TODO Auto-generated method stub
		deleteProperty(String id);
	}*/
}

