package pl.minicode.targowiska.utils;

import java.util.Random;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

public class CustomUtils {

	public static String getGeneratedFileName(MultipartFile file) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
		return generatedString;
	}
}
