
public class ProductInfo{

	String ProductName;
	int Stock;
	Double ProductPrice;
	boolean IsOnSale;
	
	public ProductInfo(){
	}
	
	public ProductInfo(String productName, int stock, Double productPrice, boolean isOnSale){
		this.ProductName = productName;
		this.Stock = stock;
		this.ProductPrice = productPrice;
		this.IsOnSale = isOnSale;
		
	}
	
	public void setIsOnSale(boolean isOnSale)
	{
		this.IsOnSale = isOnSale;
	}
	
	public boolean getIsOnSale(){
		return this.IsOnSale;
	}

	
	public void setProdcutName(String productName)
	{
		this.ProductName = productName;
	}
	
	public String getProductName(){
		return this.ProductName;
	}
	public void setItemsStock(int stock)
	{
		this.Stock = stock;
	}
	
	public int getItemsStock(){
		return this.Stock;
	}
	public void setProductPrice(Double productPrice)
	{
		this.ProductPrice = productPrice;
	}
	
	public Double getProductPrice(){
		return this.ProductPrice;
	}
}