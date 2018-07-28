import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class managestore extends HttpServlet {

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
		pw.println("<li  class=''><a href='managestore'>Add Products</a></li>");
		pw.println("<li class=''><a href='modifyproduct'>Update Product</a></li>");
		pw.println("<li class=''><a href='deleteproduct'>Delete Product</a></li>");
		pw.println("<li class=''><a href='Reports.html'>Reports</a></li>");
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
			pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		} else {

			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("</ul>");
		pw.println("</nav>");
		if(role!=null){
		pw.println("<br /><h3>Hello  "+role+"</h3>");
		}
		pw.println("<form action='addnewproduct'>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<td> Product ID :-</td>");
		pw.println("<td> <input type='text' name='product_id' ></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td> Display Under:-</td>");
		pw.println("<td><select name='display_under'><option value='tees'>T Shirts</option><option value='sHort'>Shortss</option><option value='Shirt'>Shirts</option><option value='Pant'>Pants</option><option value='SUit'>SUits</option></select></td>");
		pw.println("</tr>");

		pw.println("<tr>");
		pw.println("<td> Product Model Name :-</td>");
		pw.println("<td> <input type='text' name='product_name' ></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td> Product Price :- </td>");
		pw.println("<td> $<input type='text' name='price' ></td>");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td> Product Description:-</td>");
		pw.println("<td>  <input type='text' name='description' ></td>");
		pw.println("</tr>");

		pw.println("<tr>");
		pw.println("<td colspan='2' align='center'>");
		pw.println("<input class = 'submit-button' type = 'submit'  value = 'Add New Product'></form>");
		pw.println("</td>");
		pw.println("</tr>");
		pw.println("</tr></table>");

		pw.println("</div>");
		pw.println("</article>");
		pw.println("</section>");

		//RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
	  //sd.include(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		pw.println("<li  class=''><a href='managestore'>Add Products</a></li>");
		pw.println("<li class=''><a href='modifyproduct'>Modify Product</a></li>");
		pw.println("<li class=''><a href='deleteproduct'>Delete Product</a></li>");
		pw.println("<li class=''><a href='Reports.html'>Reports</a></li>");
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null)
		{
		pw.println("<li class=''><a href='register'>Register</a></li>");
		pw.println("<li class='' ><a href='login'>Sign in</a></li>");
		}
		  else if (role.equalsIgnoreCase("StoreManager")){
	      pw.println("<li class=''><div class='dropdown'><a>More Options</a >");
	      pw.println("<div class='dropdown-content' >");
	      //pw.println("<a href='register'>Register Customer</a >");
				pw.println("<a href='inventory'>Inventory </a >");
	      pw.println("<a href='salesreport'>Sales Report </a >");
	      pw.println("<a href='signout'> Sign Out </a>");
	      pw.println("</div >");
	      pw.println("</div >");
	      pw.println("</li >");
	      	  } else
		{
			pw.println("<li class='' ><a href='signout'>Sign Out</a></li>");
		}

		pw.println("</ul>");
		pw.println("</nav>");

		pw.println("<div id='body'>");
		pw.println("<section id='content'>");
		pw.println("<article>");

				pw.println("<form action='addnewproduct'>");
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<td> Product ID :-</td>");
				pw.println("<td> <input type='text' name='product_id' ></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td> Display Under:-</td>");
				pw.println("<td><select name='display_under'><option value='tees'>T Shirts</option><option value='sHort'>Shortss</option><option value='Shirt'>Shirts</option><option value='Pant'>Pants</option><option value='SUit'>SUits</option></select></td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td> Product Model Name :-</td>");
				pw.println("<td> <input type='text' name='product_name' ></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td> Product Price :- </td>");
				pw.println("<td> $<input type='text' name='price' ></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td> Product Description:-</td>");
				pw.println("<td>  <input type='text' name='description' ></td>");
				pw.println("</tr>");

				pw.println("<tr>");
				pw.println("<td colspan='2' align='center'>");
				pw.println("<input class = 'submit-button' type = 'submit'  value = 'Add New Product'></form>");
				pw.println("</td>");
				pw.println("</tr>");
				pw.println("</tr></table>");
		pw.println("</div>");
		pw.println("</article>");
		pw.println("</section>");

		//RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
	  //sd.include(request, response);

	}

}
