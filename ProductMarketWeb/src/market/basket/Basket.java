package market.basket;

import java.util.ArrayList;
import java.util.List;

import market.login.Product;


public class Basket {

	private List<Product> products = new ArrayList<Product>();
	
	public void addProduct(Product product){
		products.add(product);
	}
	
	public List<Product> listProducts(){
		
		return products;
		
	}
	
}
