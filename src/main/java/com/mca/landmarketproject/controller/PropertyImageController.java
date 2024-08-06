package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.PropertyImageDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.PropertyImage;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.PropertyImageService;

@RestController
@RequestMapping(path = "/propertyImage")
public class PropertyImageController {

    @Autowired
    private PropertyImageService propertyImageService;

    @GetMapping
    public ResponseEntity<LandMarketRespones> getAllPropertyImage() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        try {
            List<PropertyImageDto> listOfDto = propertyImageService.getAllPropertyImage();
            status = HttpStatus.OK;
            return ResponseEntity.ok(new LandMarketRespones(listOfDto, status));
        } catch (LandMarketException exception) {
            message = "Failed to retrieve properties data: " + exception.getLocalizedMessage();
        } catch (Exception exception) {
            message = "Internal Server Error: " + exception.getLocalizedMessage();
        }
        return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
    }

    @PostMapping("property-id/{propertyId}")
    public ResponseEntity<LandMarketRespones> addNewPropertyImage(@PathVariable(name = "propertyId") Integer propertyId,
                                                                  @RequestBody PropertyImage propertyImage) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "";
        try {
            message = propertyImageService.addNewPropertyImage(propertyImage, propertyId);
            status = HttpStatus.OK;
        } catch (LandMarketException exception) {
            message = "Failed to add property: " + exception.getLocalizedMessage();
        } catch (Exception exception) {
            message = "Internal Server Error: " + exception.getLocalizedMessage();
        }
        return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
    }
}

