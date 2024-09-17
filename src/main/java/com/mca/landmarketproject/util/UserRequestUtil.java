package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.UserRequestDto;
import com.mca.landmarketproject.model.UserRequest;

public class UserRequestUtil {
	public static UserRequestDto convertUserRequestEntityToDto(UserRequest userRequest) {
		UserRequestDto dto = new UserRequestDto();
		BeanUtils.copyProperties(userRequest, dto);
		dto.setBuyerId(userRequest.getBuyer().getId());
		dto.setOwnerId(userRequest.getOwner().getId());
		return dto;
	}

	public static UserRequest convertUserRequestDtoToEntity(UserRequestDto dto) {
		UserRequest user = new UserRequest();
		BeanUtils.copyProperties(dto, user);
		return user;
	}
}
