package com.wezaam.withdrawal.validator;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.dto.PaymentMethodDto;
import com.wezaam.withdrawal.dto.WhenDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.UserEntity;
import com.wezaam.withdrawal.enums.WhenStatusEnum;
import com.wezaam.withdrawal.service.UserServiceImpl;

@SpringBootTest
public class WithDrawalValidatorTest {
	
	private static final Integer USER_ID = 1;
	private static final String ASAP = "ASAP";
	private static final String OTHER = "OTHER";
	private static final String SCHEDULED = "SCHEDULED";
	private static final long PAYMENT_METHOD_ID = 1;
	private static final double AMOUNT = 234.5;
	private static final String DATE = "2034-06-23";
	
	@InjectMocks
	private WithDrawalValidator withDrawalValidator;
	@Mock
	private UserValidator userValidator;
	@Mock
	private WhenValidator whenValidator;
	@Mock
	private UserServiceImpl userService;
	@Mock
	private PaymentMethodValidator paymentValidator;
	
	private static WithDrawalDto withDrawalDto;
	private static UserEntity userEntity;
	
	@BeforeAll
	public static void setUp() {
		userEntity = UserEntity.builder().id(USER_ID).build();
		PaymentMethodDto paymentMethodDto = PaymentMethodDto.builder().id(PAYMENT_METHOD_ID).build();
		WhenDto whenDto = WhenDto.builder().type(WhenStatusEnum.ASAP.name()).build();
		withDrawalDto = WithDrawalDto.builder()
										.amount(AMOUNT)
										.paymentMethod(paymentMethodDto)
										.when(whenDto)
										.build();
	}
	
	@AfterAll
	public static void clean() {
		withDrawalDto = null;
		userEntity = null;
	}
	
	@Test
	public void validate_json_body_all_asap_ok() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(userEntity);
		when(userValidator.exists(userEntity)).thenReturn(true);
		when(whenValidator.exists(ASAP)).thenReturn(true);
		when(paymentValidator.exists(userEntity, PAYMENT_METHOD_ID)).thenReturn(true);
		//when paymentMethod and user is true (missing implementation)
		withDrawalValidator.validateBodyRequest(withDrawalDto, USER_ID);		
	}
	
	@Test
	@Disabled("When start the implementation of the validation for date disable this test ")
	public void validate_json_body_all_scheduled_ok() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(userEntity);
		withDrawalDto.getWhen().setType(SCHEDULED);
		withDrawalDto.getWhen().setDate(DATE);
		when(userValidator.exists(userEntity)).thenReturn(true);
		when(whenValidator.exists(SCHEDULED)).thenReturn(true);
		//when paymentMethod and user is true
		//when datevalidator returns true
		withDrawalValidator.validateBodyRequest(withDrawalDto, USER_ID);
		
	}
	
	@Test
	public void validate_json_body_user_not_ok() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(userEntity);
		when(userValidator.exists(userEntity)).thenReturn(false);
		Assertions.assertThrows(RuntimeException.class, () -> {
			withDrawalValidator.validateBodyRequest(withDrawalDto, USER_ID);
		});
	}
	
	@Test
	public void validate_json_body_type_not_ok() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(userEntity);
		when(userValidator.exists(userEntity)).thenReturn(true);
		when(whenValidator.exists(OTHER)).thenReturn(false);
		Assertions.assertThrows(RuntimeException.class, () -> {
			withDrawalValidator.validateBodyRequest(withDrawalDto, USER_ID);
		});
	}
	
	@Test
	@Disabled("When start the implementation of the validation for date disable this test ")
	public void validate_json_body_date_not_correct() {
		when(userService.getUserById(Mockito.anyLong())).thenReturn(userEntity);
		when(userValidator.exists(userEntity)).thenReturn(true);
		when(whenValidator.exists(SCHEDULED)).thenReturn(true);
		//when datevalidator returns false
		Assertions.assertThrows(RuntimeException.class, () -> {
			withDrawalValidator.validateBodyRequest(withDrawalDto, USER_ID);
		});
	}

}
