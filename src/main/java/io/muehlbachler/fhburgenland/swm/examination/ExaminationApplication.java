package io.muehlbachler.fhburgenland.swm.examination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main class for the Examination Application.
 */

@SpringBootApplication
@EntityScan("io.muehlbachler.fhburgenland.swm.examination.model")
@EnableJpaRepositories("io.muehlbachler.fhburgenland.swm.examination.repository")
public class ExaminationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaminationApplication.class, args);
	}

}
