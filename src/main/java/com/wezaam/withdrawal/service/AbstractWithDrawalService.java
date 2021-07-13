package com.wezaam.withdrawal.service;

import java.util.List;

import com.wezaam.withdrawal.async.WithDrawalEventService;
import com.wezaam.withdrawal.convert.WithDrawalEntityToTransactionDtoConvert;
import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.repository.WithDrawalRepository;
import com.wezaam.withdrawal.service.factory.WithDrawalService;
import com.wezaam.withdrawal.validator.WithDrawalValidator;

import lombok.AllArgsConstructor;
/**
 * Abstract class for WithDrawalService
 * 
 * @author dvicensnoguera
 *
 */
@AllArgsConstructor
public abstract class AbstractWithDrawalService implements WithDrawalService {
	
	protected final WithDrawalRepository repository;
	protected final WithDrawalValidator validator;
	protected final WithDrawalEntityToTransactionDtoConvert transactionConvert;
	protected final WithDrawalEventService eventService;
	
	/**
	 * Valid request body
	 * @param withDrawalDto
	 * @param userId
	 */
	protected void validRequestBody(WithDrawalDto withDrawalDto, long userId) {
		validator.validateBodyRequest(withDrawalDto, userId);
	}
	
	protected TransactionDto convertWithDrawalEntityToTransactionDto(WithDrawalEntity entity) {
		return transactionConvert.convert(entity);
	}
	
	@Override
	public WithDrawalEntity save(WithDrawalEntity entity) {
		return repository.save(entity);
	}

	@Override
	public void sendToQueue(List<WithDrawalEntity> listToSend) {
		eventService.send(listToSend);		
	}

}
