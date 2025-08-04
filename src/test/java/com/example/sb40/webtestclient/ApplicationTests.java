package com.example.sb40.webtestclient;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static reactor.core.publisher.Mono.just;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	private WebTestClient client;

	@Test
	void contextLoads() {
	}

	@Test
	void healthy() {
		client.get()
			.uri("/actuator/health")
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK)
			.expectBody(String.class)
			.consumeWith(response -> LOG.info("Health response: {}", response.getResponseBody()));
	}

	@Test
	void stringTest() {
		client.get()
			.uri("/test/14")
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK)
			.expectBody(String.class)
			.consumeWith(response -> LOG.info("Test response: {}", response.getResponseBody()));
	}

	@Test
	void createCompositeProductTest() {
		var productId = 5;
		ProductAggregate composite = new ProductAggregate(productId, "name", 1); // , null, null, null);
		LOG.info("### sending productId: {}", composite.getProductId());

		int result = client.post()
				.uri("/product-composite")
				.body(just(composite), ProductAggregate.class)
				.exchange()
				.expectStatus().isEqualTo(ACCEPTED)
				.expectBody(Integer.class).returnResult().getResponseBody();
		LOG.info("### test result: {}", result);

		assertEquals(productId, result);
	}
}
