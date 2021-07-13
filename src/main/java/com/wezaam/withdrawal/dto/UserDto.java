package com.wezaam.withdrawal.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserDto {
	private Long id;
	private String name;
	private List<PaymentMethodDto> paymentMethods;
}
