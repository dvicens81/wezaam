package com.wezaam.withdrawal.service.factory;

import java.util.List;

import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.entity.WithDrawalEntity;

/**
 * Interface class methods to withdrawal 
 * 
 * @author dvicensnoguera
 *
 */
public interface WithDrawalService {
	String getType();	
	TransactionDto executeWithDrawal(WithDrawalDto withDrawalDto, long userId);	
	WithDrawalEntity save(WithDrawalEntity entity);
	void sendToQueue(List<WithDrawalEntity> listToSend);
}
