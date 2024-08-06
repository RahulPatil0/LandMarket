
package com.mca.landmarketproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mca.landmarketproject.dto.TransactionDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Transaction;
import com.mca.landmarketproject.response.LandMarketRespones;
import com.mca.landmarketproject.service.TransactionService;

import jakarta.transaction.TransactionalException;

@RestController
@RequestMapping(path = "/Transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public LandMarketRespones getAllTransaction() {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			List<TransactionDto> listOfDto = transactionService.getAllTransaction();
			status = HttpStatus.OK;
			return new LandMarketRespones(listOfDto, status);
		} catch (LandMarketException exception) {
			message = "Failed to retrieve transaction data !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}

	@PostMapping("/buyer/{buyer_id}/property/{property_id}")
	public LandMarketRespones addNewTransaction(@PathVariable(name = "buyer_id") Integer buyerId,
			@PathVariable(name = "property_id") Integer propertyId, @RequestBody Transaction transaction) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "";
		try {
			message = transactionService.addNewTransaction(buyerId, propertyId, transaction);
			status = HttpStatus.OK;

		} catch (TransactionalException exception) {
			message = "Failed to add Transaction !!" + exception.getLocalizedMessage();
		} catch (Exception exception) {
			message = "Internal Server Error !!" + exception.getLocalizedMessage();
		}
		return new LandMarketRespones(message, status);
	}

}
