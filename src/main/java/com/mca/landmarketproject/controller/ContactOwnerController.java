package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mca.landmarketproject.dto.ContactOwnerDto;
import com.mca.landmarketproject.service.ContactOwnerService;
import com.mca.landmarketproject.exception.LandMarketException;

@RestController
@RequestMapping("/contact-owners")
public class ContactOwnerController {

    @Autowired
    private ContactOwnerService contactOwnerService;

    @GetMapping
    public ResponseEntity<List<ContactOwnerDto>> getAllContactOwners() {
        try {
            return ResponseEntity.ok(contactOwnerService.getAllContactOwners());
        } catch (LandMarketException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addNewContactOwner(@RequestBody ContactOwnerDto contactOwnerDto) {
        try {
            String response = contactOwnerService.addNewContactOwner(contactOwnerDto);
            return ResponseEntity.ok(response);
        } catch (LandMarketException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
