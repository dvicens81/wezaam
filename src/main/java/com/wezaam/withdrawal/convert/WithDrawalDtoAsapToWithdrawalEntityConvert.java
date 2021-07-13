package com.wezaam.withdrawal.convert;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WithDrawalStatus;
/**
 * Convert WithDrawalDto to WithDrawalEntity.
 * This is for asap transaction.
 * Then status is processing and inform scheduled date like now.
 * 
 * @author dvicensnoguera
 *
 */
@Component
public class WithDrawalDtoAsapToWithdrawalEntityConvert extends AbstractWithDrawalConvert {

	/**
	 * Convert WithDrawalDto to WithDrawalEntity filling like asap transaction
	 */
	@Override
	public WithDrawalEntity convert(WithDrawalDto withDrawalDto, long userId) {
		WithDrawalEntity withDrawalEntity = super.convert(withDrawalDto, userId);
		withDrawalEntity.setStatus(WithDrawalStatus.PROCESSING.name());
		withDrawalEntity.setScheduledAt(LocalDate.now());
		return withDrawalEntity;
	}

}
