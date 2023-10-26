package com.example.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

public class ProducerApplicationTests {

	public static void main(String[] args) {
		SpringApplication.from(ProducerApplication::main)
				.with(ContainerConfiguration.class)
				.run(args);
	}

	@TestConfiguration
	static class ContainerConfiguration {

		@Bean
		LocalStackContainer localstackContainer(DynamicPropertyRegistry registry) {
			LocalStackContainer localStackContainer = new LocalStackContainer(DockerImageName.parse("localstack/localstack:2.3.2"))
					.withReuse(true);
			registry.add("spring.cloud.aws.credentials.access-key", localStackContainer::getAccessKey);
			registry.add("spring.cloud.aws.credentials.secret-key", localStackContainer::getSecretKey);
			registry.add("spring.cloud.aws.region.static", localStackContainer::getRegion);
			registry.add("spring.cloud.aws.endpoint", () -> localStackContainer.getEndpointOverride(LocalStackContainer.Service.SQS));
			return localStackContainer;
		}

	}

}
