package com.wezaam.withdrawal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wezaam.withdrawal.dto.TransactionDto;
import com.wezaam.withdrawal.dto.WithDrawalDto;
import com.wezaam.withdrawal.service.factory.WithDrawalOperationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/withdrawals")
@Slf4j
public class WithDrawalController {
	
	private final WithDrawalOperationService service;
	
	@PostMapping("/users/{id}")
	public ResponseEntity<TransactionDto> executeWithDrawal(@RequestBody WithDrawalDto withDrawalDto, @PathVariable long id) {
		log.debug("Execute withDrawal with user {}", id);
		return new ResponseEntity<>(service.executeWithDrawal(withDrawalDto, id), HttpStatus.OK);
	}

}
