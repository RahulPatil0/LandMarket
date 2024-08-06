package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.NotificationDto;
import com.mca.landmarketproject.model.Notification;

public class NotificationUtil {
	public static NotificationDto convertNotificationEntityToDto(Notification notification)
	{
	NotificationDto dto=new NotificationDto();
	BeanUtils.copyProperties(notification, dto);
	dto.setUserId(notification.getUser().getId());
	return dto;
	}
	public static NotificationUtil convertNotificationDtoToEntity(NotificationDto dto)
	{
	NotificationUtil notification=new NotificationUtil();
	BeanUtils.copyProperties(dto,notification);
	return notification;
	}
}

