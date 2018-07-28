
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Shirts extends HttpServlet {

	private HashMap<String, Product> items;
	private HashMap<String, Product> shirts;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HashMap<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");


						  RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
						  rd.include(request, response);

		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");

		if (role == null) {
			pw.println("<li class=''><a href='register'>Register</a></li>");
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {

			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("<li class='' ><a href='vieworders'>View Orders</a></li>");

		pw.println("<div align='right'>");
		cart mycart;
			 mycart = (cart) session.getAttribute("cart");

	     if ( mycart == null)

	 		{
	 			pw.println("<li class='' ><a href='viewcart'>Cart(0)</a></li>");
	 		}

	 		else {
	       pw.println("<li class=''><a href='viewcart'>Cart("+mycart.numberofitems()+")</a></li>");
	     }
		pw.println("</div>");
		pw.println("</ul>");
		pw.println("</nav>");
		pw.println("<div id='body'>");
		pw.println("<section id='content'>");
		pw.println("<article>");
		pw.println("<table>");

for (HashMap.Entry<String, Product> entry : pMap.entrySet()) {
	Product product = entry.getValue();
	if(product.getDisplay_under().equalsIgnoreCase("Shirt")){



		pw.println("<tr>");
		pw.println("<td>");
		pw.println("<a href=#><img src=\""+product.getImageUrl()+"\" height=\"140px\" width=\"155px\">");


		pw.println("<span style=\"font-weight: bold;font-size:16px;text-align: center\">");
		pw.println("<td/>");
			pw.println("<td>");
		pw.println(product.getName()+" "+product.getDescription()+"<br/>");

		pw.println("</span>");
		pw.println("<span style=\"font-weight: bold;font-size:26px;text-align: center\">");
		pw.println("</td>");
			pw.println("<td>");
		pw.println("$"+product.getPrice());
		pw.println("</span>");
		pw.println("</td>");
		pw.println("<td>");
		pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
		pw.println("<i class='glyphicon glyphicon-shopping-cart'></i>");
		pw.println("<input class = 'submit-button' type = 'submit' name = 'Smartphone_Original' value = ' Add to Cart'>");
		pw.println("<input type='hidden' name='model' value='" + product.getName() + "'>");
		pw.println("<input type='hidden' name='price' value='" + product.getPrice() + "'>");
		pw.println("<input type='hidden' name='imageUrl' value='" + product.getImageUrl() + "'>");
		pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
		pw.println("</form>");
		pw.println("</td>");
		pw.println("</a>");
		pw.println("<td>");
		pw.println("<form class = 'submit-button' method = 'get' action = 'prodinfo'>");
		pw.println("<input class = 'submit-button' type = 'submit' name = 'Smartphone_Original' value = ' More Details'>");
		pw.println("<input type='hidden' name='model' value='" + product.getName() + "'>");
		pw.println("<input type='hidden' name='price' value='" + product.getPrice() + "'>");
		pw.println("<input type='hidden' name='imageUrl' value='" + product.getImageUrl() + "'>");
		pw.println("<input type='hidden' name='type' value='" + product.getDisplay_under() + "'>");
		pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
		pw.println("</form>");
		pw.println("</td>");

		pw.println("</tr>");



	}
}
pw.println("</table>");
pw.println("</div>");
pw.println("</article>");
pw.println("</section>");



RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
sd.include(request, response);

	}
}
