package com.mca.landmarketproject.service;

import java.util.List;
import com.mca.landmarketproject.dto.PropertyImageDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.PropertyImage;

public interface PropertyImageService {
    List<PropertyImageDto> getAllPropertyImage() throws LandMarketException;
    String addNewPropertyImage(PropertyImage propertyImage, Integer propertyId) throws LandMarketException;
}