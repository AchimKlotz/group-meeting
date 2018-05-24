package org.groupevents;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoreApplication {

	public static void main(String[] args) {
	Locale.setDefault(Locale.GERMANY);
		SpringApplication.run(CoreApplication.class, args);
	}
	
}
