package com.wezaam.withdrawal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wezaam.withdrawal.entity.UserEntity;
/**
 * User Repository
 * 
 * @author dvicensnoguera
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query(value="SELECT ID, NAME FROM USERS WHERE ID=?1",
			nativeQuery=true)
	UserEntity getUserById(long userId);

}
