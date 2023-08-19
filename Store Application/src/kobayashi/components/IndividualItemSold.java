package kobayashi.components;

public class IndividualItemSold {
	
	public int quantitySold, mainSaleId;
	public String productBarcode, productName, categoryName;
	public float productPrice;
	
	public IndividualItemSold(String productBarcode, String productName, String categoryName, float productPrice, int quantitySold, int mainSaleId) {
		
		this.quantitySold = quantitySold;
		this.mainSaleId = mainSaleId;
		this.productBarcode = productBarcode;
		this.productName = productName;
		this.categoryName = categoryName;
		this.productPrice  = productPrice;
		
	}
	

}
