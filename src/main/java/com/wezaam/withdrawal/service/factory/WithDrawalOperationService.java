package com.wezaam.withdrawal.service.factory;

import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;

public interface WithDrawalOperationService {
	
	TransactionDto executeWithDrawal(WithDrawalDto dto, long userId);

}
