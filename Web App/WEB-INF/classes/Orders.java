import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Orders {

	private static List<Order> orders;
	private int numItems;

	public Orders() {
		orders = new ArrayList<>();
		setNumItems(1);
	}

	public ArrayList getAllOrders() {
		return (ArrayList) orders;
	}

	public void addToOrders( Order order) {
		orders.add(order);
	}


	public static ArrayList<Order> getOrders() {
		return (ArrayList<Order>) orders;
	}

	public static void setOrders(ArrayList<Order> orders) {
		Orders.orders = orders;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public void incrementNumItems() {
	    setNumItems(getNumItems() + 1);
	  }

	 public void cancelOrder() {
		    setNumItems(0);
		  }

}
