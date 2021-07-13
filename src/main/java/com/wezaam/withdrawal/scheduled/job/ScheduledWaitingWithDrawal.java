package com.wezaam.withdrawal.scheduled.job;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wezaam.withdrawal.service.WithDrawalScheduledService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Scheduled task to execute every day at 1 AM
 * @author dvicensnoguera
 *
 */
@AllArgsConstructor
@Component
@Slf4j
public class ScheduledWaitingWithDrawal {
	
	private final WithDrawalScheduledService scheduledService;
	@Scheduled(cron="0 0 1 * * *")
    public void scheduleFixedRateTaskAsync() {
		log.info("executing scheduleFixedRateTaskAsync at {}", LocalDate.now());
		scheduledService.getWithDrawalWaiting(LocalDate.now());
    }
	
	//TODO create new scheduled to move status like completed withdrawal on withdrawal_history table

}
