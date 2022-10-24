package com.project.gachon;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GachonApplicationTests {

	@Test
	void contextLoads() {
		LocalDateTime expired = LocalDateTime.now().plusDays(5);
		System.out.println(expired);

		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);

		System.out.println(ChronoUnit.DAYS.between(now, expired));
	}

}
