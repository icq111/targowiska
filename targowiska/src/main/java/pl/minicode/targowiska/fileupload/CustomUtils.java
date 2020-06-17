package pl.minicode.targowiska.fileupload;

import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class CustomUtils {

	public static String getGeneratedFileName(MultipartFile file) {
//		int leftLimit = 97; // letter 'a'
//		int rightLimit = 122; // letter 'z'
//		int targetStringLength = 10;
//		Random random = new Random();
//		StringBuilder buffer = new StringBuilder(targetStringLength);
//		for (int i = 0; i < targetStringLength; i++) {
//			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
//			buffer.append((char) randomLimitedInt);
//		}
		UUID uuid = UUID.randomUUID();
		String generatedString = uuid.toString();
		String extension = getExtensionByStringHandling(file.getOriginalFilename()).get();
		return generatedString + extension;
	}
	
	public static Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".")));
	}
}
