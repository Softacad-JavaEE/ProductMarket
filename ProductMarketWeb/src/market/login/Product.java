package market.login;

public class Product {
	public Product (Integer sku, String name, String description, double price, String user, Integer qty) {
		super();
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;		
		this.user = user;
		this.quantity = qty;
	}
	public Product(Integer sku, String name, String description, double price) {
		super();
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	Integer sku;
	String user;
	String name;
	String description;
	double price;
	Integer quantity;
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getSku() {
		return sku;
	}
	public void setSku(Integer sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}
