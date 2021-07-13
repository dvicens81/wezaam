package com.wezaam.withdrawal.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.dto.UserDto;
import com.wezaam.withdrawal.entity.UserEntity;

@SpringBootTest
public class UserEntityToUserDtoConvertTest {
	
	private static final long ID = 1;
	private static final String NAME = "Hertiw";
	
	@InjectMocks
	private UserEntityToUserDtoConvert userConvert;
	
	@Mock
	private PaymentMethodEntityToPaymentMethodDtoConvert paymentConvert;
	
	@Test
	public void convert_entity_to_dto() {
		UserEntity userEntity = UserEntity.builder()
											.id(ID)
											.name(NAME)
											.build();
		
		UserDto response = userConvert.convert(userEntity);
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(0, response.getPaymentMethods().size());
	}

}
