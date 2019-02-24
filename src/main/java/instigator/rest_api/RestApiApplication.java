package instigator.rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"instigator.rest_api.QA"})
public class RestApiApplication {
	
	/**
	 * Software entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
		System.out.println("--- Server initialized");
	}
}
