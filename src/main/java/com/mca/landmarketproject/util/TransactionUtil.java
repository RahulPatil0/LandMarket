package com.mca.landmarketproject.util;

import org.springframework.beans.BeanUtils;

import com.mca.landmarketproject.dto.TransactionDto;
import com.mca.landmarketproject.model.Transaction;

public class TransactionUtil {
	public static TransactionDto convertTransactionEntityToDto(Transaction transaction)
	{
	TransactionDto dto=new TransactionDto();
	BeanUtils.copyProperties(transaction, dto);
	dto.setBuyerId(transaction.getUser().getId());
	dto.setPropertyId(transaction.getProperties().getId());
	return dto;
	}
	public static Transaction convertTransactionDtoToEntity(TransactionDto dto)
	{
	Transaction transaction=new Transaction();
	BeanUtils.copyProperties(dto,transaction);
	return transaction;
	}

}
