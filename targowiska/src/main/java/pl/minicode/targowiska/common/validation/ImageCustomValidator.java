package pl.minicode.targowiska.common.validation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.common.ImageProperties;
import pl.minicode.targowiska.entity.BasicEntity;
import pl.minicode.targowiska.news.News;
import pl.minicode.targowiska.slider.Slider;

@Component
public class ImageCustomValidator implements Validator {
	
	private ImageProperties properties;
	
	private MultipartFile file;

	@Override
	public boolean supports(Class<?> clazz) {
		return BasicEntity.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		initValidateNewsImage(target);
		initValidateSliderImage(target);
		if (this.file.getSize() > this.properties.getSizeInKB()) {
			errors.rejectValue("file", "upload.exceeded.file.size");
		}
		try {
			File file = convert(this.file);
			BufferedImage bimg = ImageIO.read(file);
			boolean isDimensionCorrect = bimg.getWidth() > this.properties.getMinimumWidth();
			isDimensionCorrect = isDimensionCorrect && bimg.getHeight() > this.properties.getMinimumHeight();
			
			if(! isDimensionCorrect) {
				errors.rejectValue("file", "upload.error.wrong.file.dimensions");
			}
			
			
		} catch (Exception e) {
			errors.rejectValue("file", "upload.error.parsing.file");
		}		

	}
	
	
	
	private void initValidateNewsImage(Object target) {
		if(target instanceof News) {
			News news = (News) target;
			this.properties = NewsImageProperties.createProperties();
			this.file = news.getFile();
		}
	}
	
	private void initValidateSliderImage(Object target) {
		if(target instanceof Slider) {
			Slider slider = (Slider) target;
			this.properties = SliderImageProperties.createProperties();
			this.file = slider.getFile();
		}
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
