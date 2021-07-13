package com.wezaam.withdrawal.validator;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.entity.UserEntity;

@Component
public class PaymentMethodValidator {
	

	/**
	 * Check if payment method and user are related
	 * @param paymentMethod
	 * @param userId
	 * @return
	 */
	protected boolean exists(UserEntity userEntity, long paymentMethodId) {
		return userEntity.getPaymentMethods().stream().anyMatch(paymentMethod-> paymentMethod.getId() == paymentMethodId);
	}

}
