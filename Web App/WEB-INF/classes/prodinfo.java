import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class prodinfo extends HttpServlet {


	/*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Boolean reviewed = Boolean.valueOf(request.getParameter("reviewed"));
if(!reviewed){
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession();

		String role = (String) session.getAttribute("role");
		response.setContentType("text/html;charset=UTF-8");


		RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
		rd.include(request, response);

		if (role == null)
		{
		pw.println("<li class=''><a href='register'>Register</a></li>");
		pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		}
		  else if (role.equals("SalesManager")){
	      pw.println("<li class=''><div class='dropdown'><a>More Options</a >");
	      pw.println("<a href='register'>Register Customer</a >");
	      pw.println("<a href='signout'> Sign Out </a>");
	      pw.println("</div >");
	      pw.println("</div >");
	      pw.println("</li >");
	      	  } else
		{
			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}


		pw.println("<li class='' ><a href='vieworders'>View Orders</a></li>");


		pw.println("<div align='right'>");
	  cart mycart;
			 mycart = (cart) session.getAttribute("cart");

			if ( mycart == null)

			{

				pw.println("<li class='' ><a href='viewcart'><i class='glyphicon glyphicon-shopping-cart'></i>(0)</a></li>");
			}

			else {
	      pw.println("<li class='' ><a href='viewcart'><i class='glyphicon glyphicon-shopping-cart'></i>("+mycart.numberofitems()+")</a></li>");
	    }

		pw.println("</div>");
		pw.println("</ul>");
		pw.println("</nav>");

		pw.println("<div id='body'>");
	  pw.println("<section id='content'>");
	  pw.println("<div id='container'>");

			Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
			String product_id = request.getParameter("model");

			Product product = pMap.get(product_id);


			if(product!=null){
		
		 pw.println("<table>");
		 pw.println("<tr>");
		 pw.println("<td colspan=\"2\">");
		 pw.println("<h1>");
		 pw.println("&nbsp;"+product.getName()+" "+product.getDescription());
		 pw.println("</h1>");
		 pw.println("</td>");
		 pw.println("</tr>");
		 pw.println("<tr>");
		 pw.println("<td>");
		 pw.println("<img src=\""+product.getImageUrl()+"\" height=\"260px\" width=\"280px\">");
		 pw.println("</td>");
		 pw.println("<td>");
		 pw.println("<h2>");
		 pw.println("New Condition");
		 pw.println("</h2>");
		 pw.println("<br/>");
		 pw.println("<h3>");
		 pw.println("<em>$</em>");
		 pw.println("<span>"+product.getPrice()+"</span>");
		 pw.println("</h3>");



		 pw.println("<br/>");
		 pw.println("<br/>");

		 pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
 		pw.println("<i class='glyphicon glyphicon-shopping-cart'></i>");
 		pw.println("<input class = 'submit-button' type = 'submit' name = 'Smartphone_Original' value = ' Add to Cart'>");
 		pw.println("<input type='hidden' name='model' value='" + product.getName() + "'>");
 		pw.println("<input type='hidden' name='price' value='" + product.getPrice() + "'>");
 		pw.println("<input type='hidden' name='imageUrl' value='" + product.getImageUrl() + "'>");
 		pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
 		pw.println("</form>");

		 pw.println("<br/>");
		 pw.println("<br/>");

		 if(role == null){
			 pw.println("<button class = 'submit-button' onclick=\"location.href='login'\" >Write A Review</button>");


		 }
		 else{

			 session.setAttribute("itemId", product.getName());
			 pw.println("<button class = 'submit-button' onclick=\"location.href='userreview.html'\" >Write A Review</button>");
			 //RequestDispatcher rev = request.getRequestDispatcher("userreview.html");
 			//rev.include(request, response);
		 }
		 pw.println("</td>");
		 pw.println("</tr>");
		 pw.println("</table>");

		 HashMap<String, ArrayList<Review>> reviewsMap = MongoDBDataStoreUtilities.getUserReviews();

		 if(reviewsMap != null){
			 pw.println("<h2>&nbsp;User Reviews:</h2>");
			 pw.println("<div >");
			 ArrayList<Review> reviewsArrList = 	new ArrayList<Review>();
			 if(reviewsMap.get(product.getName())!=null){
				 reviewsArrList =  reviewsMap.get(product.getName());
			 }
			 for (Review review : reviewsArrList) {

				 pw.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
				 pw.println("<tr>");
				 pw.println("<th colspan=\"2\" style=\"font-size:18px\">"+review.getProductRvS()+"</th>");
				 pw.println("</tr>");
				 pw.println("<tr>");
				 pw.println("<td valign=\"top\" width=\"70%\">");
				 pw.println("<table>");
				 pw.println("<tr>");
				 pw.println("<td>");
				 pw.println("<img src=\"images/"+review.getProductRvR()+".png\"/>&nbsp;&nbsp;&nbsp;<span style=\"font-size:36px;font-weight: bold\">"+review.getProductRvR()+"</span>");
				 pw.println("</td>");
				 pw.println("<td>");
				 pw.println("Reviewed on:<br/>");
				 pw.println(review.getProductRvD());
				 pw.println("</td>");
				 pw.println("</tr>");
				 pw.println("<tr>");
				 pw.println("<td colspan=\"2\">");
				 pw.println(review.getProductRvU()+"<br/>");
				 pw.println(review.getProductRvA()+"yrs "+review.getProductRvG()+", "+ review.getProductRvO());
				 pw.println("</td>");
				 pw.println("</tr>");
				 pw.println("<tr>");
				 pw.println("<td colspan=\"2\">");
				 pw.println("<p style=\"font-size:14px; text-align: justify;line-height:20px;color:#333334\">"+review.getProductRvB()+"</p>");
				 pw.println("</td>");
				 pw.println("</tr>");
				 pw.println("</table>");
				 pw.println("</td>");
				 pw.println("<td style=\"font-size: 13px;\" width=\"28%\" valign=\"top\">");
				 pw.println("<span style=\"text-decoration: underline;font-weight: bold;\">Model Name</span><br/><br/>");
				 pw.println("Model Name: <span style=\"font-weight: bold;\">"+review.getProductMN()+"</span><br/>");
				 pw.println("Item Category: "+review.getProductC()+"<br/>");
				 pw.println("Item Price: "+review.getProductP()+"<br/>");
				 pw.println("ItemOnSale: "+review.getProductOS()+"<br/>");
				 pw.println("Manufacturer Name: "+review.getProductMrN()+"<br/>");
				 pw.println("Manufacture Rebate: "+review.getProductMR());
				 pw.println("<br/>");
				 pw.println("<br/>");
				 pw.println("<span style=\"text-decoration: underline;font-weight: bold;\">Retailer:</span><br/><br/>");
				 pw.println(review.getProductRN()+"<br/>");
				 pw.println(review.getProductRC()+", "+review.getProductRS()+" "+ review.getProductRZ());
				 pw.println("</td>");
				 pw.println("</tr>");
				 pw.println("</table>");
			 }
			 pw.println("</div>");
		 }
		 else{
			 pw.println("<h2>&nbsp;User Reviews:</h2>");
			 pw.println("<div >");
			 pw.println("<h4 style=\"color:red\">&nbsp;&nbsp; MongoDB Server is not running right now!</h4>");
			 pw.println("</div");
		 }



		}


			pw.println("</div>");
			pw.println("</section>");
			pw.println("</div>");

			RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
			sd.include(request, response);
	}
												/**********************************************************************/
												/**********************************************************************/
												/**********************************************************************/
												/**********************************************************************/
	else{
			PrintWriter pw = response.getWriter();

			HttpSession session = request.getSession();

			String role = (String) session.getAttribute("role");
			response.setContentType("text/html;charset=UTF-8");


			RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
			rd.include(request, response);

			if (role == null) {
				pw.println("<li class=''><a href='register'>Register</a></li>");
				pw.println("<li class='' ><a href='login'>Sign in</a></li>");
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
					pw.println("<li class='' ><a href='viewcart'>Cart("+mycart.numberofitems()+")</a></li>");
				}
			pw.println("</div>");
			pw.println("</ul>");
			pw.println("</nav>");

			pw.println("<div id='body'>");
			pw.println("<section id='content'>");
			pw.println("<div id='container'>");

				String product_id = request.getParameter("product_id");
				Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();


				for (HashMap.Entry<String, Product> entry : pMap.entrySet()) {
					Product product = entry.getValue();


					if(product.getName().equalsIgnoreCase(product_id)){


				if(product!=null){

			 pw.println("<table>");
			 pw.println("<tr>");
			 pw.println("<td colspan=\"2\">");
			 pw.println("<h1>");
			 pw.println("&nbsp;"+product.getName()+" "+product.getDescription());
			 pw.println("</h>");
			 pw.println("</td>");
			 pw.println("</tr>");
			 pw.println("<tr>");
			 pw.println("<td>");
			 pw.println("<img src=\""+product.getImageUrl()+"\" height=\"260px\" width=\"280px\"");
			 pw.println("</td>");
			 pw.println("<td>");
			 pw.println("<h2>");
			 pw.println("New Condition");
			 pw.println("</h2>");
			 pw.println("<br/>");
			 pw.println("<h3>");
			 pw.println("<em>$</em>");
			 pw.println("<span>"+product.getPrice()+"<span>");
			 pw.println("</h3>");



			 pw.println("<br/>");
			 pw.println("<br/>");

			 pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
			pw.println("<i class='glyphicon glyphicon-shopping-cart'></i>");
			pw.println("<input class = 'submit-button' type = 'submit' name = 'Smartphone_Original' value = ' Add to Cart'>");
			pw.println("<input type='hidden' name='model' value='" + product.getName() + "'>");
			pw.println("<input type='hidden' name='price' value='" + product.getPrice() + "'>");
			pw.println("<input type='hidden' name='imageUrl' value='" + product.getImageUrl() + "'>");
			pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
			pw.println("</form>");

			 pw.println("<br/>");
			 pw.println("<br/>");

			 if(role == null){
				 pw.println("<button class = 'submit-button' onclick=\"location.href='login'\" >Write A Review</button>");


			 }
			 else{
				 session.setAttribute("itemId", product_id);
				 pw.println("<button class = 'submit-button' onclick=\"location.href='userreview.html'\" >Write A Review</button>");
				 //RequestDispatcher rev = request.getRequestDispatcher("userreview.html");
				//rev.include(request, response);
			 }
			 pw.println("</td>");
			 pw.println("</tr>");
			 pw.println("</table>");



			}
	}
}

				pw.println("</div>");
				pw.println("</section>");
				pw.println("</div>");

				RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
				sd.include(request, response);
		}


}
}
