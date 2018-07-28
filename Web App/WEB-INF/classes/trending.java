import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class trending extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request,response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			String product_id = request.getParameter("product_id");
			HttpSession session = request.getSession(false);
			String role =(String) session.getAttribute("role");
			Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();

			Product product = pMap.get(product_id);

			PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		out.println("<html><head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		out.println("<title>Falcon</title>");
		out.println("<link rel='shortcut icon' href='images/icon.jpg'/>");
		out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
		//out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
		//out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		//out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id='container'>");
		out.println("<header>");
		out.println("<h1><a href='/'>Falcon</a></h1>");
		out.println("</header>");
		out.println("<nav>");
		out.println("<ul>");
		out.println("<li class=''><a href='home'>Home</a></li>");
		out.println("<li class=''><a href='short'>Shorts</a></li>");
		out.println("<li class=''><a href='shirt'>Shirts</a></li>");
		out.println("<li class=''><a href='suit'>Suits</a></li>");
		out.println("<li class=''><a href='tshirts'>T Shirts</a></li>");
		out.println("<li class=''><a href='pants'>Pants</a></li>");

		if (role == null) {
			out.println("<li class=''><a href='register'>Register</a></li>");
			out.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {
			out.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
			out.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		out.println("<li class='' ><a href='vieworders'>View Orders</a></li>");

		out.println("<div align='right'>");
		cart mycart;
              mycart = (cart) session.getAttribute("cart");

              if ( mycart == null)

              {
                    out.println("<li class='' ><a href='viewcart'>Cart(0)</a></li>");
              }

              else {
      out.println("<li class='' ><a href='viewcart'>Cart("+mycart.numberofitems()+")</a></li>");
    }
		out.println("</div>");
		out.println("</ul>");
		out.println("</nav>");
		/*
	out.println("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");

	out.println("<ol class='carousel-indicators'>");
    out.println("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
    out.println("<li data-target='#myCarousel' data-slide-to='1'></li>");
    out.println("<li data-target='#myCarousel' data-slide-to='2'></li>");
	out.println("</ol>");


	out.println("<div class='carousel-inner'>");
    out.println("<div class='item active'>");
    out.println("<img src='images/1.jpg' alt='1'>");
    out.println("<div class='carousel-caption'>");
    out.println("</div>");
    out.println("</div>");

    out.println("<div class='item'>");
    out.println("<img src='images/2.jpg' alt='2'>");
    out.println("<div class='carousel-caption'>");
    out.println("</div>");
    out.println("</div>");

    out.println("<div class='item'>");
    out.println("<img src='images/3.jpg' alt='3'>");
    out.println("<div class='carousel-caption'>");;
    out.println("</div>");
    out.println("</div>");
	out.println("</div>");


	out.println("<a class='left carousel-control' href='#myCarousel' data-slide='prev'>");
    out.println("<span class='glyphicon glyphicon-chevron-left'></span>");
    out.println("<span class='sr-only'>Previous</span>");
	out.println("</a>");
	out.println("<a class='right carousel-control' href='#myCarousel' data-slide='next'>");
    out.println("<span class='glyphicon glyphicon-chevron-right'></span>");
    out.println("<span class='sr-only'>Next</span>");
	out.println("</a>");
	out.println("</div>");
	*/

		out.println("<div id='body'>");
  out.println("<section id='content'>");
 out.println("<article>");
	out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
					out.println("<tr>");
					out.println("<td>");
					out.println("<h1 style=\"color: #777;border-bottom: 2px solid #777;\">Trending</h1>");
					out.println("</td>");
					out.println("</tr>");
					out.println("</table>");

					out.println("<br/>");
					out.println("<br/>");
					out.println("<h3 style=\"font-weight: bold\">Top 5 Most Liked Products:</h3>");
					out.println("<div >");
					out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
					out.println("<tr>");
					out.println("<th class='addcolor' align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">Product</span>");
					out.println("</th>");
					out.println("<th class='addcolor' align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">Avg. Review Rating</span>");
					out.println("</th>");
					out.println("</tr>");
					out.println("</table>");

					Map<String, String> mostLikedProductsMap = MongoDBDataStoreUtilities.getTop5MostLikedProducts();

					if(mostLikedProductsMap != null){
						if(mostLikedProductsMap.isEmpty()){
							out.println("<br/>");
							out.println("<br/>");
							out.println("<h3 style=\"font-weight: bold\">Not enough information available to display this!</h3>");
						}
						else{
							for (Map.Entry<String,String> entry : mostLikedProductsMap.entrySet()) {
								out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
								out.println("<tr>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+entry.getKey()+"</span>");
								out.println("</td>");
								out.println("<td align=\"center\" width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+entry.getValue()+"</span>");
								out.println("</td>");
								out.println("</tr>");
								out.println("</table>");
							}
						}
					}
					else{
						out.println("<br/>");
						out.println("<br/>");
						out.println("<h1>MongoDB Server is not running!</h1>");
					}
					out.println("</div >");

					out.println("<br/>");
					out.println("<br/>");
					out.println("<h3 style=\"font-weight: bold\">Top 5 Zipcodes where maximum number of products Reviewed:</h3>");
					out.println("<div >");
					out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
					out.println("<tr>");
					out.println("<th class='addcolor' align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">Zipcode</span>");
					out.println("</th>");
					out.println("<th class='addcolor' align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">No. of Items Reviewed</span>");
					out.println("</th>");
					out.println("</tr>");
					out.println("</table>");

					Map<String, String> mostProductsSoldZipcodeMap = MongoDBDataStoreUtilities.getTop5MostProductsSoldZipcode();

					if(mostProductsSoldZipcodeMap != null){
						if(mostProductsSoldZipcodeMap.isEmpty()){
							out.println("<br/>");
							out.println("<br/>");
							out.println("<h3 style=\"font-weight: bold\">Not enough information available to display this!</h3>");
						}
						else{
							for (Map.Entry<String,String> entry : mostProductsSoldZipcodeMap.entrySet()) {
								out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
								out.println("<tr>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+entry.getKey()+"</span>");
								out.println("</td>");
								out.println("<td align=\"center\" width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+entry.getValue()+"</span>");
								out.println("</td>");
								out.println("</tr>");
								out.println("</table>");
							}
						}
					}
					else{
						out.println("<br/>");
						out.println("<br/>");
						out.println("<h1>MongoDB Server is not running!</h1>");
					}


					out.println("</div >");

					out.println("<br/>");
					out.println("<br/>");
					out.println("<h3 style=\"font-weight: bold\">Top 5 most reviewed products regardless of rating :</h3>");
					out.println("<div >");
					out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
					out.println("<tr>");
					out.println("<th class='addcolor' align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">Product</span>");
					out.println("</th>");
					out.println("<th class='addcolor' align=\"center\" width=\"50%\">");
					out.println("<span style=\"font-size:24px;font-weight: bold\">No. of Reviews</span>");
					out.println("</th>");
					out.println("</tr>");
					out.println("</table>");

					Map<String, String> mostSoldProductMap = MongoDBDataStoreUtilities.getTop5MostSoldProducts();

					if(mostSoldProductMap != null){
						if(mostSoldProductMap.isEmpty()){
							out.println("<br/>");
							out.println("<br/>");
							out.println("<h3 style=\"font-weight: bold\">Not enough information available to display this!</h3>");
						}
						else{
							for (Map.Entry<String,String> entry : mostSoldProductMap.entrySet()) {
								out.println("<table style=\"font-size:14px; width:80%;color:#333334\">");
								out.println("<tr>");
								out.println("<td  width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+entry.getKey()+"</span>");
								out.println("</td>");
								out.println("<td align=\"center\" width=\"50%\">");
								out.println("<span style=\"font-size:20px;font-weight: bold\">"+entry.getValue()+"</span>");
								out.println("</td>");
								out.println("</tr>");
								out.println("</table>");
							}
						}
					}
					else{
						out.println("<br/>");
						out.println("<br/>");
						out.println("<h1>MongoDB Server is not running!</h1>");
					}


					out.println("</div >");

out.println("</article>");


		out.println("</section>");
		out.println("<aside class='sidebar'>");
		out.println("<ul>");
		out.println("<li>");
		out.println("<h4>Products</h4>");
		out.println("<ul>");
		out.println("<li class=''><a href='home'>Home</a></li>");
		out.println("<li class='start selected'><a href='short'>Shorts</a></li>");
		out.println("<li class=''><a href='shirt'>Shirts</a></li>");
		out.println("<li class=''><a href='suit'>Suits</a></li>");
		out.println("<li class=''><a href='tshirts'>T Shirts</a></li>");
		out.println("<li class=''><a href='pants'>Pants</a></li>");
		/*out.println("<li class=''><a href='estorage'>External Storage</a></li>");

		out.println("</ul>");
		out.println("</li>");
		out.println("<li>");
		out.println("<h4>Helpful Links</h4>");
		out.println("<ul>");
		out.println("<li><a href='http://www.javatpoint.com/' title=' learn java'>W3 School</a></li>");*/
		out.println("</ul></li></ul></aside>");
		out.println("<div class='clear'></div>");
		out.println("</div>");
		out.println("<footer>");
		out.println("<div class='footer-content'>");
		out.println("<div class='clear'></div>");
		out.println("</div>");
		out.println("<div class='footer-bottom'>");
		out.println("<p>Falcon Ltd</p>");
		out.println("</div>");
		out.println("</footer>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");


	}catch (Exception e) {
			e.printStackTrace();
	}
}
}
