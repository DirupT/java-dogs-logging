package com.lambdaschool.projectrestdogs.services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener
{

	@RabbitListener(queues = ProjectrestdogsApplication.DOGS_ENDPOINT)
	public void receiveDogsMessage(String msg)
	{
		System.out.println("Received message from queue '" + ProjectrestdogsApplication.DOGS_ENDPOINT + "': " + msg);

	}

	@RabbitListener(queues = ProjectrestdogsApplication.DOGS_BREED_ENDPOINT)
	public void receiveDogsBreedMessage(String msg)
	{
		System.out.println("Received message from queue '" + ProjectrestdogsApplication.DOGS_BREED_ENDPOINT + "': " + msg);

	}

}
