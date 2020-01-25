package pl.minicode.targowiska.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.properties.StorageProperties;



@Service
public class FileSystemStorageService {

	@Autowired
	StorageProperties properties;

	private Path uploadLocation;

	@PostConstruct
	public void init() {
		this.uploadLocation = Paths.get(properties.getLocation() + File.separator + "news") ;
		try {
			Files.createDirectories(uploadLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage", e);
		}
	}

	public void store(MultipartFile file, String generatedFileName) {
//		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file " + generatedFileName);
			}

			// This is a security check
			if (generatedFileName.contains("..")) {
				throw new RuntimeException(
						"Cannot store file with relative path outside current directory " + generatedFileName);
			}

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.uploadLocation.resolve(generatedFileName), StandardCopyOption.REPLACE_EXISTING);

			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + generatedFileName, e);
		}
	}

//	public Resource loadAsResource(String filename) {
//		try {
//			Path file = uploadLocation.resolve(filename);
//			Resource resource = new UrlResource(file.toUri());
//			if (resource.exists() || resource.isReadable()) {
//				return resource;
//			} else {
//				throw new RuntimeException("Could not read file: " + filename);
//			}
//		} catch (MalformedURLException e) {
//			throw new RuntimeException("Could not read file: " + filename, e);
//		}
//	}
//
//	public List<Path> listSourceFiles(Path dir) throws IOException {
//		List<Path> result = new ArrayList<>();
//		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{txt}")) {
//			for (Path entry : stream) {
//				result.add(entry);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return result;
//	}
}
