import java.sql.*;
import java.util.*;

public class AjaxUtility {

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


	public static StringBuffer findMatchingProducts(String searchText){

		HashMap<String,Product> pMap;
		StringBuffer sb = new StringBuffer();

		pMap=getProductDetails();

		for (Map.Entry<String, Product> entry : pMap.entrySet()) {

			Product product = entry.getValue();
			//System.out.println("***** product "+product.getName().toLowerCase());
			if (product.getName().toLowerCase().startsWith(searchText.toLowerCase()))
			{

				sb.append("<product>");
				sb.append("<id>" + product.getId() + "</id>");
				sb.append("<productName>" + product.getName() + "</productName>");
				sb.append("</product>");
			}
		}
		return sb;
	}


}
