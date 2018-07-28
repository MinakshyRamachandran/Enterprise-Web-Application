import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.HashMap;
import java.util.Map;



public class register extends HttpServlet {
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
	pw.println("<li class='start selected'><a href='register'>Register</a></li>");
	pw.println("<li class='' ><a href='login'>Sign in</a></li>");
	}
	else
	{
		//pw.println("<li class=''><a href='#'>Hello  "+role+"</a></li>");
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

  RequestDispatcher f1 = request.getRequestDispatcher("addnewuser.html");
  f1.include(request, response);





pw.println("</article>");
pw.println("</section>");
pw.println("</div>");

  RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
  sd.include(request, response);
}

}
