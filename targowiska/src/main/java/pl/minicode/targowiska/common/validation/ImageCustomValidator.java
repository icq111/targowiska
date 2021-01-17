package pl.minicode.targowiska.common.validation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
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

	private MultipartFile file;

	private Object target;

	@Autowired
	private NewsImageProperties newsProperties;

	@Autowired
	private SliderImageProperties sliderProperties;

	@Override
	public boolean supports(Class<?> clazz) {
		return BasicEntity.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		this.target = target;
		initValidateNewsImage();
		initValidateSliderImage();
		if (this.file.getSize() > getProperties().getSizeInBytes()) {
			errors.rejectValue("file", "upload.exceeded.file.size");
		}
		try {
			File file = convert(this.file);
			BufferedImage bimg = ImageIO.read(file);
			boolean isDimensionInCorrect = bimg.getWidth() < getProperties().getMinimumWidth();
			isDimensionInCorrect = isDimensionInCorrect || bimg.getHeight() < getProperties().getMinimumHeight();

			if (isDimensionInCorrect) {
				errors.rejectValue("file", "upload.error.wrong.file.dimensions");
			}

		} catch (Exception e) {
			errors.rejectValue("file", "upload.error.parsing.file");
		}

	}

	private void initValidateNewsImage() {
		if (this.target instanceof News) {
			News news = (News) target;
			this.file = news.getFile();
		}
	}

	private void initValidateSliderImage() {
		if (this.target instanceof Slider) {
			Slider slider = (Slider) target;
			this.file = slider.getFile();
		}
	}

	private ImageProperties getProperties() {
		if (this.target instanceof News) {
			return newsProperties;
		}
		if (this.target instanceof Slider) {
			return sliderProperties;
		}
		return null;
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
