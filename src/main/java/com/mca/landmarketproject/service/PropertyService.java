//package com.mca.landmarketproject.service;
//
//import com.mca.landmarketproject.dto.PropertyDto;
//import com.mca.landmarketproject.exception.LandMarketException;
//import com.mca.landmarketproject.model.Property;
//
//import java.util.List;
//
//public interface PropertyService {
//    List<PropertyDto> getAllProperty() throws LandMarketException;
//   
//    String addNewProperty(Property property, Integer userId) throws LandMarketException;
//    String updateProperty(Integer propertyId, PropertyDto propertyDto) throws LandMarketException;
//
//	PropertyDto getPropertyById(Integer propertyId);
//}
//package com.mca.landmarketproject.service;
//
//import com.mca.landmarketproject.dto.PropertyDto;
//import com.mca.landmarketproject.exception.LandMarketException;
//import com.mca.landmarketproject.model.Property;
//
//import java.util.List;
//
//public interface PropertyService {
//    List<PropertyDto> getAllProperty() throws LandMarketException;
//   
//    String addNewProperty(Property property, Integer userId) throws LandMarketException;
//    String updateProperty(Integer propertyId, PropertyDto propertyDto) throws LandMarketException;
//
//    PropertyDto getPropertyById(Integer propertyId) throws LandMarketException;
//}
package com.mca.landmarketproject.service;

import java.util.List;

import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.exception.LandMarketException;

public interface PropertyService {
    List<PropertyDto> getAllProperty() throws LandMarketException;
   
    String addNewProperty(PropertyDto propertyDto, Integer userId) throws LandMarketException;
    String updateProperty(Integer propertyId, PropertyDto propertyDto) throws LandMarketException;

    PropertyDto getPropertyById(Integer propertyId) throws LandMarketException;

	void deleteProperty(String id);
}
