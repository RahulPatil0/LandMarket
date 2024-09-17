package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.ContactOwnerDto;
import com.mca.landmarketproject.model.ContactOwner;

public class ContactOwnerUtil {

    public static ContactOwnerDto convertContactOwnerEntityToDto(ContactOwner contactOwner) {
        ContactOwnerDto dto = new ContactOwnerDto();
        BeanUtils.copyProperties(contactOwner, dto);
        return dto;
    }

    public static ContactOwner convertContactOwnerDtoToEntity(ContactOwnerDto dto) {
        ContactOwner contactOwner = new ContactOwner();
        BeanUtils.copyProperties(dto, contactOwner);
        return contactOwner;
    }
}
