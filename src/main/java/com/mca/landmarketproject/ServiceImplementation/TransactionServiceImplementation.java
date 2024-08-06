package com.mca.landmarketproject.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mca.landmarketproject.dao.PropertyRepository;
import com.mca.landmarketproject.dao.TransactionRepository;
import com.mca.landmarketproject.dao.UserRepository;
import com.mca.landmarketproject.dto.TransactionDto;
import com.mca.landmarketproject.exception.LandMarketException;
import com.mca.landmarketproject.model.Property;
import com.mca.landmarketproject.model.Transaction;
import com.mca.landmarketproject.model.User;
import com.mca.landmarketproject.service.TransactionService;
import com.mca.landmarketproject.util.TransactionUtil;

@Service
public class TransactionServiceImplementation implements TransactionService {
	@Autowired
	private TransactionRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	public List<TransactionDto> getAllTransaction() throws LandMarketException {
		try {
			List<TransactionDto> listOfDto = repository.findAll().stream()
					.map(TransactionUtil::convertTransactionEntityToDto).collect(Collectors.toList());
			return listOfDto;
		} catch (Exception exception) {
			throw new LandMarketException(exception.getLocalizedMessage());
		}
	}

	public String addNewTransaction(Integer buyerId, Integer propertyId, Transaction transaction)
			throws LandMarketException {
		// Transaction transaction = TransactionUtil.convertTransactionDtoToEntity(dto);
		try {
			User user = userRepository.findById(buyerId).get();
			if (user == null) {
				throw new LandMarketException("No user found");
			}
			transaction.setUser(user);

			Property property = propertyRepository.findById(propertyId).get();
			if (property == null) {
				throw new LandMarketException("No property found");
			}
			transaction.setProperties(property);

			repository.save(transaction);
			return "Transaction Done successfully";
		} catch (DataIntegrityViolationException exception) {
			throw new LandMarketException("Transaction has been done already.." + exception.getLocalizedMessage());

		}

	}
}
