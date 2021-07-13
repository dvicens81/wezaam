package com.wezaam.withdrawal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wezaam.withdrawal.dto.UserDto;
import com.wezaam.withdrawal.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
	
	private final UserService service;
	
	@GetMapping()
	public ResponseEntity<List<UserDto>> findAllUsers() {
		log.debug("Find all users");
		return new ResponseEntity<>(service.findAllUsers(), HttpStatus.OK);
	}

}
