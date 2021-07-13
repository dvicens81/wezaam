package com.wezaam.withdrawal.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WhenValidatorTest {
	
	private static final String ASAP = "asap";
	private static final String SCHEDULED = "scheduled";
	private static final String OTHER_STATUS = "other";
	
	@InjectMocks
	private WhenValidator whenValidator;
	
	@Test
	public void when_asap_exists() {
		assertTrue(whenValidator.exists(ASAP));
	}
	
	@Test
	public void when_scheduled_exists() {
		assertTrue(whenValidator.exists(SCHEDULED));
	}
	
	@Test
	public void when_other_status_not_exists() {
		assertFalse(whenValidator.exists(OTHER_STATUS));
	}
	

}
