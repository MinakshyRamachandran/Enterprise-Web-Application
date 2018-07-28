import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class checkoutprocess extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher tn = request.getRequestDispatcher("topnav.html");
		tn.include(request, response);

		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
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
		String fullname = request.getParameter("fullname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String email = request.getParameter("email");
		//Integer flag = (Integer) request.getAttribute("flag");
		String country = request.getParameter("country");
		String cardNumber = " ";
		
		//int orderamt = (Integer)request.getAttribute("orderamt");
		cardNumber = request.getParameter("part1");
		cardNumber= cardNumber+request.getParameter("part2")+request.getParameter("part3")+request.getParameter("part4");
		String s = "";
		s = request.getParameter("address") + "   ";
		s = s + request.getParameter("city") + "   ";
		s = s + request.getParameter("state") + "   ";
		s = s + request.getParameter("zip") + "   ";
		s = s + request.getParameter("country") + "   ";
		Random random = new Random();
		int Low = 1;
		int High = 562566;
		int rand = random.nextInt(High - Low) + Low;
		int orderno = rand;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,0);
		Date date = cal.getTime();
		String DATE_FORMAT = "MM/dd/yyyy hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String deliverydate = sdf.format(date);
		
		
		cart ekart;
		ekart = (cart) session.getAttribute("cart");
		HashMap<String, List<Integer>> items = ekart.getCartItems();
		String userid = (String) session.getAttribute("userid");

		Orders orders = new Orders();
		for (Map.Entry<String, List<Integer>> entry : items.entrySet()) {
			String key = entry.getKey();
			List<Integer> values = entry.getValue();
			//pw.println("Test "+userid +" "+ orderno+ " "+key+" "+ values.get(0)+" "+ values.get(1)+" "+ deliverydate+" "+ s+" "+ address+" "+ city+" "+ state+" "+ zip+" "+ country+" "+ fullname+" "+ cardNumber);
			//Order order = new Order(userid, orderno, key, values.get(0), values.get(1), deliverydate, s);
			Order order = new Order(userid, orderno, key, values.get(0), values.get(1), deliverydate, s, address, city, state, zip, country, fullname, cardNumber, email);//, orderamt);
			ServletContext context = request.getSession().getServletContext();
			orders.addToOrders(order);
			context.setAttribute("orders", orders);
					//pw.println("<p>"+key+"</p>");
			MySQLDataStoreUtilities.insertOrderDetails(order);

		}
		session.removeAttribute("cart");
		if(role!=null){
		pw.println("<br/><h3>Hello  "+role+"</h3>");
		}

		pw.println("<h3><br /><br />Hi "+fullname+" Your Order No " + orderno+ ", has been Placed Succesfully. The order is placed on " + deliverydate + " and will be delivered in 3 days </h3><br /><br />");
		
		pw.println("</div>");
		pw.println("</section>");
		pw.println("</div>");

		pw.println("<aside class='sidebar'>");
		pw.println("<ul>");
		pw.println("<li>");
		pw.println("<h4>Products</h4>");
		pw.println("<ul>");
		pw.println("<li><a href='tees'>T Shirts</a></li>");
		pw.println("<li><a href='shorts'>Shorts</a></li>");
		pw.println("<li><a href='Shirts'>Shirts</a></li>");
		pw.println("<li><a href='pants'>Pants</a></li>");
		pw.println("<li><a href='suits'>Suit</a></li>");

		pw.println("</ul>");
		pw.println("</li>");
		pw.println("<li>");
		pw.println("<h4>About us</h4>");
		pw.println("</li>");
		pw.println("<li>");

		pw.println("<ul>");

		pw.println("</ul></li></ul></aside>");
		pw.println("<div class='clear'></div>");


		pw.println("<br /> <br />");
	}
 }
