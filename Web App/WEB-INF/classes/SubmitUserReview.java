import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.*;

public class SubmitUserReview extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{

				Review review = new Review();
				review.setProductMN(request.getParameter("productMN"));
				review.setProductC(request.getParameter("productC"));
				review.setProductId((String)request.getSession(false).getAttribute("itemId"));
				review.setProductP(Double.valueOf(request.getParameter("productP")));
				review.setProductRN(request.getParameter("productRN"));
				review.setProductRZ(request.getParameter("productRZ"));
				review.setProductRC(request.getParameter("productRC"));
				review.setProductRS(request.getParameter("productRS"));
				review.setProductOS(request.getParameter("productOS"));
				review.setProductMrN(request.getParameter("productMrN"));
				review.setProductMR(request.getParameter("productMR"));
				review.setProductRvU(request.getParameter("productRvU"));
				review.setProductRvA(request.getParameter("productRvA"));
				review.setProductRvG(request.getParameter("productRvG"));
				review.setProductRvO(request.getParameter("productRvO"));
				review.setProductRvD(request.getParameter("productRvD"));
				review.setProductRvS(request.getParameter("productRvS"));
				review.setProductRvB(request.getParameter("productRvB"));
				review.setProductRvR(Integer.valueOf(request.getParameter("productRvR")));

				MongoDBDataStoreUtilities.insertUserReview(review);



				if(request.getParameter("pageId").equals("product")){
					request.getSession(false).removeAttribute("itemId");
					Boolean b = true;
					response.sendRedirect(request.getContextPath()+"/prodinfo?product_id="+review.getProductId()+"&reviewed="+b);
				}
				else if(request.getParameter("pageId").equals("accessory")){
					String product_id = (String)request.getSession(false).getAttribute("accmainitemId");
					request.getSession(false).removeAttribute("itemId");
					request.getSession(false).removeAttribute("accmainitemId");
					response.sendRedirect(request.getContextPath()+"/showaccessoryinfo?accessory_id="+review.getProductId()+"&product_id="+product_id);
				}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
