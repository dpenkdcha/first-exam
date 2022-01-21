package com.scott.betaexam;

import com.scott.betaexam.controllers.UserController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BetaExamApplicationTests {

	@Autowired
	UserController userController;

	@Test
	void contextLoads() {
		Assertions.assertThat(userController).isNotNull();
	}

}
