package com.wezaam.withdrawal.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsDateTest {
	
	@InjectMocks
	private UtilsDate utilsDate;
	
	@Test
	public void convert_string_to_localDate_test() {
		String date = "2021-05-23";
		LocalDate response = utilsDate.convertStringToLocalDate(date);
		assertEquals(23, response.getDayOfMonth());
		assertEquals(5, response.getMonthValue());
		assertEquals(2021, response.getYear());
	}

}
