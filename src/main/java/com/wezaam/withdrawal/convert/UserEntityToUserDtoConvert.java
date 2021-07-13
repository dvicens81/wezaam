package com.wezaam.withdrawal.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.dto.UserDto;
import com.wezaam.withdrawal.entity.UserEntity;

import lombok.AllArgsConstructor;

/**
 * Convert UserEntity to UserDto
 * @author dvicensnoguera
 *
 */
@AllArgsConstructor
@Component
public class UserEntityToUserDtoConvert {
	
	private final PaymentMethodEntityToPaymentMethodDtoConvert paymentMethodConvert;
	
	public List<UserDto> convertList(List<UserEntity> listUsersEntity){
		return listUsersEntity.stream().map(this::convert).collect(Collectors.toList());
	}
	
	public UserDto convert(UserEntity userEntity) {
		return UserDto.builder()
						.id(userEntity.getId())
						.name(userEntity.getName())
						.paymentMethods(paymentMethodConvert.convertList(userEntity.getPaymentMethods()))
						.build();
	}

}
