import java.util.*;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class deliverycheck extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		RequestDispatcher sd = request.getRequestDispatcher("Deliverycheck.html");
		sd.include(request, response);

		HttpSession session = request.getSession();
		
		String val = request.getParameter("btnName");
		String ordernum = request.getParameter("ordernum");
		
		if(val.equalsIgnoreCase("Schedule a Delivery")){
			//update the value in DB to 2
			MySQLDataStoreUtilities.updateOrderDetails(ordernum,2);
		}
		else if(val.equalsIgnoreCase("Delivered")){
			//update the value in DB to 3
			MySQLDataStoreUtilities.updateOrderDetails(ordernum,3);
		}
		else if(val.equalsIgnoreCase("Delivery Attempted")){
			//update the value in DB to 1
			MySQLDataStoreUtilities.updateOrderDetails(ordernum,1);
		}
		else if(val.equalsIgnoreCase("Re-Schedule Delivery")){
			//update the value in DB to 2
			MySQLDataStoreUtilities.updateOrderDetails(ordernum,2);
		}
		
		DisplayOrderTable(pw);
	}
	
	public void DisplayOrderTable(PrintWriter pw){
		
		HashMap<String,ArrayList<Order>> orders = MySQLDataStoreUtilities.getOrderDetails();

		if (orders.isEmpty()) {
			pw.println("<h3>There are no Orders<h3>");
		}
		else{
			String Ordernum = "", productname = "", price = "", quantity = "", shipping = "", delivery;

			pw.println("<table>");
			pw.println(
			"<tr><td>User Id</td><td>Order Num</td><td>Product Name</td><td>Shipping Address</td><td>Status</td></tr>");
			/*for (Order order : orders.getOrders()) {*/
			for (Map.Entry<String, ArrayList<Order>> entry : orders.entrySet()) {
				String key = entry.getKey();
			
				ArrayList<Order> order1 = entry.getValue();
				for(Order order : order1){
					pw.println("<form action='/Falcon/deliverycheck' method='POST'>");
					/*pw.println("<input type='hidden' name='userid' value='" + order.getUserid() + "'>"); */
					pw.println("<input type='hidden' name='ordernum' value='" + order.getOrdernumber() + "'>"); 
					pw.println("<td>" + order.getUserid() + "</td>");
					pw.println("<td>" + order.getOrdernumber() + "</td>");
					pw.println("<td>" + order.getItemId() + "</td>");
					//pw.println("<td> Confirmed </td>");// + order.getOrderStatus() + "</td>");
					//pw.println("<td>" + order.getQuantity() + "</td>");
					//pw.println("<td>" + order.getDeliveryDate() + "</td>");
					pw.println("<td>" + order.getAddress() + "</td>");
					if(order.getFlag()==3){
						//Delivered
						pw.println("<td><label>Delivered</label></td></tr>");
					}
					else if(order.getFlag()==0){
						//Ordered. Yet to schedule a delivery.
						pw.println("<td><label>Ordered &nbsp;&nbsp;&nbsp; </label></td>");
						pw.println("<td><input type='submit' name='btnName'  value='Schedule a Delivery'></td></tr>");
					}
					else if(order.getFlag() == 2) {
						// Out for delivery. 
						pw.println("<input type='hidden' name='changeto' value=''>");
						pw.println("<td><label>Out for Delivery &nbsp;&nbsp;&nbsp; </label></td>");
						pw.println("<td><input type='submit' name='btnName' value='Delivered'> &nbsp;&nbsp;&nbsp;");
						pw.println("<input type='submit' name='btnName'  value='Delivery Attempted'></td></tr>");
					}
					else if(order.getFlag()==1){
						// Reschedule a Delivery
						pw.println("<input type='hidden' name='changeto' value='2'>");
						pw.println("<td><label>Delivered Attempted. &nbsp;&nbsp;&nbsp; </label></td>");
						pw.println("<td><input type='submit' name='btnName' value='Re-Schedule Delivery'></td></tr>");
					}
					
					
					pw.println("</form>");
				}
			}
			pw.println("</table>");
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		RequestDispatcher sd = request.getRequestDispatcher("Deliverycheck.html");
		sd.include(request, response);

		HttpSession session = request.getSession();
		
		DisplayOrderTable(pw);
		
	}
}
		
	
