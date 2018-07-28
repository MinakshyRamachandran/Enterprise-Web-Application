import java.sql.*;
import java.util.*;
import java.sql.CallableStatement;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MySQLDataStoreUtilities {

	public static Connection conn = null;
	
	public static int getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/falcon","root","Qwerty1!");
			return 1;
		}
		catch(Exception e){
			return 0;
		}
	}

public static void TruncateProducts(){
			try{
				if(getConnection() == 1){
					String deleteProductQuery = "truncate table products";
					PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
					pst.executeUpdate();

					String deleteAccessoryQuery = "truncate table accessories";
					PreparedStatement pst1 = conn.prepareStatement(deleteAccessoryQuery);
					pst1.executeUpdate();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
public static void deleteProduct(String p_id){
				try{
					if(getConnection() == 1){

						String deleteProductQuery = "delete from products WHERE id='"+p_id+"'";
						PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
						pst.executeUpdate();

					String deleteAccessoryQuery = "delete from accessories WHERE p_id='"+p_id+"'";
						PreparedStatement pst1 = conn.prepareStatement(deleteAccessoryQuery);
						pst1.executeUpdate();

					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			public static int addnewProduct( Product product){

			try{
				if(getConnection() == 1){
					PreparedStatement pst=null;
					String ProductsQuery = "INSERT INTO products(name,id,quantity,description,display_under,imageUrl,price,discountAmt,rebateAmt) "+ "VALUES (?,?,?,?,?,?,?,?,?)";
					 pst = conn.prepareStatement(ProductsQuery);
					pst.setString(1,product.getName());
					pst.setString(2,product.getId());
					pst.setInt(3,product.getQuantity());
					pst.setString(4,product.getDescription());
					pst.setString(5,product.getDisplay_under());
					pst.setString(6,product.getImageUrl());
					//PreparedStatement pst = new PreparedInputStream(ImageUrl);
					//pst.setBinaryStream(6, (InputStream)fis, (int)(ImageUrl.length()));
					pst.setInt(7,product.getPrice());
					pst.setString(8,Double.toString(product.getDiscounAmt()));
					pst.setString(9,Double.toString(product.getRebateAmt()));


					pst.executeUpdate();

					Iterator ita = product.getAccessories().entrySet().iterator();
				while(ita.hasNext()){
					Map.Entry paira = (Map.Entry)ita.next();

					Accessory accessory= (Accessory)paira.getValue();

							PreparedStatement pst1=null;
				String insertAccesoryDetails = "INSERT INTO accessories(a_id,p_id, a_image, a_name, a_price, a_description) "+ "VALUES (?,?,?,?,?,?)";
				pst1 = conn.prepareStatement(insertAccesoryDetails);
				pst1.setString(1,accessory.getId());
				pst1.setString(2,product.getId());
				pst1.setString(3,accessory.getImageUrl());
				
				pst1.setString(4,accessory.getName());
				pst1.setInt(5,accessory.getPrice());
				pst1.setString(6,accessory.getDescription());
				pst1.executeUpdate();
				pst1.close();

			}
				pst.close();
				conn.close();

				//return 0;
					}
			}
			catch(Exception e){
				e.printStackTrace();
				return -1;
			}
			return 	1;

		}


		public static int modifyProduct(Product product,String id){

			try{
				if(getConnection() == 1){
					PreparedStatement pst=null;
					String ProductsQuery = "Update products SET id = ?,imageUrl = ?,name = ?,price = ?,description = ?,display_under = ?,discountAmt = ?,rebateAmt = ?,quantity = ? WHERE id='"+id+"'";
					pst = conn.prepareStatement(ProductsQuery);
					pst.setString(1,product.getId());
					pst.setString(2,product.getImageUrl());
					pst.setString(3,product.getName());
					pst.setInt(4,product.getPrice());
					pst.setString(5,product.getDescription());
					pst.setString(6,product.getDisplay_under());
					pst.setString(7,Double.toString(product.getDiscounAmt()));
					pst.setString(8,Double.toString(product.getRebateAmt()));
					pst.setInt(9,product.getQuantity());
					/*pst.setString(1,"123");
					pst.setString(2,"image");
					pst.setString(3,"siva");
					pst.setInt(4,300);
					pst.setString(5,"good");
					pst.setString(6,"Pants");
					pst.setString(7,Double.toString(50));
					pst.setString(8,Double.toString(30));
					pst.setInt(9,900);*/
					
					pst.executeUpdate();
					
				pst.close();
				conn.close();
System.out.println("Record is updated to table!");
				//return 0;
					}
			}
			catch(Exception e){
				e.printStackTrace();
				return -1;
			}
			return 	1;

		}



	public static int insertProducts( Map<String,Product> p){
		try{
			if(getConnection() == 1){
				Iterator it = p.entrySet().iterator();
				PreparedStatement pst=null;
				while(it.hasNext()){
					Map.Entry pair=(Map.Entry)it.next();

					Product product=(Product)pair.getValue();

				String ProductsQuery = "INSERT INTO products(name,id,quantity,description,display_under,imageUrl,price,discountAmt,rebateAmt) "+ "VALUES (?,?,?,?,?,?,?,?,?)";
				 pst = conn.prepareStatement(ProductsQuery);
				pst.setString(1,product.getName());
				pst.setString(2,product.getId());
				pst.setInt(3,product.getQuantity());
				pst.setString(4,product.getDescription());
				pst.setString(5,product.getDisplay_under());
				pst.setString(6,product.getImageUrl());
				pst.setInt(7,product.getPrice());
				pst.setString(8,Double.toString(product.getDiscounAmt()));
				pst.setString(9,Double.toString(product.getRebateAmt()));


				pst.executeUpdate();


				Iterator ita = product.getAccessories().entrySet().iterator();
			while(ita.hasNext()){
				Map.Entry paira = (Map.Entry)ita.next();

				Accessory accessory= (Accessory)paira.getValue();

			String insertAccesoryDetails = "INSERT INTO accessories(a_id,p_id, a_image, a_name, a_price, a_description) "+ "VALUES (?,?,?,?,?,?)";
			pst = conn.prepareStatement(insertAccesoryDetails);
			pst.setString(1,accessory.getId());
			pst.setString(2,product.getId());
			pst.setString(3,accessory.getImageUrl());
			pst.setString(4,accessory.getName());
			pst.setInt(5,accessory.getPrice());
			pst.setString(6,accessory.getDescription());
			pst.executeUpdate();
			pst.close();
			}
			}
			pst.close();
			conn.close();

			//return 0;
				}
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return 	1;
	}

	public static HashMap<String,Product> getProductDetails(){
	try{
	if(getConnection() == 1){
		HashMap<String,Product> ProductDetailsMap = new HashMap<String,Product>();
		String selectProductDetailsQuery ="select * from products";
		PreparedStatement pst = conn.prepareStatement(selectProductDetailsQuery);
		ResultSet rs = pst.executeQuery();
		Product product;
		while(rs.next()){
			if(!ProductDetailsMap.containsKey(rs.getString("id"))){

			product = new Product();
			product.setId(rs.getString("id"));
			product.setImageUrl(rs.getString("imageUrl"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			product.setDescription(rs.getString("description"));
			product.setDisplay_under(rs.getString("display_under"));
			product.setDiscounAmt(rs.getDouble("discountAmt"));
			product.setRebateAmt(rs.getDouble("rebateAmt"));
			product.setQuantity(rs.getInt("quantity"));



			String selectAccessoryDetailsQuery ="select * from accessories where p_id='"+rs.getString("id")+"'";
			PreparedStatement pst1 = conn.prepareStatement(selectAccessoryDetailsQuery);
			ResultSet rs1 = pst1.executeQuery();
			Accessory accessory;
				while(rs1.next()){
					//if(!AccessoryDetailsMap.containsKey(rs1.getString("a_id"))){

			accessory = new Accessory();
			accessory.setId(rs1.getString("a_id"));
			accessory.setImageUrl(rs1.getString("a_image"));
			accessory.setName(rs1.getString("a_name"));
			accessory.setPrice(rs1.getInt("a_price"));
			accessory.setDescription(rs1.getString("a_description"));


			product.getAccessories().put(rs1.getString("a_id"), accessory); //set the respective accessorys of that product


			}


			ProductDetailsMap.put(rs.getString("id"),product);

			}
		}


		return ProductDetailsMap;

	}
	else{
		return null;
	}

	}
	catch(Exception e){
		e.printStackTrace();
		return null;
	}
}


	public static void insertUser(User user){
		try{
			if(getConnection() == 1){
				String insertUser = "INSERT INTO users(firstName,lastName,userId,password,repassword,userType) "+ "VALUES (?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertUser);
				pst.setString(1,user.getFirstName());
				pst.setString(2,user.getLastName());
				pst.setString(3,user.getUserId());
				pst.setString(4,user.getPassword());
				pst.setString(5,user.getrePassword());
				pst.setString(6,user.getUserType());
				pst.executeUpdate();

				pst.close();
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	

	public static HashMap<String, User> getUsers(){
		try{
			if(getConnection() == 1){
				HashMap<String, User> users=new HashMap<String, User>();
				String selectUserQuery ="select * from users";
				PreparedStatement pst = conn.prepareStatement(selectUserQuery);
				ResultSet rs = pst.executeQuery();

				while(rs.next()){
					User user = new User();
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					user.setUserId(rs.getString("userId"));
					user.setPassword(rs.getString("password"));
					user.setrePassword(rs.getString("repassword"));
					user.setUserType(rs.getString("userType"));

					users.put(user.getUserId(),user);
				}

				pst.close();
				conn.close();
				return users;

			}
			else{
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static int insertOrderDetails(Order order){
		try{
			if(getConnection() == 1){

				String insertOrderDetailsQuery = "INSERT INTO Orders(O_Id, u_Id, O_Ddate, O_Status, item, itemCount, f_name, address, city, state, zipcode, card_number,email,flag) "+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(insertOrderDetailsQuery);
				pst.setInt(1,order.getOrdernumber());
				pst.setString(2,order.getUserid());
				pst.setString(3, order.getDeliveryDate());
				pst.setString(4,"Pending");
				pst.setString(5,order.getItemId());
				pst.setInt(6,order.getQuantity());
				pst.setString(7,order.getName());
				pst.setString(8,order.getAddress());
				pst.setString(9,order.getCity());
				pst.setString(10,order.getState());
				pst.setString(11,order.getZip());
				pst.setString(12,order.getCardNumber());
				pst.setString(13,order.getEmail());
				pst.setBoolean(14,false);
				//pst.setInt(13,order.getOrderAmt());
				pst.executeUpdate();
				pst.close();
				//updateInventory();

				//String updateInventoryQuery = "{call inventory_update}";
				CallableStatement cStmt = conn.prepareCall("{call inventory_update}");
				//PreparedStatement pst = conn.prepareStatement(updateInventoryQuery);
				cStmt.executeQuery();
				cStmt.close();
				
				
				conn.close();
				return 	1;
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
		/*public static void updateInventory(){
		try{
			if(getConnection() == 1){

				//String updateInventoryQuery = "{call inventory_update}";
				CallableStatement cStmt = conn.prepareCall("{call inventory_update}");
				//PreparedStatement pst = conn.prepareStatement(updateInventoryQuery);
				cStmt.executeQuery();
				cStmt.close();
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	public static ArrayList<SalesByProduct> getSalesByProduct(){
		try{
		if(getConnection() == 1){
			ArrayList<SalesByProduct> salesByProductList = new ArrayList<SalesByProduct>();
			String selectOrderDetailsQuery ="select o.item ProductName, i.item_unitprice ProductPrice, sum(o.itemCount) ItemsSold from Orders o INNER JOIN Inventory i on o.item = i.item_name group by o.item, i.item_unitprice";
			PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				salesByProductList.add(new SalesByProduct(rs.getString("ProductName"), rs.getInt("ItemsSold"), rs.getDouble("ProductPrice")));
			}

			return salesByProductList;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<ProductInfo> getProductInventoryInfo(boolean onlyOnSale){
		try{
		if(getConnection() == 1){
			ArrayList<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
			String productInfoQuery ="select item_name ProductName, stock_qty Stock, item_unitprice ProductPrice, allow_sale IsOnSale From Inventory";
			if(onlyOnSale)
				productInfoQuery += " where allow_sale = 1";
			PreparedStatement pst = conn.prepareStatement(productInfoQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				
				productInfoList.add(new ProductInfo(rs.getString("ProductName"), rs.getInt("Stock"), rs.getDouble("ProductPrice"), rs.getBoolean("IsOnSale")));
			}

			return productInfoList;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<DailySales> getDailySales(){
		try{
		if(getConnection() == 1){
			ArrayList<DailySales> dailySalesList = new ArrayList<DailySales>();
			String selectOrderDetailsQuery ="SELECT Left(o.O_Ddate,10) OrderDate, sum(itemCount * i.item_unitprice) TotalSales FROM ORDERS o INNER JOIN INVENTORY i on o.item = i.item_name GROUP BY OrderDate";
			PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				dailySalesList.add(new DailySales(rs.getString("OrderDate"), rs.getDouble("TotalSales")));
			}

			return dailySalesList;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String,ArrayList<Order>> getOrderDetails(){
		try{
		if(getConnection() == 1){
			HashMap<String,ArrayList<Order>> orderDetailsMap = new HashMap<String,ArrayList<Order>>();
			String selectOrderDetailsQuery ="select * from Orders";
			PreparedStatement pst = conn.prepareStatement(selectOrderDetailsQuery);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				if(!orderDetailsMap.containsKey(rs.getString("u_Id"))){
					ArrayList<Order> orderDetailsInfoArr = new ArrayList<Order>();
					orderDetailsMap.put(rs.getString("u_Id"), orderDetailsInfoArr);
				}
				ArrayList<Order> orderDetailsInfoArrList = orderDetailsMap.get(rs.getString("u_Id"));

				Order orderDetailsInfo = new Order();
				orderDetailsInfo.setName(rs.getString("f_name"));
				orderDetailsInfo.setAddress(rs.getString("address"));
				orderDetailsInfo.setCity(rs.getString("city"));
				orderDetailsInfo.setState(rs.getString("state"));
				orderDetailsInfo.setZip(rs.getString("zipcode"));
				//orderDetailsInfo.setPhone(rs.getString("phone"));
				//orderDetailsInfo.setCard_name(rs.getString("card_name"));
				orderDetailsInfo.setCardNumber(rs.getString("card_number"));
				orderDetailsInfo.setEmail(rs.getString("email"));
				//orderDetailsInfo.setMonth(rs.getString("month"));
				//orderDetailsInfo.setYear(rs.getString("year"));
				//orderDetailsInfo.setSecurityCode(rs.getString("securityCode"));
				orderDetailsInfo.setOrdernumber(rs.getInt("O_Id"));
				orderDetailsInfo.setDeliveryDate(rs.getString("O_Ddate"));
				orderDetailsInfo.setOrderStatus("confirmed");
				orderDetailsInfo.setItemId(rs.getString("item"));
				orderDetailsInfo.setQuantity(rs.getInt("itemCount"));
				orderDetailsInfo.setUserid(rs.getString("u_Id"));
				orderDetailsInfo.setFlag(rs.getInt("flag"));
				orderDetailsInfoArrList.add(orderDetailsInfo);

			}

			return orderDetailsMap;

		}
		else{
			return null;
		}

		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static void cancelOrder(String order_id){
		try{
			if(getConnection() == 1){

					String deleteOrderQuery = "delete from orders Where O_Id='"+order_id+"'";
					PreparedStatement pst1 = conn.prepareStatement(deleteOrderQuery);
					pst1.executeUpdate();
					pst1.close();
						conn.close();

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void updateOrderDetails(String order_id, int status){
		try{
			if(getConnection() == 1){
				String updateOrderQuery = "update orders set flag = '"+ status +"' Where O_Id='"+order_id+"'";
				PreparedStatement pst1 = conn.prepareStatement(updateOrderQuery);
				pst1.executeUpdate();
				pst1.close();
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void sortPageDetails(){
		try{
			if(getConnection() == 1){
				String sortPageQuery = "select * from products order by price";
				PreparedStatement pst1 = conn.prepareStatement(sortPageQuery);
				pst1.executeUpdate();
				pst1.close();
				conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
