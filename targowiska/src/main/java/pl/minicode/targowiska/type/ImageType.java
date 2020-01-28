package pl.minicode.targowiska.type;

public enum ImageType {

	NEWS, OFFER, GALLERY, PRODUCT;

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
}
