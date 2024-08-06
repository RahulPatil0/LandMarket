package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.PropertyImageDto;
import com.mca.landmarketproject.model.PropertyImage;

public class PropertyImageUtil {
	public static PropertyImageDto convertPropertyImagesEntityToDto(PropertyImage propertyimages)
	{
	PropertyImageDto dto=new PropertyImageDto();
	BeanUtils.copyProperties(propertyimages, dto);
	return dto;
	}
	public static PropertyImage convertPropertyImagesDtoToEntity(PropertyImage dto)
	{
	PropertyImage PropertyImages=new PropertyImage();
	BeanUtils.copyProperties(dto,PropertyImages);
	return dto;
	}

}
