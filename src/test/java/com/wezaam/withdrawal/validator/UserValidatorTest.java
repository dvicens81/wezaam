package com.wezaam.withdrawal.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.entity.UserEntity;
import com.wezaam.withdrawal.service.UserServiceImpl;

@SpringBootTest
public class UserValidatorTest {
	
	private static final Integer USER_ID = 1;
	
	@InjectMocks
	private UserValidator userValidator;
	
	@Mock
	private UserServiceImpl userService;
	
	@Test
	public void when_user_exists() {
		UserEntity userEntity = UserEntity.builder().id(USER_ID).build();
		boolean exists = userValidator.exists(userEntity);		
		assertTrue(exists);
	}
	
	@Test
	public void when_user_not_exists() {
		boolean exists = userValidator.exists(null);		
		assertFalse(exists);
	}
}
