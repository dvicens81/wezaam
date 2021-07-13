package com.wezaam.withdrawal.enums;

import java.util.Arrays;

/**
 * Available status when is executed the withdrawal
 * 
 * For this test I created this enum, but it could consider insert this status on database
 * 
 * @author dvicensnoguera
 *
 */
public enum WhenStatusEnum {

	ASAP,
	SCHEDULED;
	
	public static WhenStatusEnum checkStatus(String name) {
		return Arrays.stream(values())
                .filter(e -> e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
	}
}
