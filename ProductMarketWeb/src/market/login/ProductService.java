package market.login;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
	
	private List<Product> products = new ArrayList<Product>();
	private static ProductService instance = null;
	
	private ProductService () {
		this.products.add(new Product(12, "Bag","Small light one-hand bag", 5.99));
		this.products.add(new Product(111, "Hat","Red velvet barette", 15.34));
	}
	
	public static ProductService getInstance () {
		if(instance==null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public List<Product> getProducts(){
		return products;
	}
}
