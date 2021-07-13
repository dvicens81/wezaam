package com.wezaam.withdrawal.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.enums.WhenStatusEnum;
/**
 * All the validation that it is needed fot whenDto object
 * @author dvicensnoguera
 *
 */
@Component
public class WhenValidator {
	/**
	 * Return if exists the parameter send it by the user
	 * @param name
	 * @return
	 */
	protected boolean exists(String name) {
		return Optional.ofNullable(WhenStatusEnum.checkStatus(name)).isPresent();
	}

}
