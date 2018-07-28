import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
@WebServlet("/InventoryByProductOnSale")
public class InventoryByProductOnSale extends HttpServlet {

	public InventoryByProductOnSale() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLDataStoreUtilities mysqlDataStoreUtilities = new MySQLDataStoreUtilities();
		List<ProductInfo> productInfoList = mysqlDataStoreUtilities.getProductInventoryInfo(true);
		Gson gson = new Gson();
		String jsonString = gson.toJson(productInfoList);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
}