package kobayashi.components;

public class SalesInfo {

	public String date, sellername, paymentMethod;
	public float totalValue, valueReceived, changeGiven;
	public int  saleId, sellerId;
	
	public SalesInfo(int saleId,String date, float totalValue, float valueReceived, float changeGiven, int sellerId, String paymentMethod, String sellername) {
		
		this.date = date;
		this.totalValue = totalValue;
		this.valueReceived = valueReceived;
		this.changeGiven = changeGiven;
		this.saleId = saleId;
		this.sellerId = sellerId;
		this.paymentMethod = paymentMethod;
		this.sellername = sellername;
		
	}
	
	
}
