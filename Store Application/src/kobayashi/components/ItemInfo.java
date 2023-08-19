package kobayashi.components;

public class ItemInfo {
	public String barcode, productName, categoryName, imgPath;
	public float productPrice;
	public int id, quantityStock, categoryId;
	
	public ItemInfo(String barcode, String productName, float productPrice, int quantityStock, int categoryId, String imgPath, String categoryName) {
		this.barcode = barcode;
		this.productName = productName;
		this.categoryName = categoryName;
		this.productPrice = productPrice;
		this.categoryId = categoryId;
		this.quantityStock = quantityStock;
		this.imgPath = imgPath;
	}
	
}
