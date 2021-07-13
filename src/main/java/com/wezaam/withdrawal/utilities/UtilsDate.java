package com.wezaam.withdrawal.utilities;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class UtilsDate {
	/**
	 * Convert string date to localDate
	 * @param sDate
	 * @return
	 */
	public LocalDate convertStringToLocalDate(String sDate) {
		return LocalDate.parse(sDate);		
	}

}
