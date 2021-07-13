package com.wezaam.withdrawal.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.entity.PaymentMethodEntity;
import com.wezaam.withdrawal.entity.UserEntity;

@SpringBootTest
public class PaymentMethodValidatorTest {
	
	private final static long PAYMENT_METHOD = 1;
	private final static long USER_ID = 1;
	private final static long PAYMENT_METHOD_NOT_RELATED = 2;
	
	@InjectMocks
	private PaymentMethodValidator paymentMethodValidator;
	
	@Test
	public void when_paymentMethod_exists() {
		PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder().id(PAYMENT_METHOD).build();
		List<PaymentMethodEntity> listPaymentMethod = Stream.of(paymentMethodEntity).collect(Collectors.toList());;
		UserEntity userEntity = UserEntity.builder().id(USER_ID).paymentMethods(listPaymentMethod).build();
		assertTrue(paymentMethodValidator.exists(userEntity, PAYMENT_METHOD));
	}
	
	@Test
	public void when_paymentMethod_not_exists() {
		PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder().id(PAYMENT_METHOD).build();
		List<PaymentMethodEntity> listPaymentMethod = Stream.of(paymentMethodEntity).collect(Collectors.toList());;
		UserEntity userEntity = UserEntity.builder().id(USER_ID).paymentMethods(listPaymentMethod).build();
		assertFalse(paymentMethodValidator.exists(userEntity, PAYMENT_METHOD_NOT_RELATED));
	}

}
