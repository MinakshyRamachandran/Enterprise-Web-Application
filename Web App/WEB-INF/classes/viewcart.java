
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class viewcart extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

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
	  pw.println("<li  class=''><a href='home'>Home</a></li>");
	  pw.println("<li class=''><a href='tees'>T Shirts</a></li>");
	  pw.println("<li class=''><a href='shorts'>Shorts</a></li>");
		pw.println("<li class=''><a href='Shirts'>Shirts</a></li>");
		pw.println("<li class=''><a href='pants'>Pants</a></li>");

	  

	  pw.println("<li class=''><a href='suits'>Suit</a></li>");


		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");

		if (role == null) {
			pw.println("<li class=''><a href='register'>Register</a></li>");
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {
			//pw.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("<li class='' ><a href='vieworders'>View Orders</a></li>");

		pw.println("<div align='right'>");
		cart cartnum;
			 cartnum = (cart) session.getAttribute("cart");

	     if ( cartnum == null)

	 		{
	 			pw.println("<li class='start selected' ><a href='viewcart'>Cart(0)</a></li>");
	 		}

	 		else {
	       pw.println("<li class='start selected'><a href='viewcart'>Cart("+cartnum.numberofitems()+")</a></li>");
	     }
		pw.println("</div>");
		pw.println("</ul>");
		pw.println("</nav>");

		pw.println("<div id='body'>");
		pw.println("<section id='content'>");
		pw.println("<div id='container'>");
		cart mycart;
		 mycart = (cart) session.getAttribute("cart");

		if ( mycart == null)

		{
			if(role!=null){
		  pw.println("<br /><h3>Hello  "+role+"</h3>");
			}
			pw.println("<br /><h1>The Cart is Empty </h1>");
			pw.println("<tr>");
			pw.println("<td>");
			pw.println("</td>");
			pw.println("</tr>");

		}

		else {

			HashMap<String, List<Integer>> items =  mycart.getCartItems();
			HashMap<String, String> itemsUrl = mycart.getCartItemsUrl();

			if (items.isEmpty()) {

				if(role!=null){
			  pw.println("<br /><h3>Hello  "+role+"</h3>");
				}
				pw.println("<br /><h1>The Cart is Empty </h1>");
				pw.println("<tr>");
				pw.println("<td>");
				pw.println("</td>");
				pw.println("</tr>");

			} else {
				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<link rel='stylesheet' href='css/bootstrap.min.css' type='text/css' />");
				pw.println("<script src='http://code.jquery.com/jquery-1.11.1.min.js'></script>");
				pw.println("<link rel='stylesheet' href='sytle2.css' type='text/css' />");
				pw.println("<script src='js/bootstrap.min.js'></script>");
				pw.println("<script src='js/bootstrap.min.js'></script>");
				pw.println("<script src='js/script.js'></script>");
				pw.println("<title>cart</title>");
				pw.println("</head>");
				pw.println("<body>");

				if(role!=null){
			  pw.println("<br /><h3>Hello  "+role+"</h3>");
				}
				pw.println("<h1>Items in the cart </h1>");
				pw.println("<hr>");
				pw.println("<table  frame='box' rules='rows'>");
				pw.println("<tr><td></td><td>Product</td><td>Price&nbsp&nbsp&nbsp&nbsp</td><td>Quantity</td><td></td>");

				String url = null;
				for (Map.Entry<String, List<Integer>> entry : items.entrySet()) {
					String key = entry.getKey();
					 url = itemsUrl.get(key);
					List<Integer> values = entry.getValue();
					pw.println("<form action='deleteitem'><input type='hidden' name='name' value='" + key + "'>");
					pw.println("<tr><td><img src ='" + url + "' width = '100' height = '100'></td><td>" + key
							+ "  </td><td>" + "$" + values.get(0) + "</td>");
					pw.println("<td><select name='" + key	+ "'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
					pw.println("<td><input type='submit' name='action1' value='RemoveItem'></td></tr>");
				}


				pw.println("<tr ><td align='center' colspan='5'><input type='submit' name='action1' value='CheckOut'></td></tr></form>");
				pw.println("</table>");





				pw.println("<!--Item slider text-->");
pw.println("<div class='container'>");
	pw.println("<div class='row' id='slider-text'>");
		pw.println("<div class='col-md-6' >");
			pw.println("<br/><br/><h2>Related Accessories</h2>");
		pw.println("</div>");
	pw.println("</div>");
pw.println("</div>");

pw.println("<!-- Item slider-->");
pw.println("<div class='container-fluid'>");

 pw.println("<div class='row'>");
		pw.println("<div class='col-xs-12 col-sm-12 col-md-12'>");
			pw.println("<div class='carousel carousel-showmanymoveone slide' id='itemslider'>");
				pw.println("<div class='carousel-inner'>");

				/////////////////////////////////////////////////////
					HashMap<String,Product> pMap;
					Map<String,Accessory> accessories;
					String[] aName = new String[3];
					int[] aPrice = new int[3];
					String[] aDesc = new String[3];
					String[] aUrl = new String[3];

				 pMap = (HashMap<String,Product>)session.getAttribute("pMap");
				 int i=0;

				 for(Map.Entry<String, Product> entry1 : pMap.entrySet()){
					 Product product = entry1.getValue();
					 if(product.getImageUrl().equals(url)){
						 accessories = (Map<String,Accessory>)product.getAccessories();

						 for(Map.Entry<String, Accessory> entry2 : accessories.entrySet()){
							 Accessory accessory = entry2.getValue();
							 aName[i] = accessory.getName();
							 aPrice[i] = accessory.getPrice();
							 aDesc[i] = accessory.getDescription();
							 aUrl[i] = accessory.getImageUrl();

							 i++;
						 }

					 }
				 }
				 pw.println("<div class='item active'>");
	 				pw.println("<div class='col-xs-12 col-sm-6 col-md-2'>");

	 				pw.println("</div>");
	 			pw.println("</div>");

				 pw.println("<div class='item'>");
					 pw.println("<div class='col-xs-12 col-sm-6 col-md-2'>");
						 pw.println("<a href='#'><img src='"+aUrl[0]+"' class='img-responsive center-block'></a>");
						 pw.println("<h3 class='text-center'>"+aName[0]+"</h4>");
						pw.println(" <h5 class='text-center'>"+aPrice[0]+"</h5>");
						//pw.println(" <h5 >Test</h5>");
						pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
		 			 pw.println("<input class = 'submit-button' type = 'submit' name = 'carousel' value = ' Add to Cart'>");
		 			 pw.println("<input type='hidden' name='model' value='" + aName[0] + "'>");
		              pw.println("<input type='hidden' name='price' value='" + aPrice[0] + "'>");
		              pw.println("<input type='hidden' name='imageUrl' value='" + aUrl[0] + "'>");
		 			 pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
		              pw.println("</form>");

					 pw.println("</div>");
				 pw.println("</div>");



				 		if(aUrl[1]!= null){
					 pw.println("<div class='item'>");
						 pw.println("<div class='col-xs-12 col-sm-6 col-md-2'>");
							 pw.println("<a href='#'><img src='"+aUrl[1]+"'class='img-responsive center-block'></a>");
							 pw.println("<h3 class='text-center'>"+aName[1]+"</h4>");
							 pw.println("<h4 class='text-center'>"+aPrice[1]+"</h5>");
							 pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
							 pw.println("<input class = 'submit-button' type = 'submit' name = 'carousel' value = ' Add to Cart'>");
							 pw.println("<input type='hidden' name='model' value='" + aName[1] + "'>");
				             pw.println("<input type='hidden' name='price' value='" + aPrice[1] + "'>");
				             pw.println("<input type='hidden' name='imageUrl' value='" + aUrl[1] + "'>");
							 pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
				             pw.println("</form>");

						 pw.println("</div>");
					 pw.println("</div>");
				 }
				 if(aUrl[2]!= null){
				pw.println("<div class='item'>");
					pw.println("<div class='col-xs-12 col-sm-6 col-md-2'>");
						pw.println("<a href='#'><img src='"+aUrl[2]+"'class='img-responsive center-block'></a>");
						pw.println("<h3 class='text-center'>"+aName[2]+"</h4>");
						pw.println("<h4 class='text-center'>"+aPrice[2]+"</h5>");
						pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
		 			 pw.println("<input class = 'submit-button' type = 'submit' name = 'carousel' value = ' Add to Cart'>");
		 			 pw.println("<input type='hidden' name='model' value='" + aName[2] + "'>");
		              pw.println("<input type='hidden' name='price' value='" + aPrice[2] + "'>");
		              pw.println("<input type='hidden' name='imageUrl' value='" + aUrl[2] + "'>");
		 			 pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
		              pw.println("</form>");

					pw.println("</div>");
				pw.println("</div>");
			}

			if(aUrl[1]!= null){
		 pw.println("<div class='item'>");
			 pw.println("<div class='col-xs-12 col-sm-6 col-md-2'>");
				 pw.println("<a href='#'><img src='"+aUrl[1]+"'class='img-responsive center-block'></a>");
				 pw.println("<h3 class='text-center'>"+aName[1]+"</h4>");
				 pw.println("<h4 class='text-center'>"+aPrice[1]+"</h5>");
				 pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
				 pw.println("<input class = 'submit-button' type = 'submit' name = 'carousel' value = ' Add to Cart'>");
				 pw.println("<input type='hidden' name='model' value='" + aName[1] + "'>");
							 pw.println("<input type='hidden' name='price' value='" + aPrice[1] + "'>");
							 pw.println("<input type='hidden' name='imageUrl' value='" + aUrl[1] + "'>");
				 pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
							 pw.println("</form>");

			 pw.println("</div>");
		 pw.println("</div>");
	 }
	 if(aUrl[2]!= null){
	pw.println("<div class='item'>");
		pw.println("<div class='col-xs-12 col-sm-6 col-md-2'>");
			pw.println("<a href='#'><img src='"+aUrl[2]+"'class='img-responsive center-block'></a>");
			pw.println("<h3 class='text-center'>"+aName[2]+"</h4>");
			pw.println("<h4 class='text-center'>"+aPrice[2]+"</h5>");
			pw.println("<form class = 'submit-button' method = 'get' action = 'addtocart'>");
		 pw.println("<input class = 'submit-button' type = 'submit' name = 'carousel' value = ' Add to Cart'>");
		 pw.println("<input type='hidden' name='model' value='" + aName[2] + "'>");
						pw.println("<input type='hidden' name='price' value='" + aPrice[2] + "'>");
						pw.println("<input type='hidden' name='imageUrl' value='" + aUrl[2] + "'>");
		 pw.println("<input type='hidden' name='quantity' value='" + 1 + "'>");
						pw.println("</form>");

		pw.println("</div>");
	pw.println("</div>");
}





				pw.println("<div id='slider-control'>");
				pw.println("<a class='left carousel-control' href='#itemslide' data-slide='prev'><img src='https://s12.postimg.org/uj3ffq90d/arrow_left.png' alt='Left' class='img-responsive'></a>");
				pw.println("<a class='right carousel-control' href='#itemslider' data-slide='next'><img src='https://s12.postimg.org/djuh0gxst/arrow_right.png' alt='Right' class='img-responsive'></a>");
			pw.println("</div>");
			pw.println("</div>");
		pw.println("</div>");
	pw.println("</div>");
pw.println("</div>");
pw.println("<!-- Item slider end-->");




			}

		}

		pw.println("</div>");
		pw.println("</section>");
		pw.println("</div>");


		RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
		sd.include(request, response);

	}

}
