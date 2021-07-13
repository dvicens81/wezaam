package com.wezaam.withdrawal.service.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
/**
 * Factory class to manage the different service types.
 *  
 * @author dvicensnoguera
 *
 */
@Service
@RequiredArgsConstructor
public class WithDrawalServiceFactory {
	
	private final List<WithDrawalService> listWithDrawalService;

	private Map<String, WithDrawalService> cacheOperationMap = new HashMap<>();

	@PostConstruct
	public void initSearchMedicationOperationCache() {
		listWithDrawalService.stream().forEach(operation -> cacheOperationMap.put(operation.getType(), operation));
	}

	public WithDrawalService getSearchMedicationOperation(String type) {
		return Optional.ofNullable(type).filter(operationType -> cacheOperationMap.containsKey(operationType)).map(operationType -> cacheOperationMap
		        .get(operationType)).orElseThrow(() -> new RuntimeException("Unsupported WithDrawalConvert (" + type + ")"));
	}

}
