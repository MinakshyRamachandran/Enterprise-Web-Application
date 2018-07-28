import java.io.*;
import javax.servlet.http.*;
import java.util.*;

public class loadProductsToDB extends HttpServlet {

	public void init(){

		try{
			MySQLDataStoreUtilities.TruncateProducts();
			Map<String,Product> pMap = (HashMap<String,Product>)ProductUtility.InitiateMap();
				MySQLDataStoreUtilities.insertProducts(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
