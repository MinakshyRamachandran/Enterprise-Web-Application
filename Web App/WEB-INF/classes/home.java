import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;




public class home extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

    PrintWriter pw = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");


  RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
  rd.include(request, response);

	HttpSession session = request.getSession();
	String role=(String)session.getAttribute("role");

	if (role == null)
	{
	pw.println("<li class=''><a href='register'>Register</a></li>");
	pw.println("<li class='' ><a href='login'>Sign in</a></li>");
	}
	  else if (role.equals("SalesMngr")){
		  pw.println("<li class=''><a href='register'>Register Customer</a></li>");

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
  if(role!=null){
  pw.println("<h3>Hello  "+role+"</h3>");
}
	pw.println("<br /><br />");
	pw.println("<p><h2>Check the best products in our site</h2></p>");
  pw.println("<p>Visit often to check for the offers and discounts</p>");
  pw.println("<p></p>");
	pw.println("</article>");
	pw.println("<article class='expanded'>");


	pw.println("<p><h2>Choose from the wide range of products we have</h2></p>");

	pw.println("</article>");
	pw.println("</section>");

  RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
  sd.include(request, response);


}




public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {


    PrintWriter pw = response.getWriter();
	response.setContentType("text/html;charset=UTF-8");

  RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
  rd.include(request, response);

	HttpSession session = request.getSession();
	String role=(String)session.getAttribute("role");

	if (role == null)
	{
	pw.println("<li class=''><a href='register'>Register</a></li>");
	pw.println("<li class='' ><a href='login'>Sign in</a></li>");
	}
	  else if (role.equals("SalesManager")){
		  pw.println("<li class=''><a href='register'>Register a Customer</a></li>");


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
 			pw.println("<li class='' ><a href='viewcart'>Cart(0)</a></li>");
 		}

 		else {
       pw.println("<li class=''><a href='viewcart'>Cart("+mycart.numberofitems()+")</a></li>");
     }

  pw.println("</div>");
	pw.println("</ul>");
	pw.println("</nav>");
	pw.println("<div id='body'>");
	pw.println("<section id='content'>");
	pw.println("<article>");
  if(role!=null){
  pw.println("<h3>Hello  "+role+"</h3>");
}
	pw.println("<br /><br />");
 	pw.println("<br /><br />");
	pw.println("<p><h2>Check the best products in our site</h2></p>");
  pw.println("<p>Visit often to check for the offers and discounts</p>");
  pw.println("<p></p>");
	pw.println("</article>");
	pw.println("<article class='expanded'>");


	pw.println("<p><h2>Choose from the wide range of products we have</h2></p>");
	pw.println("</article>");
	pw.println("</section>");	

  RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
  sd.include(request, response);


}
}
