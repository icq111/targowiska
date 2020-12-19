package pl.minicode.targowiska.contractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;

public class ContractorDto {
	private Page<Contractor> contractorPages = Page.empty();
	private List<List<Contractor>> contractorRows = Collections.emptyList();
	private List<Integer> pageNumbers = Collections.emptyList();

	public static ContractorDto createContractorDto(Page<Contractor> contractorsPage) {
		return new ContractorDto(contractorsPage);
	}

	public List<List<Contractor>> getContractorRows() {
		return contractorRows;
	}

	public List<Integer> pagesNumbers() {
		int totalPages = contractorPages.getTotalPages();
		if (totalPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}
		return pageNumbers;
	}

	public Page<Contractor> rowsPerPages() {
		return contractorPages;
	}

	private ContractorDto(Page<Contractor> contractorPages) {
		if (!contractorPages.isEmpty()) {
			this.contractorPages = contractorPages;
			this.contractorRows = new ArrayList<>();
			this.contractorRows.add(new ArrayList<>());
		}
		devideContractorListToGrind();
	}

	private void devideContractorListToGrind() {
		AtomicInteger counter = new AtomicInteger();
		final int chunkSize = 3;
		for (Contractor image : getContractorList()) {
			List<Contractor> row = contractorRows.get(counter.intValue());
			if (row.size() > 0 && row.size() % chunkSize == 0) {

				contractorRows.add(new ArrayList<>());
				counter.incrementAndGet();
			}
			contractorRows.get(counter.intValue()).add(image);

		}

	}

	private List<Contractor> getContractorList() {
		return contractorPages.getContent();
	}
}
