import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addnewproduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HashMap<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		pw.println("<html><head>");
		pw.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		pw.println("<title>Falcon</title>");
		pw.println("<link rel='shortcut icon' href='images/icon.jpg'/>");
		pw.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<div id='container'>");
		pw.println("<header>");
		pw.println("<h1><a href='/'>Falcon</a></h1>");
	
		pw.println("</header>");
		pw.println("<nav>");
		pw.println("<ul>");
		pw.println("<li  class='start selected'><a href='managestore'>Add Products</a></li>");
		pw.println("<li class=''><a href='modifyproduct'>Modify Product</a></li>");
		pw.println("<li class=''><a href='deleteproduct'>Delete Product</a></li>");
		pw.println("<li class=''><a href='Reports.html'> Reports </a></li>");
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {
			//pw.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
			pw.println("<a href='Reports.html'> Reports </a>");
			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("</ul>");
		pw.println("</nav>");
		try {
			String productid = request.getParameter("product_id");
			String display_under = request.getParameter("display_under");
			String productname = request.getParameter("product_name");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			//Image ImageUrl = request.getParameter("ImageUrl");
			int quantity = 20;
			Double discount_amt = 0.0;
			Double rebate_amt = 0.0;



			Product product =  new Product();
			product.setId(productid);
			product.setName(productname);
			product.setDescription(description);
			product.setDisplay_under(display_under);
			product.setImageUrl("images/noimage.png");
			//product.setImageUrl(ImageURL);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setDiscounAmt(discount_amt);
			product.setRebateAmt(rebate_amt);


		    MySQLDataStoreUtilities.addnewProduct(product);


			pw.println("<h3><br /><br />The Product has been succesfully added to the inventory </h3><br /><br />");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
