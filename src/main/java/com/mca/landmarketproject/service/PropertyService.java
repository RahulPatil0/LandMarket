package com.mca.landmarketproject.service;

import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Property;

import java.util.List;

public interface PropertyService {
    List<PropertyDto> getAllProperty() throws LandMarketException;
   
    String addNewProperty(Property property, Integer userId) throws LandMarketException;
    String updateProperty(Integer propertyId, PropertyDto propertyDto) throws LandMarketException;
}
