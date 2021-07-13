package com.wezaam.withdrawal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.async.WithDrawalEventService;
import com.wezaam.withdrawal.convert.WithDrawalDtoScheduledToWithdrawalEntityConvert;
import com.wezaam.withdrawal.convert.WithDrawalEntityToTransactionDtoConvert;
import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WhenStatusEnum;
import com.wezaam.withdrawal.enums.WithDrawalStatus;
import com.wezaam.withdrawal.repository.WithDrawalRepository;
import com.wezaam.withdrawal.utilities.UtilsDate;
import com.wezaam.withdrawal.validator.WithDrawalValidator;

import lombok.extern.slf4j.Slf4j;
/**
 * Scheduled service.
 * Is similar to ASAP service but in this case 
 * when it is executing the withdrawal it is not send it to the queue
 * The scheduled job is who execute searching by date and status
 * 
 * @author dvicensnoguera
 *
 */
@Service
@Slf4j
public class WithDrawalScheduledService extends AbstractWithDrawalService{
	
	private final WithDrawalDtoScheduledToWithdrawalEntityConvert convertScheduled;
	
	public WithDrawalScheduledService(WithDrawalRepository repository, WithDrawalValidator validator,
			WithDrawalEntityToTransactionDtoConvert transactionConvert, WithDrawalEventService eventService) {
		super(repository, validator, transactionConvert, eventService);
		convertScheduled = new WithDrawalDtoScheduledToWithdrawalEntityConvert(new UtilsDate());
	}	

	@Override
	public String getType() {
		return WhenStatusEnum.SCHEDULED.name();
	}

	@Override
	public TransactionDto executeWithDrawal(WithDrawalDto withDrawalDto, long userId) {
		log.info("Scheduled service. Execute with user {} ", userId);
		//validate the information received
		validRequestBody(withDrawalDto, userId);
		log.info("Scheduled service. All the data is correct. Payment Method {} ", withDrawalDto.getPaymentMethod().getId() );
		WithDrawalEntity entity = convertScheduled.convert(withDrawalDto, userId);
		entity = save(entity);
		return convertWithDrawalEntityToTransactionDto(entity);
	}
	/**
	 * Get the waiting withdrawal by date and send to queue
	 * @param dateToProcess
	 */
	public void getWithDrawalWaiting(LocalDate dateToProcess) {
		List<WithDrawalEntity> listWaiting = repository.getWithDrawalByStatusAndLessScheduledAtThanSpecificDate(WithDrawalStatus.WAITING.name(), dateToProcess);
		log.info("Scheduled job. Size to process {}", listWaiting.size());
		Optional.of(listWaiting).filter(CollectionUtils::isNotEmpty)
								.ifPresent(this::sendToQueue);
	}
}
