package pl.minicode.targowiska.gallery;

public enum ImageType {

	NEWS, OFFER, GALLERY, PRODUCT, PRODUCT_CATEGORY, CONTRACTOR;

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
}
