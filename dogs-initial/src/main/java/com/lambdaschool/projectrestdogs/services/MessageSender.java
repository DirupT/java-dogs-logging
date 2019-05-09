package com.lambdaschool.projectrestdogs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageSender
{
	private RabbitTemplate rt;
	private static final Logger logger = LoggerFactory.getLogger(MessageSource.class);

	public MessageSender(RabbitTemplate rt)
	{
		this.rt = rt;
	}

	public void sendMessage(String queue, String message)
	{
		logger.info(message);
		rt.convertAndSend(queue, message);
	}
}
