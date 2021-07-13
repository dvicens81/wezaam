package com.wezaam.withdrawal.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.async.WithDrawalEventService;
import com.wezaam.withdrawal.convert.WithDrawalDtoAsapToWithdrawalEntityConvert;
import com.wezaam.withdrawal.convert.WithDrawalEntityToTransactionDtoConvert;
import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WhenStatusEnum;
import com.wezaam.withdrawal.repository.WithDrawalRepository;
import com.wezaam.withdrawal.validator.WithDrawalValidator;

import lombok.extern.slf4j.Slf4j;
/**
 * ASAP service
 * Execute the withdrawal and send to queue to process it.
 * 
 * @author dvicensnoguera
 *
 */
@Service
@Slf4j
public class WithDrawalAsapService extends AbstractWithDrawalService{	
	
	private final WithDrawalDtoAsapToWithdrawalEntityConvert convertAsap;

	public WithDrawalAsapService(WithDrawalRepository repository, WithDrawalValidator validator,
			WithDrawalEntityToTransactionDtoConvert transactionConvert, WithDrawalEventService eventService) {
		super(repository, validator, transactionConvert, eventService);
		convertAsap = new WithDrawalDtoAsapToWithdrawalEntityConvert();
	}		

	@Override
	public String getType() {
		return WhenStatusEnum.ASAP.name();
	}

	@Override
	public TransactionDto executeWithDrawal(WithDrawalDto withDrawalDto, long userId) {
		log.info("ASAP service. Execute with user {} ", userId);
		//validate the information received
		validRequestBody(withDrawalDto, userId);
		log.info("ASAP service. All the data is correct. Payment Method {} ", withDrawalDto.getPaymentMethod().getId() );
		WithDrawalEntity entity = convertAsap.convert(withDrawalDto, userId);
		entity = this.save(entity);
		sendToQueue(Arrays.asList(entity));
		return convertWithDrawalEntityToTransactionDto(entity);
	}

}
