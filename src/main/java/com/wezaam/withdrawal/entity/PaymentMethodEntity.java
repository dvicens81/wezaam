package com.wezaam.withdrawal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "payment_methods")
@Table(name = "payment_methods")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class PaymentMethodEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "name")
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

}
