package com.wezaam.withdrawal.convert;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;

/**
 * This class is to send only the id on the json body response 
 * when a withDrawal is executed
 * @author dvicensnoguera
 *
 */
@Component
public class WithDrawalEntityToTransactionDtoConvert {
	/**
	 * Convert withDrawalEntity to TransactionDto to send only the transaction ID (ID)
	 * Maybe this class will disappear. 
	 * But for first version I prefer only send transactionId like body response
	 * and not all the withDrawalDto.
	 * Only it is sended the information necessary on the body response
	 * @param entity
	 * @return
	 */
	public TransactionDto convert(WithDrawalEntity entity) {
		return TransactionDto.builder().id(entity.getId()).status(entity.getStatus()).build();
	}
}
