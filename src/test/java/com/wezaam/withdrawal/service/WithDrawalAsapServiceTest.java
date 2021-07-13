package com.wezaam.withdrawal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.async.WithDrawalEventService;
import com.wezaam.withdrawal.convert.WithDrawalDtoAsapToWithdrawalEntityConvert;
import com.wezaam.withdrawal.convert.WithDrawalEntityToTransactionDtoConvert;
import com.wezaam.withdrawal.dto.PaymentMethodDto;
import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WhenDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;
import com.wezaam.withdrawal.enums.WhenStatusEnum;
import com.wezaam.withdrawal.repository.WithDrawalRepository;
import com.wezaam.withdrawal.validator.WithDrawalValidator;

@SpringBootTest
public class WithDrawalAsapServiceTest {
	
	private static final long PAYMENT_METHOD_ID = 1;
	private static final double AMOUNT = 234.5;
	private static final long USER_ID = 1;
	private static final long TRANSACTION_ID = 1;
	
	@InjectMocks
	private WithDrawalAsapService withDrawalAsapService;
	
	@Mock
	private WithDrawalDtoAsapToWithdrawalEntityConvert convertAsap;
	@Mock
	private WithDrawalRepository repository;
	@Mock
	private WithDrawalValidator validator;
	@Mock
	private WithDrawalEntityToTransactionDtoConvert transactionConvert;
	@Mock
	private WithDrawalEventService eventService;
	
	@Test
	public void execute_withDrawal() {
		PaymentMethodDto paymentMethodDto = PaymentMethodDto.builder().id(PAYMENT_METHOD_ID).build();
		WhenDto whenDto = WhenDto.builder().type(WhenStatusEnum.ASAP.name()).build();
		WithDrawalDto withDrawalDto = WithDrawalDto.builder()
													.amount(AMOUNT)
													.paymentMethod(paymentMethodDto)
													.when(whenDto)
													.build();
		
		WithDrawalEntity entityToDataBase = WithDrawalEntity.builder()
															.amount(AMOUNT)
															.createdAt(LocalDateTime.now())
															.paymentMethodId(withDrawalDto.getPaymentMethod().getId())
															.status(WhenStatusEnum.ASAP.name()).build();
		TransactionDto transactionDto = TransactionDto.builder().id(TRANSACTION_ID).status(WhenStatusEnum.ASAP.name()).build();
		WithDrawalEntity entityFromDataBase = WithDrawalEntity.builder()
																.id(TRANSACTION_ID)
																.amount(AMOUNT)
																.createdAt(LocalDateTime.now())
																.paymentMethodId(withDrawalDto.getPaymentMethod().getId())
																.status(WhenStatusEnum.ASAP.name()).build();
		when(convertAsap.convert(withDrawalDto, USER_ID)).thenReturn(entityToDataBase);
		
		when(repository.save(Mockito.any(WithDrawalEntity.class))).thenReturn(entityFromDataBase);
		when(transactionConvert.convert(entityFromDataBase)).thenReturn(transactionDto);
		
		TransactionDto response = withDrawalAsapService.executeWithDrawal(withDrawalDto, USER_ID);
		assertEquals(TRANSACTION_ID, response.getId());
		assertEquals(WhenStatusEnum.ASAP.name(), response.getStatus());
	}

}
