package com.wezaam.withdrawal.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WhenStatusEnum;

@SpringBootTest
public class WithDrawalEntityToTransactionDtoConvertTest {
	
	private static final long PAYMENT_METHOD_ID = 1;
	private static final double AMOUNT = 234.5;
	private static final long TRANSACTION_ID = 1;
	
	@InjectMocks
	private WithDrawalEntityToTransactionDtoConvert convert;
	
	@Test
	public void convert_entity_to_transactionDto() {
		WithDrawalEntity entity = WithDrawalEntity.builder()
													.id(TRANSACTION_ID)
													.amount(AMOUNT)
													.createdAt(LocalDateTime.now())
													.paymentMethodId(PAYMENT_METHOD_ID)
													.status(WhenStatusEnum.ASAP.name()).build();
		TransactionDto response = convert.convert(entity);
		assertEquals(TRANSACTION_ID, response.getId());
		assertEquals(WhenStatusEnum.ASAP.name(), response.getStatus());
	}

}
