package com.wezaam.withdrawal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PaymentMethodDto {	
	private Long id;
	@ApiModelProperty(value="Optional value on the request")
	private String description;
}
