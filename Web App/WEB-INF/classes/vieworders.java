import java.util.*;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class vieworders extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher tn = request.getRequestDispatcher("topnav.html");
		tn.include(request, response);

		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");

		if (role == null) {
			pw.println("<li class=''><a href='register'>Register</a></li>");
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else if (role.equals("SalesMngr")) {
			pw.println("<li class=''><a href='register'>Register Customer</a></li>");
			//pw.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");

		} else {
			//pw.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}
		pw.println("<li><a href='deliverycheck'>Delivery Check</a></li>");
		pw.println("<li class='start selected' ><a href='vieworders'>View Orders</a></li>");

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
		if (role == null) {
			pw.println("<br /><h1>Login is required to view/place an Order</h1>");

		} else {

		  pw.println("<br /><h3>Hello  "+role+"</h3>");
			String userid = (String) session.getAttribute("userid");


			if (role.equals("SalesManager")) {
				//ServletContext context = request.getSession().getServletContext();
				//Orders orders = (Orders) context.getAttribute("orders");
				HashMap<String,ArrayList<Order>> orders = MySQLDataStoreUtilities.getOrderDetails();

				if (orders.isEmpty()) {
					pw.println("<h3>There are no Orders<h3>");
				}

				else{
					DisplayOrder(pw, userid, orders);
				}
			}
			else {
				HashMap<String,ArrayList<Order>> orders = MySQLDataStoreUtilities.getOrderDetails();
				if (orders.isEmpty()) {
					pw.println("<h3>Orders doesnt exist for this User Id<h3>");
				}
				else{
					DisplayOrder(pw, userid, orders);
				}
			}
		}

		pw.println("</div>");
		pw.println("</section>");
		pw.println("</div>");

		RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
		sd.include(request, response);
	}
	
	public void DisplayOrder(PrintWriter pw, String userid,HashMap<String,ArrayList<Order>> orders){
					String Ordernum = "", productname = "", price = "", quantity = "", shipping = "", delivery;

					pw.println("<table>");
					pw.println(
							"<tr><td></td><td>User Id</td><td>Order Num</td><td>Product Name</td><td>Order Status</td><td>Quantity</td><td>Delivery Date</td><td>Shipping Address</td></tr>");
					/*for (Order order : orders.getOrders()) {*/
						for (Map.Entry<String, ArrayList<Order>> entry : orders.entrySet()) {
								String key = entry.getKey();
							
									ArrayList<Order> order1 = entry.getValue();
									for(Order order : order1){

						pw.println("<form action='cancelorder'>");
						pw.println("<input type='hidden' name='userid' value='" + userid + "'>");
						pw.println("<input type='hidden' name='product' value='" + order.getItemId() + "'>");
						pw.println("<input type='hidden' name='ordernum' value='" + order.getOrdernumber() + "'>");
						pw.println("<input type='hidden' name='delivery' value='" + order.getDeliveryDate() + "'>");
						pw.println("<td><img src ='images/" + order.getItemId()+ ".jpg' width = '100' height = '100'></td>");
						pw.println("<td>" + order.getUserid() + "</td>");
						pw.println("<td>" + order.getOrdernumber() + "</td>");
						pw.println("<td>" + order.getItemId() + "</td>");
						pw.println("<td> Confirmed </td>");// + order.getOrderStatus() + "</td>");
						pw.println("<td>" + order.getQuantity() + "</td>");
						pw.println("<td>" + order.getDeliveryDate() + "</td>");
						pw.println("<td>" + order.getAddress() + "</td>");
						if(order.getFlag()==0){				
					pw.println("<td><input type='submit'  value='Cancel Order'></td></tr>");
				}
				else if(order.getFlag()==2){
					pw.println("<td><label>Out for Delivery, can not be cancelled</label></td>");
				}
				else if(order.getFlag()==1){
					pw.println("<td><label>Delivered attempted. Contact customer care to reschedule a delivery again.</label></td>");
				}
				else if(order.getFlag()==3){
					pw.println("<td><label>Delivered.</label></td>");
				}
				pw.println("</tr>");
						pw.println("</form>");
					}

					}
					pw.println("</table>");

				}
			} 