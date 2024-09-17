package com.mca.landmarketproject.service;

import java.util.List;
import com.mca.landmarketproject.dto.ContactOwnerDto;
import com.mca.landmarketproject.exception.LandMarketException;

public interface ContactOwnerService {
    List<ContactOwnerDto> getAllContactOwners() throws LandMarketException;
    String addNewContactOwner(ContactOwnerDto contactOwnerDto) throws LandMarketException;
}
