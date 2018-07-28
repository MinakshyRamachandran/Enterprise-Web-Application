import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class Autofill extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			StringBuffer sb = new StringBuffer();
			String searchText = request.getParameter("searchText");
			String action = request.getParameter("action");

			if (action.equals("findMatch")){

				if (!searchText.trim().equals("")){
					AjaxUtility aUtility = new AjaxUtility();
					sb = aUtility.findMatchingProducts(searchText.trim());
					if(sb != null && !sb.equals("")){
						
						response.setContentType("text/xml");
						response.setHeader("Cache-Control", "no-cache");
						response.getWriter().write("<products>" + sb.toString() + "</products>");
					}
				}
			}

			if (action.equals("lookup")){
				String productId = request.getParameter("productId");
				response.sendRedirect(request.getContextPath()+"/prodinfo?model="+productId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
