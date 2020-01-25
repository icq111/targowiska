package pl.minicode.targowiska;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.unit.DataSize;

import pl.minicode.targowiska.properties.StorageProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@EnableConfigurationProperties(StorageProperties.class)
public class TargowiskaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TargowiskaApplication.class, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TargowiskaApplication.class);
    }

	@Bean
	  public MultipartConfigElement multipartConfigElement() {
	      MultipartConfigFactory factory = new MultipartConfigFactory();
	      
	      factory.setMaxFileSize(DataSize.ofKilobytes(180));
	      return factory.createMultipartConfig();
	  }
}
