package com.wezaam.withdrawal.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.entity.UserEntity;
/**
 * All the validations that could do on a user
 * @author dvicensnoguera
 *
 */
@Component
public class UserValidator {
	/**
	 * check if userEntity is not null
	 * @param userEntity
	 * @return
	 */
	protected boolean exists(UserEntity userEntity) {
		return Optional.ofNullable(userEntity).isPresent();
	}
}
