package pl.minicode.targowiska.service.impl;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.domain.dto.StoredFileInfo;
import pl.minicode.targowiska.service.IFileSystemStorageService;
import pl.minicode.targowiska.type.ImageLayout;
import pl.minicode.targowiska.type.ImageType;

@Service
public class FileSystemStorageService implements IFileSystemStorageService {

	@Autowired
	private Environment applicationProperty;

	private Path uploadLocation;

	@PostConstruct
	public void init() {
		this.uploadLocation = Paths.get(applicationProperty.getProperty("dynamic.images.base") + File.separator) ;
		try {
			Files.createDirectories(uploadLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage", e);
		}
	}

	public StoredFileInfo storeImage(MultipartFile file, String generatedFileName, ImageType imageType) {
		StoredFileInfo storedFileInfo = null;
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
				Path location = Paths.get(this.uploadLocation.toString() + File.separator + getStoragePlace(imageType));
				Files.createDirectories(location);
				Files.copy(inputStream, location.resolve(generatedFileName), StandardCopyOption.REPLACE_EXISTING);
				storedFileInfo = getSroredFileInfo(location, generatedFileName);
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + generatedFileName, e);
		}
		return storedFileInfo;
	}
	
	private StoredFileInfo getSroredFileInfo(Path location, String fileName) throws IOException {
		StoredFileInfo storedFileInfo = new StoredFileInfo();
		File f = location.resolve(fileName).toFile();
		BufferedImage bimg = ImageIO.read(f);
		storedFileInfo.setFilePath(location);
		storedFileInfo.setDimension(new Dimension(bimg.getWidth(), bimg.getHeight()));
		
		ImageLayout imageLayout = (bimg.getWidth() > bimg.getHeight()) ? ImageLayout.HORIZONTAL : ImageLayout.VERTICAL;
		storedFileInfo.setImageLayout(imageLayout);
		storedFileInfo.setFileName(f.getName());
		return storedFileInfo;
	}
	
	private String getStoragePlace(ImageType type) {
		String result = "";
		switch (type) {
		case NEWS:
			result = "news";
			break;
		case GALLERY:
			result = "gallery";
			break;
		case OFFER:
			result = "offer";
			break;
		case PRODUCT:
			result = "product";
			break;
		default:
			break;
		}
		return result;
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
