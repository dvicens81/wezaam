package com.wezaam.withdrawal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wezaam.withdrawal.entity.WithDrawalEntity;

public interface WithDrawalRepository extends JpaRepository<WithDrawalEntity, Long> {

	@Query(value="SELECT ID, USER_ID, PAYMENT_METHOD_ID, STATUS, CREATED_AT, EXECUTED_AT, SCHEDULED_AT, AMOUNT " +
				 "FROM WITHDRAWALS " + 
				 "WHERE STATUS = ?1 " + 
				 "AND SCHEDULED_AT < ?2",
			nativeQuery=true)
	List<WithDrawalEntity> getWithDrawalByStatusAndLessScheduledAtThanSpecificDate(String status, LocalDate scheduledAt);
}
