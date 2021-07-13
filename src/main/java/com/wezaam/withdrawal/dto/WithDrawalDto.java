package com.wezaam.withdrawal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class WithDrawalDto {
	
	private PaymentMethodDto paymentMethod;
	private Double amount;
	private WhenDto when;

}
