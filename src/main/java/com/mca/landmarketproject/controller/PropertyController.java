//
//package com.mca.landmarketproject.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mca.landmarketproject.dto.PropertyDto;
//import com.mca.landmarketproject.model.Property;
//import com.mca.landmarketproject.response.LandMarketRespones;
//import com.mca.landmarketproject.service.PropertyService;
//
//@RestController
//@CrossOrigin
//@RequestMapping(path = "/property")
//public class PropertyController {
//
//    @Autowired
//    private PropertyService propertyService;
//
//    // Fetch all properties
//    @GetMapping
//    public ResponseEntity<LandMarketRespones> getAllProperty() {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        String message = "";
//        try {
//            List<PropertyDto> listOfDto = propertyService.getAllProperty();
//            status = HttpStatus.OK;
//            return ResponseEntity.ok(new LandMarketRespones(listOfDto, status));
//        } catch (Exception exception) {
//            message = "Internal Server Error: " + exception.getLocalizedMessage();
//            return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
//        }
//    }
//
//    // Fetch a property by its ID
//    @GetMapping("/{propertyId}")
//    public ResponseEntity<LandMarketRespones> getPropertyById(@PathVariable(name = "propertyId") Integer propertyId) {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        String message = "";
//        try {
//            PropertyDto propertyDto = propertyService.getPropertyById(propertyId);
//            status = HttpStatus.OK;
//            return ResponseEntity.ok(new LandMarketRespones(propertyDto, status));
//        } catch (Exception exception) {
//            message = "Internal Server Error: " + exception.getLocalizedMessage();
//            return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
//        }
//    }
//
//    // Add a new property
//    @PostMapping("user-id/{userId}")
//    public ResponseEntity<LandMarketRespones> addNewProperty(
//            @PathVariable(name = "userId") Integer userId,
//            @RequestBody Property property) {
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        String message = "";
//        try {
//            message = propertyService.addNewProperty(property, userId);
//            status = HttpStatus.OK;
//            return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
//        } catch (Exception exception) {
//            message = "Internal Server Error: " + exception.getLocalizedMessage();
//            return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
//        }
//    }
//
//    // Update a property by its ID
//    @PutMapping("/{propertyId}")
//    public ResponseEntity<LandMarketRespones> updateProperty(
//            @PathVariable(name = "propertyId") Integer propertyId,
//            @RequestBody PropertyDto propertyDto) {
//
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        String message = "";
//
//        try {
//            // Call the service method to update the property
//            message = propertyService.updateProperty(propertyId, propertyDto);
//            status = HttpStatus.OK;
//            return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
//        } catch (Exception exception) {
//            message = "Internal Server Error: " + exception.getLocalizedMessage();
//            return ResponseEntity.status(status).body(new LandMarketRespones(message, status));
//        }
//    }
//}
package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.PropertyService;

@RestController
@CrossOrigin
@RequestMapping(path = "property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    private ResponseEntity<LandMarketRespones> buildResponse(HttpStatus status, Object body) {
        return ResponseEntity.status(status).body(new LandMarketRespones(body, status));
    }

    @GetMapping
    public ResponseEntity<LandMarketRespones> getAllProperty() {
        try {
            List<PropertyDto> listOfDto = propertyService.getAllProperty();
            return buildResponse(HttpStatus.OK, listOfDto);
        } catch (Exception exception) {
            return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + exception.getLocalizedMessage());
        }
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<LandMarketRespones> getPropertyById(@PathVariable(name = "propertyId") Integer propertyId) {
        try {
            PropertyDto propertyDto = propertyService.getPropertyById(propertyId);
            return buildResponse(HttpStatus.OK, propertyDto);
        } catch (Exception exception) {
            return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + exception.getLocalizedMessage());
        }
    }

//    @PostMapping("/user-id/{userId}")
//    public ResponseEntity<LandMarketRespones> addNewProperty(
//            @PathVariable(name = "userId") Integer userId,
//            @RequestBody PropertyDto property) {
//        try {
//            String message = propertyService.addNewProperty(property, userId);
//            return buildResponse(HttpStatus.OK, message);
//        } catch (Exception exception) {
//            return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + exception.getLocalizedMessage());
//        }
//    }
    @PostMapping("/delete-property/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable String id) {
        try {
            propertyService.deleteProperty(id); // Replace with your service method
            return new ResponseEntity<>("Property deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete property", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<LandMarketRespones> updateProperty(
            @PathVariable(name = "propertyId") Integer propertyId,
            @RequestBody PropertyDto propertyDto) {
        try {
            String message = propertyService.updateProperty(propertyId, propertyDto);
            return buildResponse(HttpStatus.OK, message);
        } catch (Exception exception) {
            return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + exception.getLocalizedMessage());
        }
    }
}
