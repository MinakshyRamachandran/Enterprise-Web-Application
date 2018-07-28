import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
@WebServlet("/DailySales")
public class DailySalesData extends HttpServlet {

	public DailySalesData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLDataStoreUtilities mysqlDataStoreUtilities = new MySQLDataStoreUtilities();
		List<DailySales> dailySalesList = mysqlDataStoreUtilities.getDailySales();
		Gson gson = new Gson();
		String jsonString = gson.toJson(dailySalesList);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
}