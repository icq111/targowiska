package pl.minicode.targowiska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import pl.minicode.targowiska.properties.StorageProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(StorageProperties.class)
public class TargowiskaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargowiskaApplication.class, args);
	}

}
