
public class Order {
private String userid;
private Integer ordernumber;
private String itemId;
private Integer price;
private Integer quantity;
private String deliveryDate;
private String shippingAddress;
//private String itemUrl;
private String address;
private String city;
private String state;
private String zip;
private String country;
private String name;
private String cardNumber;
private String status;
private String email;
private int flag;
//private Integer orderamt;

public Order(){

}

public Order(String userid, Integer ordernumber, String itemId, Integer price, Integer quantity, String deliveryDate,
		String shippingAddress, String address, String city, String state, String zip, String country, String name, String cardNumber, String email)//, Integer orderamt)
		{
	super();
	this.userid = userid;
	this.ordernumber = ordernumber;
	this.itemId = itemId;
	this.price = price;
	this.quantity = quantity;
	this.deliveryDate = deliveryDate;
	this.shippingAddress = shippingAddress;
	this.address = address;
	this.city = city;
	this.state = state;
	this.zip = zip;
	this.country = country;
	this.name = name;
	this.cardNumber = cardNumber;
	this.email= email;
	this.flag= flag;
	//this.orderamt= orderamt;
	//log.d("Test in Order class  "+userid +" "+ ordernumber+ " "+itemId+" "+ price+" "+ quantity+" "+ deliveryDate+" "+ shippingAddress+" "+ address+" "+ city+" "+ state+" "+ zip+" "+ country+" "+ name+" "+ cardNumber);
}

public String getUserid() {	return userid;}
public void setUserid(String userid) {	this.userid = userid;}
public Integer getOrdernumber() {	return ordernumber;}
public void setOrdernumber(int ordernumber) {	this.ordernumber = ordernumber;}
public String getItemId() {	return itemId;}
public void setItemId(String itemId) {	this.itemId = itemId;}
public Integer getPrice() {	return price;}
public void setPrice(Integer price) {	this.price = price;}
public Integer getQuantity() {	return quantity;}
public void setQuantity(Integer quantity) {	this.quantity = quantity;}
public String getDeliveryDate() {	return deliveryDate;}
public void setDeliveryDate(String deliveryDate) {	this.deliveryDate = deliveryDate;}
public String getShippingAddress() {	return shippingAddress;}
public void setShippingAddress(String shippingAddress) {	this.shippingAddress = shippingAddress;}
public String getCardNumber() {
	return cardNumber;
}
/*public Integer getOrderAmt() {
	return orderamt;
}*/
public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
}
/*public void setOrderAmt(Integer orderamt) {
	this.orderamt = orderamt;
}*/
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public void setAddress(String address) {
	this.address = address;
}
public void setCity(String city) {
	this.city = city;
}
public void setState(String state) {
	this.state = state;
}
public void setZip(String zip) {
	this.zip = zip;
}
public void setCountry(String country) {
	this.country = country;
}
public String getAddress() {
	return address;
}
public String getCity() {
	return city;
}
public String getState() {
	return state;
}
public String getZip() {
	return zip;
}
public String getCountry() {
	return country;
}

public String getOrderStatus() {
	return status;
}
public void setOrderStatus(String name) {
	this.status = status;
}


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getFlag(){
	return this.flag;
}

public void setFlag(int flag){
	this.flag = flag;
}


}
