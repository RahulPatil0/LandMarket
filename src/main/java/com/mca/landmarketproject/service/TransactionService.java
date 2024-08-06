package com.mca.landmarketproject.service;

import java.util.List;

import com.mca.landmarketproject.dto.TransactionDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Transaction;

public interface TransactionService {

		List<TransactionDto> getAllTransaction() throws LandMarketException;

		String addNewTransaction(Integer propertyId,Integer buyerId,Transaction transaction) throws LandMarketException;

		
}

