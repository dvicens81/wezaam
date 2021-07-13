package com.wezaam.withdrawal.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "withdrawals")
@Table(name = "withdrawals")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class WithDrawalEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "payment_method_id")
	private Long paymentMethodId;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "status")
	private String status;
	@Column(name = "created_at")
	private LocalDateTime createdAt; //when register is storage on database
	@Column(name = "scheduled_at")
	private LocalDate scheduledAt; //when register is scheduled (now or on the future)
	@Column(name = "executed_at")
	private LocalDateTime executedAt; //when register is processed
	

}
