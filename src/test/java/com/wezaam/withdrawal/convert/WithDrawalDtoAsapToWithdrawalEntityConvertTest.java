package com.wezaam.withdrawal.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.dto.PaymentMethodDto;
import com.wezaam.withdrawal.dto.WhenDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WhenStatusEnum;
import com.wezaam.withdrawal.enums.WithDrawalStatus;

@SpringBootTest
public class WithDrawalDtoAsapToWithdrawalEntityConvertTest {
	
	private static final long PAYMENT_METHOD_ID = 1;
	private static final double AMOUNT = 234.5;
	private static final long USER_ID = 1;
	
	@InjectMocks
	private WithDrawalDtoAsapToWithdrawalEntityConvert convert;
	
	@Test
	public void convert_dto_to_entity_asap_test() {
		LocalDate localDate = LocalDate.now();
		PaymentMethodDto paymentMethodDto = PaymentMethodDto.builder().id(PAYMENT_METHOD_ID).build();
		WhenDto whenDto = WhenDto.builder().type(WhenStatusEnum.ASAP.name()).build();
		WithDrawalDto withDrawalDto = WithDrawalDto.builder()
													.amount(AMOUNT)
													.paymentMethod(paymentMethodDto)
													.when(whenDto)
													.build();
		WithDrawalEntity response = convert.convert(withDrawalDto, USER_ID);
		assertEquals(USER_ID, response.getUserId());
		assertEquals(PAYMENT_METHOD_ID, response.getUserId());
		assertEquals(AMOUNT, response.getAmount());
		assertEquals(WithDrawalStatus.PROCESSING.name(), response.getStatus());
		assertEquals(localDate.getDayOfMonth(), response.getScheduledAt().getDayOfMonth());
		assertEquals(localDate.getMonth().getValue(), response.getScheduledAt().getMonthValue());
		assertEquals(localDate.getYear(), response.getScheduledAt().getYear());
	}

}
