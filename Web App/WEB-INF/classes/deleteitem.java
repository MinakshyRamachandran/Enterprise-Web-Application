
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class deleteitem extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			//pw.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
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
	      pw.println("<li class='' ><a href='viewcart'>Cart("+mycart.numberofitems()+")</a></li>");
	    }
		pw.println("</div>");
		pw.println("</ul>");
		pw.println("</nav>");

		pw.println("<div id='body'>");
	  pw.println("<section id='content'>");
	  pw.println("<div id='container'>");
		String action = request.getParameter("action1");
		String email = request.getParameter("email");

		if (action.equals("RemoveItem"))

		{

			String name = request.getParameter("name");
			cart ekart;
			ekart = (cart) session.getAttribute("cart");
			ekart.deleteFromCart(name);
			session.setAttribute("cart", ekart);
			ekart = (cart) session.getAttribute("cart");



				if(role!=null){
			  pw.println("<br/><h3>Hello  "+role+"</h3>");
				}
				pw.println("<h1>Items Currently in  cart </h1>");
				pw.println("<hr>");
				pw.println("<h2>Cart</h2>");
				HashMap<String, List<Integer>> items = ekart.getCartItems();
				pw.println("<table  frame='box' rules='rows'>");
				pw.println(
						"<tr><td></td><td>Product</td><td>Price&nbsp&nbsp&nbsp&nbsp</td><td>Quantity</td><td></td>");
				for (Map.Entry<String, List<Integer>> entry : items.entrySet()) {
					String key = entry.getKey();
					List<Integer> values = entry.getValue();
					pw.println("<form action='deleteitem'><input type='hidden' name='name' value='" + key + "'>");
					pw.println("<tr><td><img src ='images/" + key + ".jpg' width = '100' height = '100'><td>" + key
							+ " - </td><td>" + "$" + values.get(0) + "</td>");
					pw.println("<td><select name='" + key
							+ "'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select></td>");
					pw.println("<td><input type='submit' name='action1' value='RemoveItem'></td></tr>");
				}
				pw.println("<tr ><td align='center' colspan='4'><input type='submit' name='action1' value='CheckOut'></td></tr></form>");
				pw.println("<tr>");
				pw.println("<td>");
				pw.println("</td>");
				pw.println("</tr>");
				pw.println("</table>");




		}

		if (action.equals("CheckOut"))

		{


			cart ekart;
			ekart = (cart) session.getAttribute("cart");
			HashMap<String, List<Integer>> items = ekart.getCartItems();
			for (Map.Entry<String, List<Integer>> entry : items.entrySet()) {
				String key = entry.getKey();
				List<Integer> values = entry.getValue();
				int quantity = Integer.parseInt(request.getParameter(key));
				ekart.addToCart(key, values.get(0), quantity);
			}

			int amount = 0, price = 0, quantity = 0;
			for (Map.Entry<String, List<Integer>> entry : items.entrySet()) {
				String key = entry.getKey();
				List<Integer> values = entry.getValue();
				price = values.get(0);
				quantity = values.get(1);
				amount = amount + price * quantity;
			}

			String userid = (String) session.getAttribute("userid");
			String roleOne = (String) session.getAttribute("role");
			if (roleOne == null) {
				pw.println("<br /><h1>Login required for Checkout</h1>");
			}

			else {


				pw.println("<form method = 'get' action = 'checkoutprocess'>");


				pw.println("<table><tr>");
				if (roleOne.equals("SalesMngr")) {
					pw.println("<td> User Id</td>");
					pw.println("<td><input type='text' name='userid'  required></td>");
					pw.println("</tr>");
				}

				else {
					pw.println("<td> User Id</td>");
					pw.println("<td><input type='text' name='userid' value=" + userid + " required></td>");
					pw.println("</tr>");
				}
				pw.println("<tr>");
				pw.println("<td> Full Name</td>");
				pw.println("<td><input type='text' name='fullname' required></td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td> Address:</td>");
				pw.println("<td><input type='text' name='address' required></td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td> City </td>");
				pw.println("<td><input type='text' name='city' required></td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td> State </td>");
				pw.println("<td><input type='text' name='state' required></td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td> Zip </td>");
				pw.println("<td><input type='text' name='zip' required></td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td> Country </td>");
				pw.println("<td><input type='text' name='country' required></td>");
				pw.println("</tr>");
				//pw.println("<form action='mail' method='get'>");
								pw.println("<tr>");
				pw.println("<td> Email ID </td>");
				pw.println("<td><input type='text' method='get' name='email' required></td>");
				//pw.println("</tr></form>");
				//pw.println("<tr>");
				//pw.println("<td> Flag </td>");
				//pw.println("<td><input type='text' method='get' name='flag' required></td>");
				pw.println("</tr></form>");
				


				pw.println("<tr>");
				pw.println("<td> Amount Payable </td>");
				pw.println("<td>$" + amount + "</td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td> Credit Card Details:-</td>");
				//pw.println("<td><input type='text'  maxlength='4' size='2' required><input type='text'  maxlength='4' size='2' required><input type='text'  maxlength='4' size='2' required><input type='text' maxlength='4' size='2' required>&nbsp&nbspValid Till Month<input type='text'  maxlength='2' size='2'>&nbsp&nbspYear<input type='text'  maxlength='4' size='4' required>&nbsp&nbspCVV<input type='password'  maxlength='3' size='4' required></td>");
				pw.println("<td><input type='text' name='part1' maxlength='4' size='2' required><input type='text' name='part2' maxlength='4' size='2' required><input type='text' name='part3' maxlength='4' size='2' required><input type='text' name='part4' maxlength='4' size='2' required>&nbsp&nbspValid Till Month<input type='text'  maxlength='2' size='2'>&nbsp&nbspYear<input type='text'  maxlength='4' size='4' required>&nbsp&nbspCVV<input type='password'  maxlength='3' size='4' required></td>");
				pw.println("</tr>");
				//pw.println("<form action='index.html'><input type='submit' value='send'>");
				pw.println("<tr>");
				pw.println("<td align='center' colspan='2'>");
				pw.println("<tr></td></tr>");
				pw.println("</form>");
				pw.println("<td align='center' colspan='2'><input type=\"submit\"" + "value=\"Make Payment\"> </a>");
				pw.println("</tr>");
				pw.println("</table>");
		
				pw.println("</form>");


			}

		}

		pw.println("</div>");
		pw.println("</section>");
		pw.println("</div>");


		RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
		sd.include(request, response);
		
	}
}
