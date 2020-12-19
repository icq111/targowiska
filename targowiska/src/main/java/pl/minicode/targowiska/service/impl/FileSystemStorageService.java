package pl.minicode.targowiska.service.impl;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.fileupload.CustomUtils;
import pl.minicode.targowiska.fileupload.IFileSystemStorageService;
import pl.minicode.targowiska.fileupload.StoredFileInfo;
import pl.minicode.targowiska.gallery.ImageLayout;
import pl.minicode.targowiska.gallery.ImageType;

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

	public StoredFileInfo storeImage(MultipartFile file, ImageType imageType) {
		String generatedFileName = CustomUtils.getGeneratedFileName(file);
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
				InputStream minimalizedImage = inputStream;
				if(storedFileInfo.getImageLayout().isVertical()) {
					minimalizedImage = cropFromImage(file, storedFileInfo);
					minimalizedImage.close();
				} else {
					minimalizedImage = changeImageSize(file, storedFileInfo);					
					minimalizedImage.close();
				}
				
				Files.copy(minimalizedImage, location.resolve(storedFileInfo.getCleanFileName()+"-min."+storedFileInfo.getFileExtension()), StandardCopyOption.REPLACE_EXISTING);
				inputStream.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + generatedFileName, e);
		}
		return storedFileInfo;
	}
	
	private InputStream changeImageSize(MultipartFile file, StoredFileInfo storedFileInfo) throws IOException {
		BufferedImage imBuff = ImageIO.read(file.getInputStream());
		Integer width = new Integer(applicationProperty.getProperty("dynamic.images.min.image.width"));
		Integer height = new Integer(applicationProperty.getProperty("dynamic.images.min.image.height"));
		
		BufferedImage scalledImgBuff = Scalr.resize(imBuff, Mode.FIT_EXACT, width, height);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(scalledImgBuff, storedFileInfo.getFileExtension(), os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
		os.close();
		return new ByteArrayInputStream(os.toByteArray());
	}
	
	private InputStream cropFromImage(MultipartFile file, StoredFileInfo storedFileInfo) throws IOException {
		BufferedImage originalImgage = ImageIO.read(file.getInputStream());
		Integer width = new Integer(applicationProperty.getProperty("dynamic.images.min.image.width"));
		Integer height = new Integer(applicationProperty.getProperty("dynamic.images.min.image.height"));
		int imgWidth = storedFileInfo.getDimension().width;
		int imgHeight = storedFileInfo.getDimension().height;
		double x_coordinate = (imgWidth / 2) - (width / 2);
		double y_coordinate = (imgHeight / 2) - (height / 2);
		BufferedImage subImgage = originalImgage.getSubimage((int) x_coordinate, (int) y_coordinate, width, height);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(subImgage, storedFileInfo.getFileExtension(), os); // Passing: ​(RenderedImage im, String
																			// formatName, OutputStream output)
		os.close();
		return new ByteArrayInputStream(os.toByteArray());
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
		case CONTRACTOR:
			result = "contractor";
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
