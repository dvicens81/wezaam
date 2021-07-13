package com.wezaam.withdrawal.service;

import java.util.List;

import com.wezaam.withdrawal.dto.UserDto;
import com.wezaam.withdrawal.entity.UserEntity;

public interface UserService {
	
	UserEntity getUserById(long userId);
	
	List<UserDto> findAllUsers();
}
