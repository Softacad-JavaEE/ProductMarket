package market.basket;

import java.util.ArrayList;
import java.util.List;

import market.login.Product;


public class Basket {

	private List<Product> products = new ArrayList<Product>();
	private int numOfProducts;
	private float totalPrice;
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getNumOfProducts() {
		return numOfProducts;
	}
	public void setNumOfProducts(int numOfProducts) {
		this.numOfProducts = numOfProducts;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void addProduct(Product product){
		this.products.add(product);
	}
	
	
}
