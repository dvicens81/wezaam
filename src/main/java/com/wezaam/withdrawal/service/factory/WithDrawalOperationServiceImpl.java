package com.wezaam.withdrawal.service.factory;

import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;

import lombok.AllArgsConstructor;
/**
 * 
 * This class dispatch to specific service (asap or scheduled)
 * 
 * @author dvicensnoguera
 *
 */
@Service
@AllArgsConstructor
public class WithDrawalOperationServiceImpl implements WithDrawalOperationService {
	
	//factory
	private final WithDrawalServiceFactory factory;
	/**
	 * Execute specific service depending the type informed on dto.when.type 
	 */
	@Override
	public TransactionDto executeWithDrawal(WithDrawalDto dto, long userId) {
		WithDrawalService service = factory.getSearchMedicationOperation(dto.getWhen().getType().toUpperCase());
		return service.executeWithDrawal(dto, userId);
	}

}
