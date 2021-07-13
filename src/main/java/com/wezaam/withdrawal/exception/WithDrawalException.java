package com.wezaam.withdrawal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Easy Custom exception.
 * 
 * @author dvicensnoguera
 *
 */
@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class WithDrawalException extends RuntimeException{
	private static final long serialVersionUID = 8885147646735004911L;

	public WithDrawalException(String message){  
		super(message);  
	}  
}
