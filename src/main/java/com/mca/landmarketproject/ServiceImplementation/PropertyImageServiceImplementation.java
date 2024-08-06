package com.mca.landmarketproject.ServiceImplementation;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.PropertyImageRepository;
import com.mca.landmarketproject.dao.PropertyRepository;
import com.mca.landmarketproject.dto.PropertyImageDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Property;
import com.mca.landmarketproject.model.PropertyImage;
import com.mca.landmarketproject.service.PropertyImageService;
import com.mca.landmarketproject.util.PropertyImageUtil;

@Service
public class PropertyImageServiceImplementation implements PropertyImageService {

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    @Autowired
    private PropertyRepository propertiesRepository;

    @Override
    public List<PropertyImageDto> getAllPropertyImage() throws LandMarketException {
        try {
            return propertyImageRepository.findAll().stream()
                    .map(PropertyImageUtil::convertPropertyImagesEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new LandMarketException("Failed to retrieve property images: " + exception.getLocalizedMessage());
        }
    }

    @Override
    public String addNewPropertyImage(PropertyImage propertyImage, Integer propertyId) throws LandMarketException {
        try {
            Property property = propertiesRepository.findById(propertyId).orElseThrow(() -> new LandMarketException("Property not found"));
            propertyImage.setProperty(property);
            propertyImageRepository.save(propertyImage);
            return "Property image added successfully";
        } catch (Exception exception) {
            throw new LandMarketException("Failed to add property image: " + exception.getLocalizedMessage());
        }
    }
}
