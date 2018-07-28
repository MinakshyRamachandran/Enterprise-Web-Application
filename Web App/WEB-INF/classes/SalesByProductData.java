import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
@WebServlet("/SalesByProduct")
public class SalesByProductData extends HttpServlet {

	public SalesByProductData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLDataStoreUtilities mysqlDataStoreUtilities = new MySQLDataStoreUtilities();
		List<SalesByProduct> salesByProductList = mysqlDataStoreUtilities.getSalesByProduct();
		Gson gson = new Gson();
		String jsonString = gson.toJson(salesByProductList);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
}