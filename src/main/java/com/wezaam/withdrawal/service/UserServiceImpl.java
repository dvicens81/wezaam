package com.wezaam.withdrawal.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.convert.UserEntityToUserDtoConvert;
import com.wezaam.withdrawal.dto.UserDto;
import com.wezaam.withdrawal.entity.UserEntity;
import com.wezaam.withdrawal.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserEntityToUserDtoConvert convertEntityToDto;

	/**
	 * get user by id
	 * return an entity.
	 */
	//TODO This method should be cached. To not access every time to database
	@Override
	public UserEntity getUserById(long userId) {
		return userRepository.getUserById(userId);
	}
	
	//TODO Return the results pageables
	@Override
	public List<UserDto> findAllUsers() {
		List<UserEntity> listUsers = userRepository.findAll();
		return Optional.ofNullable(listUsers)
						.filter(CollectionUtils::isNotEmpty)
						.map(convertEntityToDto::convertList)
						.orElse(null);
	}

}
