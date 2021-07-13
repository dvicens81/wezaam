package com.wezaam.withdrawal.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.dto.PaymentMethodDto;
import com.wezaam.withdrawal.dto.WhenDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WhenStatusEnum;
import com.wezaam.withdrawal.enums.WithDrawalStatus;
import com.wezaam.withdrawal.utilities.UtilsDate;

@SpringBootTest
public class WithDrawalDtoScheduledToWithdrawalEntityConvertTest {
	
	private static final long PAYMENT_METHOD_ID = 1;
	private static final double AMOUNT = 234.5;
	private static final long USER_ID = 1;
	private static final String DATE = "2025/05/21";
	
	@InjectMocks
	private WithDrawalDtoScheduledToWithdrawalEntityConvert convert;
	
	@Mock
	private UtilsDate utilsDate;
	
	@Test
	public void convert_dto_to_entity_scheduled_test() {
		when(utilsDate.convertStringToLocalDate(DATE)).thenReturn(LocalDate.of(2025, 5, 21));
		
		PaymentMethodDto paymentMethodDto = PaymentMethodDto.builder().id(PAYMENT_METHOD_ID).build();
		WhenDto whenDto = WhenDto.builder().type(WhenStatusEnum.SCHEDULED.name()).date(DATE).build();
		WithDrawalDto withDrawalDto = WithDrawalDto.builder()
													.amount(AMOUNT)
													.paymentMethod(paymentMethodDto)
													.when(whenDto)
													.build();
		WithDrawalEntity response = convert.convert(withDrawalDto, USER_ID);
		assertEquals(USER_ID, response.getUserId());
		assertEquals(PAYMENT_METHOD_ID, response.getUserId());
		assertEquals(AMOUNT, response.getAmount());
		assertEquals(WithDrawalStatus.WAITING.name(), response.getStatus());
		assertEquals(21, response.getScheduledAt().getDayOfMonth());
		assertEquals(5, response.getScheduledAt().getMonthValue());
		assertEquals(2025, response.getScheduledAt().getYear());
	}

}
