package com.wezaam.withdrawal.convert;

import java.time.LocalDateTime;

import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
/**
 * Abstract class to convert withDrawalDto to withDrawalEntity
 * It is extending by WithDrawalDtoAsapToWithdrawalEntityConvert 
 * and WithDrawalDtoScheduledToWithdrawalEntityConvert
 * 
 * @author dvicensnoguera
 *
 */
public abstract class AbstractWithDrawalConvert {
	
	public WithDrawalEntity convert(WithDrawalDto withDrawalDto, long userId) {
		return WithDrawalEntity.builder()
								.createdAt(LocalDateTime.now())
								.paymentMethodId(withDrawalDto.getPaymentMethod().getId())
								.amount(withDrawalDto.getAmount())
								.userId(userId).build();
	}

}
