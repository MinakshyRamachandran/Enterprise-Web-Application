
public class DailySales{

	String OrderDate;
	Double SalesAmount;
	
	public DailySales(){
	}
	
	public DailySales(String OrderDate, Double salesAmount){
		this.OrderDate = OrderDate;
		this.SalesAmount = salesAmount;
	}
	
	public void setOrderDate(String OrderDate)
	{
		this.OrderDate = OrderDate;
	}
	
	public String getOrderDate(){
		return this.OrderDate;
	}
	public void setProductPrice(Double salesAmount)
	{
		this.SalesAmount = salesAmount;
	}
	
	public Double getProductPrice(){
		return this.SalesAmount;
	}
}