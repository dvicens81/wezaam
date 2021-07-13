package com.wezaam.withdrawal.async;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.wezaam.withdrawal.entity.WithDrawalEntity;

@EnableAsync
@Service
public class WithDrawalEventService {
	
	@Async
	public void send(List<WithDrawalEntity> entity) {
		/*
		 * ID == TRANSACTIONID ? I took the ID of withdrawal like transactionId.
		 *  
		 * The idea is use RabbitMQ using RPC pattern.
		 * 
		 * It will have a Producer, Consumer and two queues.
		 * 
		 * Producer send a message to a specific queue with entity (every item in one message) (information necessary), and one attribute replyTo saying the queue to answer.
		 * Consumer read the message and do the transaction. 
		 * and return the message to the queue that was informed before and update the data on database (informing "executed_at" and "status" like complete) 
		 * 
		 * Producer receive the message and send a response to the user that the transaction is done.
		 * 
		 * With this solution evict the connectivity problem, because if happens something the message is in the queue and then when the connectivity comes back, the application read the queue.
		 * 
		 * In other hand all the transaction completed we could put on "historic_withdrawal" table and then the actual "withdrawal" only has the data that is needed to execute.
		 * This last part we can do on scheduled job.
		 */
	}

}
