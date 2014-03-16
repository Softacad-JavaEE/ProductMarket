package market.login;

import java.util.ArrayList;
import java.util.List;

import market.models.Product;

public class ProductService {
	private List<Product> products = new ArrayList<Product>();
	private static ProductService instance = null;
	private ProductService () {
//		this.products.add(new Product(121, "Bag","Small light one-hand bag", 5.99));
//		this.products.add(new Product(111, "Hat","Red velvet barette", 15.34, "john", 10));
//		this.products.add(new Product(112, "Shirt","Non-ironing shirt with bottons", 74.89, "john", 50));
//		this.products.add(new Product(113, "Shorts","Swimming shorts", 10.19, "john", 40));
//		this.products.add(new Product(114, "Umbrella","All colors umbrella", 4.44 ));		

	}
	public static ProductService getInstance () {
		if(instance==null) {
			instance = new ProductService();
		}
		return instance;
	}
	public void addProduct(Product product){
		products.add(product);
	}
	public List<Product> getProducts(){
		return products;
	}
	
	public Product getProduct(int SKU){
		
		Product returnProduct = null;
		
		for(int i=0; i<products.size();i++){
			
			if(products.get(i).getSku() == SKU){
				returnProduct = products.get(i);
				break;
			}
			
		}
		
		return returnProduct;
	}
}
