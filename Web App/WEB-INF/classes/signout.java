import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class signout extends HttpServlet {


  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
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
  pw.println("<li class=''><a href='tees'>T-Shirts</a></li>");
  pw.println("<li class=''><a href='shorts'>Shorts</a></li>");
	pw.println("<li class=''><a href='Shirts'>Shirts</a></li>");
	pw.println("<li class=''><a href='pants'>Pants</a></li>");


  pw.println("<li class=''><a href='suits'>Suit</a></li>");
	HttpSession session = request.getSession(false);


		pw.println("<li class='' ><a href='login'>Sign In</a></li>");

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
  pw.println("<div id = 'container'>");
	if(session!=null)
	session.invalidate();
		pw.println("<br /><br /><h2>You are logged out !<br />Please use Sign in from the Navigation bar to Sign in again. </h2><br /><br />");
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
    pw.println("<footer>");
		pw.println("<div class='footer-content'>");
		pw.println("<div class='clear'></div>");
		pw.println("</div>");
		pw.println("<div class='footer-bottom'>");
		pw.println("<p>&copy Falcon </p>");
		pw.println("</div>");
		pw.println("</footer>");
		pw.println("</div>");
		pw.println("</body>");
		pw.println("</html>");

}

}
