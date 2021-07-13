package com.wezaam.withdrawal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wezaam.withdrawal.entity.UserEntity;
import com.wezaam.withdrawal.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
	
	private static final long USER_ID = 1;
	private static final String USER_NAME = "Klaus";

	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void when_user_is_not_empty() {
		UserEntity userEntity = UserEntity.builder().id(USER_ID).name(USER_NAME).build();
		when(userRepository.getUserById(USER_ID)).thenReturn(userEntity);
		UserEntity response = userService.getUserById(USER_ID);
		assertEquals(USER_ID, response.getId());
		assertEquals(USER_NAME, response.getName());
	}
	
	@Test
	public void when_user_is_null() {
		when(userRepository.getUserById(USER_ID)).thenReturn(null);
		UserEntity response = userService.getUserById(USER_ID);
		assertNull(response);
		
	}
}
