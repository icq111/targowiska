package pl.minicode.targowiska.domain.dto;

import java.awt.Dimension;
import java.nio.file.Path;

public class StoredFileInfo {

	private Dimension dimension;
	private Path filePath;
	private String fileName;
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
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "StoredFileInfo [dimension=" + dimension + ", filePath=" + filePath + ", fileName=" + fileName + "]";
	}
}
