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
public class WhenDto {
	@ApiModelProperty(value="asap or scheduled")
	private String type;
	
	@ApiModelProperty(value="ASAP it is not necessary to inform. SCHEDULED is mandatory")
	private String date; //For ASAP is null

}
