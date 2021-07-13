package com.wezaam.withdrawal.validator;

import java.util.Optional;

import org.apache.commons.lang3.BooleanUtils;

import com.wezaam.withdrawal.exception.WithDrawalException;

public class AbstractWithDrawalValidator {
	/**
	 * Check if boolean isTrue orElseThrow an exception
	 * @param isCorrect
	 * @param message
	 * @return
	 */
	protected boolean checkBooleanOrElseThow(boolean isCorrect, String message) {
		//TODO Create CustomExceptionHandler insteadOf this WithDrawalException
		return Optional.of(isCorrect)
						.filter(BooleanUtils::isTrue)
						.orElseThrow(()-> new WithDrawalException(message));
	}

}
