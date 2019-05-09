package com.lambdaschool.projectrestdogs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class ProjectrestdogsApplication
{
	public static final String EXCHANGE_NAME = "LambdaServer";
	public static final String DOGS_ENDPOINT = "DOGS_ENDPOINT";
	public static final String DOGS_BREED_ENDPOINT = "DOGS_BREED_ENDPOINT";

	public static DogList ourDogList;

	public static void main(String[] args)
	{
		ourDogList = new DogList();
		SpringApplication.run(ProjectrestdogsApplication.class, args);
	}

	@Bean
	public TopicExchange appExchange()
	{
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue dogsEndpoint()
	{
		return new Queue(DOGS_ENDPOINT);
	}

	@Bean
	public Binding declareBindingDogsEndpoint()
	{
		return BindingBuilder.bind(dogsEndpoint()).to(appExchange()).with(DOGS_ENDPOINT);
	}

	@Bean
	public Queue dogsBreedEndpoint()
	{
		return new Queue(DOGS_BREED_ENDPOINT);
	}

	@Bean
	public Binding declareBindingDogsBreedEndpoint()
	{
		return BindingBuilder.bind(dogsBreedEndpoint()).to(appExchange()).with(DOGS_BREED_ENDPOINT);
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}
}

