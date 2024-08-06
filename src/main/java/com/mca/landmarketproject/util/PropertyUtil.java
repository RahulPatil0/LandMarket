package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.model.Property;

public class PropertyUtil {

    public static PropertyDto convertPropertyEntityToDto(Property property) {
        PropertyDto dto = new PropertyDto();
        BeanUtils.copyProperties(property, dto);
        return dto;
    }

    public static Property convertPropertyDtoToEntity(PropertyDto dto) {
        Property property = new Property();
        BeanUtils.copyProperties(dto, property);
        return property;
    }
}
