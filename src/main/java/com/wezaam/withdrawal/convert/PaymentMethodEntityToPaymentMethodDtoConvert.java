package com.wezaam.withdrawal.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.dto.PaymentMethodDto;
import com.wezaam.withdrawal.entity.PaymentMethodEntity;
/**
 * Convert class payment method entity to dto
 * 
 * @author dvicensnoguera
 *
 */
@Component
public class PaymentMethodEntityToPaymentMethodDtoConvert {
	
	public List<PaymentMethodDto> convertList (List<PaymentMethodEntity> listPaymentMethodEntity){
		return listPaymentMethodEntity.stream().map(this::convert).collect(Collectors.toList());
	}
	
	public PaymentMethodDto convert (PaymentMethodEntity paymentMethodEntity) {
		return PaymentMethodDto.builder()
								.id(paymentMethodEntity.getId())
								.description(paymentMethodEntity.getName())
								.build();
	}

}
