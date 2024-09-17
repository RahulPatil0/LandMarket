package com.mca.landmarketproject.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.PropertyDto;
import com.mca.landmarketproject.dto.PropertyImageDto;
import com.mca.landmarketproject.model.Property;
import com.mca.landmarketproject.model.PropertyImage;

public class PropertyUtil {

    public static PropertyDto convertPropertyEntityToDto(Property property) {
        PropertyDto dto = new PropertyDto();
        dto.setUserId(property.getUser().getId());
        List<PropertyImageDto> imageDtos = new ArrayList<>();
        for (PropertyImage propertyImage : property.getPropertyImages()) {
			PropertyImageDto imageDto = PropertyImageUtil.convertPropertyImagesEntityToDto(propertyImage);
			imageDtos.add(imageDto);
		}
        dto.setPropertyImages(imageDtos);
        BeanUtils.copyProperties(property, dto);
        return dto;
    }

    public static Property convertPropertyDtoToEntity(PropertyDto dto) {
        Property property = new Property();
        BeanUtils.copyProperties(dto, property);
        return property;
    }
}
