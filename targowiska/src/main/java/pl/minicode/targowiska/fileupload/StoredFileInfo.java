package pl.minicode.targowiska.fileupload;

import java.awt.Dimension;
import java.nio.file.Path;

import pl.minicode.targowiska.gallery.ImageLayout;

public class StoredFileInfo {

	private Dimension dimension;
	private Path filePath;
	private String fileName;
	private ImageLayout imageLayout;

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}
	
	public String getFileExtension() {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	public String getCleanFileName() {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	
	public String getMinFileName() {
		return getCleanFileName() + "-min." + getFileExtension();
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ImageLayout getImageLayout() {
		return imageLayout;
	}

	public void setImageLayout(ImageLayout imageLayout) {
		this.imageLayout = imageLayout;
	}

	@Override
	public String toString() {
		return "StoredFileInfo [dimension=" + dimension + ", filePath=" + filePath + ", fileName=" + fileName
				+ ", imageLayout=" + imageLayout + "]";
	}
}
