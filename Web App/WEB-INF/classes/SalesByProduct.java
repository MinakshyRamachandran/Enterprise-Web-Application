
public class SalesByProduct{

	String ProductName;
	int ItemsSold;
	Double ProductPrice;
	
	public SalesByProduct(){
	}
	
	public SalesByProduct(String productName, int itemsSold, Double productPrice){
		this.ProductName = productName;
		this.ItemsSold = itemsSold;
		this.ProductPrice = productPrice;
	}
	
	public void setProdcutName(String productName)
	{
		this.ProductName = productName;
	}
	
	public String getProductName(){
		return this.ProductName;
	}
	public void setItemsSold(int itemsSold)
	{
		this.ItemsSold = itemsSold;
	}
	
	public int getItemsSold(){
		return this.ItemsSold;
	}
	public void setProductPrice(Double productPrice)
	{
		this.ProductPrice = productPrice;
	}
	
	public Double getProductPrice(){
		return this.ProductPrice;
	}
}