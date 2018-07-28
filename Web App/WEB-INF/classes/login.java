import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
	public static Map<String, String> users;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
		rd.include(request, response);

		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");

		if (role == null) {
			pw.println("<li class=''><a href='register'>Register</a></li>");
			pw.println("<li class='start selected' ><a href='login'>Sign in</a></li>");
		} else {
			pw.println("<li class=''><a href='#'>Hello  " + role + "</a></li>");
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
	      pw.println("<liclass='' ><a href='viewcart'>Cart("+mycart.numberofitems()+")</a></li>");
	    }
		pw.println("</div>");
		pw.println("</ul>");
		pw.println("</nav>");

		pw.println("<div id='body'>");
		pw.println("<section id='content'>");
		pw.println("<article>");

		pw.println("<form  method='Post' action='login'>");
		pw.println("<table>");

		pw.println("<tr>");
		pw.println("<td>");
		pw.println("Login as&nbsp&nbsp");
		pw.println(
				"<input type='radio' name='usertype' value='Customer' checked='checked'>Customer&nbsp&nbsp&nbsp <input type='radio' name='usertype' value='storemanager'>Store Manager&nbsp&nbsp&nbsp <input type='radio' name='usertype' value='salesmanager'>Sales Manager&nbsp&nbsp&nbsp<input type='radio' name='usertype' value='delivery'>Delivery ");
		pw.println("</td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>");
		pw.println("User Id &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp :");
		pw.println("<input type='text' name='userid' required/></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>");
		pw.println("Password&nbsp&nbsp&nbsp:");
		pw.println("<input type='password' name='password' required/></td>");
		pw.println("</tr>");
		pw.println("<tr><td ><input type='submit' value='Login'></td></tr>");
		pw.println("</table>");
		pw.println("</form>");

		pw.println("</div>");
		pw.println("</article>");
		pw.println("</section>");



		RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
		sd.include(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();




		response.setContentType("text/html;charset=UTF-8");
		int htmlvalue=9;
		String user_id="";

		RequestDispatcher rd1 = request.getRequestDispatcher("topnav.html");
		rd1.include(request, response);

		HttpSession session = request.getSession();
		
		ServletContext context = session.getServletContext();
		if(context.getAttribute("products")!=null){

		}else{
			HashMap<String,Product> pMap = (HashMap<String,Product>)ProductUtility.getProductMap();
			 session.setAttribute("pMap",pMap);
		}


				try{
					String customertype = request.getParameter("usertype");
						user_id = request.getParameter("userid");
						String password = request.getParameter("password");

						if(user_id.equals("admin"))	{

								if(password.equals("admin")){

								session.setAttribute("role", "StoreManager");
								session.setAttribute("userid",user_id);
								htmlvalue=6;
								response.sendRedirect(request.getContextPath()+"/Reports.html");
								
							}
							else{
								htmlvalue=7;
								pw.println("<p>Invalid Login Attempt, Please Login again</p>");
								//response.sendRedirect(request.getContextPath()+"/invalidlogin.html");
							}
						}
						else if(user_id.equals("delivery"))	{

								if(password.equals("delivery")){

								session.setAttribute("role", "Order Delivery");
								session.setAttribute("userid",user_id);
								htmlvalue=8;
								response.sendRedirect(request.getContextPath()+"/Delivery.html");
								
							}
				
							else{
								htmlvalue=7;
								pw.println("<p>Invalid Login Attempt, Please Login again</p>");
								//response.sendRedirect(request.getContextPath()+"/invalidlogin.html");
							}
						}

						else{

								HashMap<String, User> userMap = MySQLDataStoreUtilities.getUsers();
								if(userMap != null){

									User user  = userMap.get(user_id);
									if(user != null){

										if(user.getPassword().equals(password)){
											if(user.getUserType().equalsIgnoreCase("Customer")){
												session.setAttribute("userid",user_id);
												session.setAttribute("userTypeInfo",user.getUserType());
												session.setAttribute("role", "Customer");
												htmlvalue=1;
											}
											else if(user.getUserType().equalsIgnoreCase("Salesmanager")){
												session.setAttribute("userid",user_id);
												session.setAttribute("userTypeInfo",user.getUserType());
												session.setAttribute("role", "SalesManager");
												htmlvalue=2;
											}
										}
										else{
											htmlvalue =3;
										}
									}
									else{
										htmlvalue=4;
									}

								}
								else{
									htmlvalue=5;
								}
						}

				}catch (Exception e) {
					e.printStackTrace();
				}



		String roleOne = (String) session.getAttribute("role");

		if (roleOne == null) {
			pw.println("<li class=''><a href='register'>Register</a></li>");
			pw.println("<li class='start selected' ><a href='login'>Sign in</a></li>");
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
	      pw.println("<li class='' ><a href='viewcart'>Cart("+mycart.numberofitems()+")</a></li>");
	    }
		pw.println("</div>");
		pw.println("</ul>");
		pw.println("</nav>");

		pw.println("<div id='body'>");
		pw.println("<section id='content'>");
		pw.println("<article>");

//here there should be try and catch


switch(htmlvalue) {
				case 1:
						pw.println("<p>Welcome "+user_id+" ! Shop for yourself using the Menu options</p>");
						break;

				case 2:
						pw.println("<p>Welcome "+user_id+". Go ahead and add, delete or update products !</p>");
						break;

				case 3:
						pw.println("<p>Userid and Password Doesnot match, Please check the Username and Password and Login again !</p>");
						break;

				case 4:
						pw.println("<p>User Id Does not Exist, Please try again !</p>");
						break;

				case 5:
						pw.println("<p>Please check Database Server and Login again !</p>");
						break;

						case 6:
						pw.println("<p>Welcome to Admin !</p>");
						break;

						case 7:
						pw.println("<p>Please check the Username and Password and Login again !</p>");
						break;
						
						case 8:
						pw.println("<p>Welcome to Deliverer! </p>");
						break;

				default:
					pw.println("<p>Invalid Login Attempt, Please Login again</p>");
		}

		pw.println("</div>");
		pw.println("</article>");
		pw.println("</section>");



		RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
		sd.include(request, response);

	}

}
