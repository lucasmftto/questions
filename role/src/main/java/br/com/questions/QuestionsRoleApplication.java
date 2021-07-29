package br.com.questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuestionsRoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionsRoleApplication.class, args);
	}

}
