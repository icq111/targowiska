package pl.minicode.targowiska.gallery;

public enum ImageType {

	NEWS, OFFER, GALLERY, PRODUCT, PRODUCT_CATEGORY, CONTRACTOR, MAIN_GUI;

	public boolean isNewsType() {
		return this.equals(NEWS);
	}

	public boolean isOffersType() {
		return this.equals(OFFER);
	}

	public boolean isGalleryType() {
		return this.equals(GALLERY);
	}

	public boolean isProductType() {
		return this.equals(PRODUCT);
	}
	
	public boolean isContractorType() {
		return this.equals(CONTRACTOR);
	}
	
	public boolean isMainGuiType() {
		return this.equals(MAIN_GUI);
	}
	
	
}
