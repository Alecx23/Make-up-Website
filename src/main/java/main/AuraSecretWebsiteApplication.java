package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "obiecte")
@EnableJpaRepositories(basePackages = "Repositories")
@ComponentScan(basePackages = {"main","Services","Repositories","Controlare"})
public class AuraSecretWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuraSecretWebsiteApplication.class, args);
	}

}
