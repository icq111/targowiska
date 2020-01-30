package pl.minicode.targowiska.service;

import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.type.ImageType;

public interface IFileSystemStorageService {

	void store(MultipartFile file, String generatedFileName, ImageType imageType);
}
