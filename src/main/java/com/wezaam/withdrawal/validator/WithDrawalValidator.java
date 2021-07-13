package com.wezaam.withdrawal.validator;

import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.UserEntity;
import com.wezaam.withdrawal.service.UserService;

import lombok.AllArgsConstructor;
/**
 * This class validate all the JSON BODY request send it by the user
 * on withdrawal endpoint
 * 
 * @author dvicensnoguera
 *
 */
@AllArgsConstructor
@Component
public class WithDrawalValidator extends AbstractWithDrawalValidator {
	
	private final UserValidator userValidator;
	private final WhenValidator whenValidator;
	private final UserService userService;
	private final PaymentMethodValidator paymentValidator;
	
	/**
	 * Validate json body request
	 * 
	 * @param request
	 */
	public void validateBodyRequest(WithDrawalDto request, long userId) {
		//TODO add in a list the errors and send on response all in one time
		UserEntity userEntity = userService.getUserById(userId);
		//Check User is OK
		boolean isValid = userValidator.exists(userEntity);		
		checkBooleanOrElseThow(isValid, "User not exists " + userId);
		//Check type of the withdrawal is OK
		isValid = whenValidator.exists(request.getWhen().getType());
		checkBooleanOrElseThow(isValid, "Type of the withdrawal not exists (when.type) " + request.getWhen().getType());
		isValid = paymentValidator.exists(userEntity, request.getPaymentMethod().getId());
		checkBooleanOrElseThow(isValid, "PaymentMethod is not valid " + request.getPaymentMethod().getId());

		//TODO If is SCHEDULED type, check if the date has the yyyy-MM-dd format and is future date
		//TODO validate amount is not negative value
	}

}
