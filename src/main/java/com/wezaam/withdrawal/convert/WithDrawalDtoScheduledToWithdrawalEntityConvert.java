package com.wezaam.withdrawal.convert;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WithDrawalStatus;
import com.wezaam.withdrawal.utilities.UtilsDate;

import lombok.AllArgsConstructor;
/**
 * Convert WithDrawalDto to WithDrawalEntity.
 * This is for scheduled transaction.
 * Then status is waiting and inform scheduled date with the information send it by the user
 * 
 * @author dvicensnoguera
 *
 */
@Component
@AllArgsConstructor
public class WithDrawalDtoScheduledToWithdrawalEntityConvert extends AbstractWithDrawalConvert {

	private final UtilsDate utilsDate;
	
	/**
	 * Convert WithDrawalDto to WithDrawalEntity filling like scheduled transaction
	 */
	@Override
	public WithDrawalEntity convert(WithDrawalDto withDrawalDto, long userId) {
		WithDrawalEntity withDrawalEntity = super.convert(withDrawalDto, userId);
		withDrawalEntity.setStatus(WithDrawalStatus.WAITING.name());
		withDrawalEntity.setScheduledAt(utilsDate.convertStringToLocalDate(withDrawalDto.getWhen().getDate()));
		return withDrawalEntity;
	}

}
