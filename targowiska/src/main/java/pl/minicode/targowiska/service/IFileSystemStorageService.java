package pl.minicode.targowiska.service;

import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.domain.dto.StoredFileInfo;
import pl.minicode.targowiska.type.ImageType;

public interface IFileSystemStorageService {

	StoredFileInfo storeImage(MultipartFile file, String generatedFileName, ImageType imageType);
}
