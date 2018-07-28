
import java.util.Map;
import java.util.HashMap;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class registerdata extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();

		RequestDispatcher rd = request.getRequestDispatcher("topnav.html");
	  rd.include(request, response);
		pw.println("</ul>");
		pw.println("</nav>");



		pw.println("<div id='body'>");
	  pw.println("<section id='content'>");
	  pw.println("<article>");

		HttpSession session=request.getSession(false);

		//pw.println("<h1>Test "+session+"</h1>");
try{
	String firstName = request.getParameter("first_name");
	String lastName = request.getParameter("last_name");
	String userId = request.getParameter("user_id");
	String password = request.getParameter("password");
	String repassword = request.getParameter("repassword");
	String userType = request.getParameter("user_type");



	HashMap<String, User> users = MySQLDataStoreUtilities.getUsers();

	if(users != null){
		if(!users.containsKey(userId)){
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserId(userId);
			user.setPassword(password);
			user.setrePassword(repassword);
			user.setUserType(userType);
			MySQLDataStoreUtilities.insertUser(user);

			if(session==null){
				session=request.getSession();

				if(userType.equalsIgnoreCase("Customer")){
					session.setAttribute("userid",userId);
					session.setAttribute("userTypeInfo",userType);

						pw.println("<h2>You are Succesfully Registered as a User ! <br/>Please click <a href='login'>Login</a> to access</h2>");

					/*RequestDispatcher n1 = request.getRequestDispatcher("validlogin.html");
				  n1.include(request, response);*/
				}
				else if(userType.equalsIgnoreCase("Salesman")){
					session.setAttribute("userid",userId);
					session.setAttribute("userTypeInfo",userType);

						pw.println("<h2>You are Succesfully Registered as Salesman ! <br/>Please click <span><a href='login'>Login</a></span> to access</h2>");
					/*RequestDispatcher n2 = request.getRequestDispatcher("salesmanvalidlogin.html");
				  n2.include(request, response);*/
				}
			}
			else{
				if(userType.equalsIgnoreCase("Customer")){
					session.setAttribute("userid",userId);
					session.setAttribute("userTypeInfo",userType);

						pw.println("<h2>You are Succesfully Registered as a User ! <br/>Please click <span><a href='login'>Login</a></span> to access</h2>");

					/*RequestDispatcher n1 = request.getRequestDispatcher("validlogin.html");
					n1.include(request, response);*/
				}
				else if(userType.equalsIgnoreCase("Salesman")){
					session.setAttribute("userid",userId);
					session.setAttribute("userTypeInfo",userType);

						pw.println("<h2>You are Succesfully Registered as Salesman ! <br/>Please click <span><a href='login'>Login</a></span> to access</h2>");
					/*RequestDispatcher n2 = request.getRequestDispatcher("salesmanvalidlogin.html");
					n2.include(request, response);*/
				}

			}
		}
		else{
			if(session==null){

					pw.println("<h2>Error while Registering! please <span><a href='register'>Register</a></span> Again</h2>");
				/*RequestDispatcher n4 = request.getRequestDispatcher("invalidsignup.html");
				n4.include(request, response);*/
			}
			else{

					pw.println("<h2>User Registration Invalid ! please <span><a href='register'>Register</a></span> Again</h2>");
				/*RequestDispatcher n5 = request.getRequestDispatcher("invalidadduser.html");
				n5.include(request, response);*/

			}
		}
	}
	else{
		if(session==null){

				pw.println("<h2>Server Down while Registering !</h2>");
			/*RequestDispatcher n6 = request.getRequestDispatcher("signupserverdown.html");
			n6.include(request, response);*/
		}
		else{

				pw.println("<h2>Server Down while adding User !</h2>");
			/*RequestDispatcher n7 = request.getRequestDispatcher("adduserserverdown.html");
			n7.include(request, response);*/
		}
	}


} catch (Exception e) {
	e.printStackTrace();
}


pw.println("</article>");
pw.println("</section>");
RequestDispatcher sd = request.getRequestDispatcher("sidebarfooter.html");
sd.include(request, response);
}


}
