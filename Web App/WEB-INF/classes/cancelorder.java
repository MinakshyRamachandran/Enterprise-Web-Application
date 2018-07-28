
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class cancelorder extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	 PrintWriter pw = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");


					RequestDispatcher tn = request.getRequestDispatcher("topnav.html");
					tn.include(request, response);

		HttpSession session = request.getSession();
	String role=(String)session.getAttribute("role");
	if (role == null)
	{
	pw.println("<li class='' ><a href='login'>Sign in</a></li>");
	}
	else
	{

		pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
	}

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

	String userid = request.getParameter("userid");
 	String model= request.getParameter("product");
	String ordernum= request.getParameter("ordernum");
	String delivery= request.getParameter("delivery");

	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH, 5);
	Date date = cal.getTime();
	String DATE_FORMAT = "MM/dd/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	String checkdate = sdf.format(date);
HashMap<String,ArrayList<Order>> myorders = MySQLDataStoreUtilities.getOrderDetails();

	try{
	Date del = sdf.parse(delivery);
	Date chk = sdf.parse(checkdate);


	if(del.compareTo(chk)>0)

		{



			if(myorders.isEmpty())
			{
				pw.println("<h3><br><br> No items have been ordered <br><br></h3>");
			}

			else
			{


					for (Map.Entry<String, ArrayList<Order>> entry : myorders.entrySet()) {
							String key = entry.getKey();
						if(key.equalsIgnoreCase(userid)){
								ArrayList<Order> order1 = entry.getValue();
								for(Order order : order1){
									if(order.getOrdernumber().equals(ordernum)){
									MySQLDataStoreUtilities.cancelOrder(ordernum);
									//pw.println("<h3> ordernum: "+ordernum +order.getOrdernumber()+" </h3>");
								}
								}
							}
						}

				/*
				Iterator<Order> itr= orders.getOrders().iterator();
				//Order order : orders.getOrders()
			   while(itr.hasNext()){
					 //order.getOrdernumber().equals(ordernum)
				if( itr.next().getOrdernumber().equals(ordernum) ){
					//orders.getOrders().remove(order);
					itr.remove();
				}
			}
			context.setAttribute("orders", orders);

*/
			pw.println("<h3> Your Order has been cancelled ! </h3>");
			}
		}


		else

			{

				pw.println("<h3><br><br>The order has been shipped for delivery. We will not be able to process your request for cancellation!<br><br></h3>");
			}


	}
	catch (Exception e)
	{
	}

	pw.println("</div>");
	pw.println("</section>");
	pw.println("</div>");


			RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
		  sd.include(request, response);
}
}
