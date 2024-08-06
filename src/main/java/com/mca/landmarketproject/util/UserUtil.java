package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.UserDto;
import com.mca.landmarketproject.model.User;

public class UserUtil {
	
public static UserDto convertUserEntityToDto(User user)
{
UserDto dto=new UserDto();
BeanUtils.copyProperties(user, dto);
return dto;
}

public static User convertUserDtoToEntity(UserDto dto)
{
User user=new User();
BeanUtils.copyProperties(dto,user);
return user;
}

}