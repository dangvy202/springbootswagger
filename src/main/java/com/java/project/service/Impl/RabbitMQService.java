package com.java.project.service.Impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void messageMQResponse(String nomaFila, Object onj) {
		this.rabbitTemplate.convertAndSend(nomaFila,onj);
	}
}
