package pl.minicode.targowiska.gallery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;

public class ImagesGalleryDto {

	private Page<ImageGallery> pageOfImages = Page.empty();
	private List<List<ImageGallery>> listOfImageRows = Collections.emptyList();
	private List<Integer> pageNumbers = Collections.emptyList();

	public static ImagesGalleryDto createImageGalleryDto(Page<ImageGallery> listOfImages) {
		ImagesGalleryDto dto = new ImagesGalleryDto(listOfImages);
		dto.devideImagedListToGrind();
		return dto;
	}

	public static ImagesGalleryDto createImageGalleryDtoForMainPage(Page<ImageGallery> listOfImages) {
		ImagesGalleryDto dto = new ImagesGalleryDto(listOfImages);
		dto.devideForMainPage();
		return dto;
	}

	public List<List<ImageGallery>> getImagesGrid() {
		return listOfImageRows;
	}

	public List<Integer> pagesNumbers() {
		int totalPages = pageOfImages.getTotalPages();
		if (totalPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}
		return pageNumbers;
	}

	public Page<ImageGallery> imagesPerPages() {
		return pageOfImages;
	}

	private ImagesGalleryDto(Page<ImageGallery> listOfImages) {
		if (!listOfImages.isEmpty()) {
			this.pageOfImages = listOfImages;
			this.listOfImageRows = new ArrayList<>();
			this.listOfImageRows.add(new ArrayList<>());
		}
	}

	private void devideImagedListToGrind() {
		AtomicInteger counter = new AtomicInteger();
		final int chunkSize = 4;
		for (ImageGallery image : getPureListOfImages()) {
			List<ImageGallery> row = listOfImageRows.get(counter.intValue());
			if (row.size() > 0 && row.size() % chunkSize == 0) {

				listOfImageRows.add(new ArrayList<>());
				counter.incrementAndGet();
			}
			listOfImageRows.get(counter.intValue()).add(image);

		}

	}

	private void devideForMainPage() {
		if (getPureListOfImages().size() == 7) {

			AtomicInteger counter = new AtomicInteger();
			int chunkSize = 3;
			for (ImageGallery image : getPureListOfImages()) {
				List<ImageGallery> row = listOfImageRows.get(counter.intValue());
				if (row.size() > 0 && row.size() % chunkSize == 0) {

					listOfImageRows.add(new ArrayList<>());
					counter.incrementAndGet();
					chunkSize = 4;
				}
				listOfImageRows.get(counter.intValue()).add(image);

			}

		}
	}

	private List<ImageGallery> getPureListOfImages() {
		return pageOfImages.getContent();
	}
}
