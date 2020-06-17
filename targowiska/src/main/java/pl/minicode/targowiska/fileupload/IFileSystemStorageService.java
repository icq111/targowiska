package pl.minicode.targowiska.fileupload;

import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.gallery.ImageType;

public interface IFileSystemStorageService {

	StoredFileInfo storeImage(MultipartFile file, String generatedFileName, ImageType imageType);
}
