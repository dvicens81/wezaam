package com.wezaam.withdrawal.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.dto.PaymentMethodDto;
import com.wezaam.withdrawal.entity.PaymentMethodEntity;

@SpringBootTest
public class PaymentMethodEntityToPaymentMethodDtoConvertTest {
	
	private static final long ID = 1;
	private static final String NAME = "My personal account";
	
	@InjectMocks
	private PaymentMethodEntityToPaymentMethodDtoConvert paymentConvert;
	
	@Test
	public void convert_entity_to_dto() {
		PaymentMethodEntity entity = PaymentMethodEntity.builder().id(ID).name(NAME).build();
		
		PaymentMethodDto response = paymentConvert.convert(entity);
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getDescription());				
	}
}
