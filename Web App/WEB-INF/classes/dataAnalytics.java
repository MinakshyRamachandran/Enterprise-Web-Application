import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class dataAnalytics extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		int htmlvalue=9;
		String user_id="";

		RequestDispatcher rd1 = request.getRequestDispatcher("DataAnalytics.html");
		rd1.include(request, response);

		//HttpSession session = request.getSession();		
		

		try{
			//if(session.getAttribute("role").equals("StoreManager"))
			{
				String[] projectFields = request.getParameter("txtProjectList").split(";");	
				String filterField = request.getParameter("field");
				String filter = request.getParameter("filter");
				String value = request.getParameter("filterValue");
				String sortField = request.getParameter("sortField");
				String sortType = request.getParameter("sortType");
				String strLimit = request.getParameter("limit");
				int limit =0;
				if(strLimit != null)
					limit = Integer.parseInt(strLimit);
				pw.println("<table>");
				pw.println("<tr>");
				for(String projectfield: projectFields){
					
					pw.print("<th> " + projectfield + "</th>");
				}
				pw.println("</tr>");
//pw.println(projectFields);				
				ArrayList<ArrayList<DynamicResult>> result =  MongoDBDataStoreUtilities.DynamicQuery(projectFields, filterField, filter, value, sortField, sortType, limit);
				if(result != null && result.size() >0)
				{
					for(ArrayList<DynamicResult> dynamicResult : result){
						pw.println("<tr>");
					//ArrayList<DynamicResult> first = result.get(0);
					for(DynamicResult kvp : dynamicResult)
					{
						pw.println("<td>" + kvp.getValue() + "</td>");
					}
					pw.println("</tr>");
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
