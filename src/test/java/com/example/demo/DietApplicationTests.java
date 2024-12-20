package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DietApplication.class)
@EntityScan(basePackages = "com.example.demo.domain")
class DietApplicationTests {

	@Test
	void contextLoads() {
	}

}
