package com.example.consumer;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@SqsListener(queueNames = "test-topic")
	void listener(String message) {
		System.out.println("Received Message in group foo: " + message);
	}

}
