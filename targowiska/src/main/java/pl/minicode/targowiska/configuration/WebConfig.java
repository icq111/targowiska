package pl.minicode.targowiska.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment applicationProperty	;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    String rootPath = applicationProperty.getProperty("dynamic.images.base");
	    String imagePath = "file:"+rootPath + File.separator;
	    System.out.println(imagePath);
	    registry.addResourceHandler("/resources/**").addResourceLocations("resources/");
	    registry.addResourceHandler("/tmpFiles/**").addResourceLocations(imagePath);
	}
	
}