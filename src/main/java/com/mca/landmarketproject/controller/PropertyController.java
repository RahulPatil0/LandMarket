package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Property;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.PropertyService;

@RestController
@RequestMapping(path = "/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    
    @GetMapping
    public ResponseEntity<LandMarketRespones> getAllProperty() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        try {
            List<PropertyDto> listOfDto = propertyService.getAllProperty();
            status = HttpStatus.OK;
            return ResponseEntity.ok(new LandMarketRespones(listOfDto, status));
        } catch (LandMarketException exception) {
            message = "Failed to retrieve properties data: " + exception.getLocalizedMessage();
        } catch (Exception exception) {
            message = "Internal Server Error: " + exception.getLocalizedMessage();
        }
        return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
    }

    @PostMapping("user-id/{userId}")
    public ResponseEntity<LandMarketRespones> addNewProperty(@PathVariable(name = "userId") Integer userId,
                                                            
                                                             @RequestBody Property property) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        try {
            message = propertyService.addNewProperty(property, userId);
            status = HttpStatus.OK;
        } catch (LandMarketException exception) {
            message = "Failed to add property: " + exception.getLocalizedMessage();
        } catch (Exception exception) {
            message = "Internal Server Error: " + exception.getLocalizedMessage();
        }
        return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
    }
    
    @PutMapping("/{propertyId}")
    public ResponseEntity<LandMarketRespones> updateProperty(
            @PathVariable(name = "propertyId") Integer propertyId,
            @RequestBody PropertyDto propertyDto) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";

        try {
            // Call the service method to update the property
            message = propertyService.updateProperty(propertyId, propertyDto);
            status = HttpStatus.OK;
        } catch (LandMarketException exception) {
            message = "Failed to Update Property: " + exception.getLocalizedMessage();
        } catch (Exception exception) {
            message = "Internal Server Error: " + exception.getLocalizedMessage();
        }

        return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
    }
}

