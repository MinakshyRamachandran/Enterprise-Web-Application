import java.util.*;

public class ProductUtility{
	
	private static Map<String,Product> productMap = null;

		public static Map<String,Product> getProductMap(){


			
			productMap = MySQLDataStoreUtilities.getProductDetails();
			//SAXParserForProducts prdSaxParser = new SAXParserForProducts("ProductCatalog.xml");
			//productMap = (HashMap<String,Product>) prdSaxParser.getProducts();
			//(new MySQLDataStoreUtilities()).insertProducts(productMap);


			return productMap;

	}

	public static Map<String,Product> InitiateMap(){



			//productMap = (new MySQLDataStoreUtilities()).getProductDetails();
			SAXParserForProducts prdSaxParser = new SAXParserForProducts("ProductCatalog.xml");
			productMap = (HashMap<String,Product>) prdSaxParser.getProducts();
			//(new MySQLDataStoreUtilities()).insertProducts(productMap);


			return productMap;

	}



}
