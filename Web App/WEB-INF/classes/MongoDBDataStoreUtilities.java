import com.mongodb.*;
import java.util.*;

public class MongoDBDataStoreUtilities {

	public static DBCollection userReviews;

	public static int getConnection(){
		try{
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("falcon");
			userReviews = db.getCollection("userReviews");
			return 1;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public static void insertUserReview(Review review){
		try{

			if(getConnection() == 1){
				BasicDBObject doc = new BasicDBObject("title", "userReviews").
				append("productMN", review.getProductMN()).
				append("productC", review.getProductC()).
				append("productId", review.getProductId()).
				append("productP", review.getProductP()).
				append("productRN", review.getProductRN()).
				append("productRZ", review.getProductRZ()).
				append("productRC", review.getProductRC()).
				append("productRS", review.getProductRS()).
				append("productOS", review.getProductOS()).
				append("productMrN", review.getProductMrN()).
				append("productMR", review.getProductMR()).
				append("productRvU", review.getProductRvU()).
				append("productRvA", review.getProductRvA()).
				append("productRvG", review.getProductRvG()).
				append("productRvO", review.getProductRvO()).
				append("productRvD", review.getProductRvD()).
				append("productRvS", review.getProductRvS()).
				append("productRvB", review.getProductRvB()).
				append("productRvR", review.getProductRvR());
				userReviews.insert(doc);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static HashMap<String, ArrayList<Review>> getUserReviews(){
		try{
			if(getConnection()== 1){
				HashMap<String,ArrayList<Review>> userReviewsMap = new HashMap<String,ArrayList<Review>>();

				DBCursor cursor = userReviews.find();
				while (cursor.hasNext()){
					BasicDBObject obj = (BasicDBObject) cursor.next();
					if(!userReviewsMap.containsKey(obj.getString("productId"))){
						ArrayList<Review> reviewsArr = new ArrayList<Review>();
						userReviewsMap.put(obj.getString("productId"), reviewsArr);
					}
					ArrayList<Review> reviewsArrList = userReviewsMap.get(obj.getString("productId"));

					Review review = new Review();
					review.setProductMN(obj.getString("productMN"));
					review.setProductC(obj.getString("productC"));
					review.setProductId(obj.getString("productId"));
					review.setProductP(Double.valueOf(obj.getString("productP")));
					review.setProductRN(obj.getString("productRN"));
					review.setProductRZ(obj.getString("productRZ"));
					review.setProductRC(obj.getString("productRC"));
					review.setProductRS(obj.getString("productRS"));
					review.setProductOS(obj.getString("productOS"));
					review.setProductMrN(obj.getString("productMrN"));
					review.setProductMR(obj.getString("productMR"));
					review.setProductRvU(obj.getString("productRvU"));
					review.setProductRvA(obj.getString("productRvA"));
					review.setProductRvG(obj.getString("productRvG"));
					review.setProductRvO(obj.getString("productRvO"));
					review.setProductRvD(obj.getString("productRvD"));
					review.setProductRvS(obj.getString("productRvS"));
					review.setProductRvB(obj.getString("productRvB"));
					review.setProductRvR(Integer.valueOf(obj.getString("productRvR")));

					reviewsArrList.add(review);
				}
				return userReviewsMap;
			}
			else{
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<ArrayList<DynamicResult>> DynamicQuery(String[] projectFields, String field, String filter, String filterValue, String sortField, String sortMode, int limit){
		try{
			if(getConnection()== 1){
				ArrayList<ArrayList<DynamicResult>> dynamicResult = new ArrayList<ArrayList<DynamicResult>>();

				DBCursor cursor;
				if(filterValue != null)
				{
					if(filter.equals("equal")){
						BasicDBObject eqQry = new BasicDBObject();
						eqQry.put(field, filterValue);
						cursor = userReviews.find(eqQry);
					}
					else{
						BasicDBObject neqQry = new BasicDBObject();
						neqQry.put(field, new BasicDBObject("$ne", filterValue));
						cursor = userReviews.find(neqQry);
					}
				}
				else
				{	cursor = userReviews.find();}
			if(sortField != null)
			{
				int sortType =1;
				if(sortMode.equals("Desc"))
					sortType= -1;
				cursor.sort(new BasicDBObject(sortField, sortType));
			}
			if(limit > 0)
			{
				cursor.limit(limit);
			}
				while (cursor.hasNext()){
					BasicDBObject obj = (BasicDBObject) cursor.next();
					ArrayList<DynamicResult> itrDynamicResult = new ArrayList<DynamicResult>();
					for(String projectfield: projectFields){
						itrDynamicResult.add(new DynamicResult(projectfield, obj.getString(projectfield)));
					}
					dynamicResult.add(itrDynamicResult);
				}
				return dynamicResult;
			}
			return null;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static LinkedHashMap<String, String> getTop5MostLikedProducts(){
		try{
			if(getConnection()== 1){
				DBObject groupFields = groupFields = new BasicDBObject("_id", 0);
				groupFields.put("_id", "$productId");
				groupFields.put("avgRating", new BasicDBObject("$avg", "$productRvR"));
				groupFields.put("productModelName", new BasicDBObject("$push", "$productMN"));
				DBObject group = new BasicDBObject("$group", groupFields);

				DBObject projectFields = new BasicDBObject("_id", 0);
				projectFields.put("productId", "$_id");
				projectFields.put("avgRating", "$avgRating");
				projectFields.put("productModelName", "$productModelName");
				DBObject project = new BasicDBObject("$project", projectFields);

				DBObject sort = new BasicDBObject();
				sort.put("avgRating",-1);
				DBObject orderby=new BasicDBObject("$sort",sort);

				DBObject limit=new BasicDBObject("$limit",5);

				AggregationOutput aggregate = userReviews.aggregate(group,project,orderby,limit);

				LinkedHashMap<String, String> mostLikedProductsMap = new LinkedHashMap<String, String>();

				for (DBObject queryResult : aggregate.results()) {
					BasicDBObject obj = (BasicDBObject) queryResult;
					BasicDBList productModelName = (BasicDBList) obj.get("productModelName");
					mostLikedProductsMap.put((String)productModelName.get(0),obj.getString("avgRating"));
				}
				return mostLikedProductsMap;
			}
			else{
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}


	public static LinkedHashMap<String, String> getTop5MostProductsSoldZipcode(){
		try{
			if(getConnection()== 1){
				DBObject groupFields = groupFields = new BasicDBObject("_id", 0);
				groupFields.put("_id", "$productRZ");
				groupFields.put("itemreviewcount", new BasicDBObject("$sum", 1));
				DBObject group = new BasicDBObject("$group", groupFields);

				DBObject projectFields = new BasicDBObject("_id", 0);
				projectFields.put("productRZ", "$_id");
				projectFields.put("itemreviewcount", "$itemreviewcount");
				DBObject project = new BasicDBObject("$project", projectFields);

				DBObject sort = new BasicDBObject();
				sort.put("itemreviewcount",-1);
				DBObject orderby=new BasicDBObject("$sort",sort);

				DBObject limit=new BasicDBObject("$limit",5);

				AggregationOutput aggregate = userReviews.aggregate(group,project,orderby,limit);

				LinkedHashMap<String, String> mostProductsSoldZipcodeMap = new LinkedHashMap<String, String>();
				for (DBObject queryResult : aggregate.results()) {
					BasicDBObject obj = (BasicDBObject) queryResult;
					mostProductsSoldZipcodeMap.put(obj.getString("productRZ"),obj.getString("itemreviewcount"));
				}
				return mostProductsSoldZipcodeMap;
			}
			else{
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public static LinkedHashMap<String, String> getTop5MostSoldProducts(){
		try{
			if(getConnection()== 1){
				DBObject groupFields = groupFields = new BasicDBObject("_id", 0);
				groupFields.put("_id", "$productId");
				groupFields.put("itemcount", new BasicDBObject("$sum", 1));
				groupFields.put("productModelName", new BasicDBObject("$push", "$productMN"));
				DBObject group = new BasicDBObject("$group", groupFields);

				DBObject projectFields = new BasicDBObject("_id", 0);
				projectFields.put("productId", "$_id");
				projectFields.put("itemcount", "$itemcount");
				projectFields.put("productModelName", "$productModelName");
				DBObject project = new BasicDBObject("$project", projectFields);

				DBObject sort = new BasicDBObject();
				sort.put("itemcount",-1);
				DBObject orderby=new BasicDBObject("$sort",sort);

				DBObject limit=new BasicDBObject("$limit",5);

				AggregationOutput aggregate = userReviews.aggregate(group,project,orderby,limit);

				LinkedHashMap<String, String> mostSoldProductMap = new LinkedHashMap<String, String>();

				for (DBObject queryResult : aggregate.results()) {
					BasicDBObject obj = (BasicDBObject) queryResult;
					BasicDBList productModelName = (BasicDBList) obj.get("productModelName");
					mostSoldProductMap.put((String)productModelName.get(0),obj.getString("itemcount"));

				}
				return mostSoldProductMap;
			}
			else{
				return null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
}

