import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class MailApp extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");


  RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
  rd.include(request, response);
  out.println("<br/>");
      
		String to = "snamachivayan@hawk.iit.edu";
        String subject = request.getParameter("subject");
        String message =  request.getParameter("message");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        SendMail.send(to,subject, message, user, pass);
		out.println("<br />");
  out.println("Mail send successfully");
  RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
  sd.include(request, response);	
        out.println("<br />");
		
    }   
}