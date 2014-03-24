package market.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	Integer sku;
	@ManyToOne(optional=false)
	User user;
	String name;
	String description;
	double price;
	int quantity;
	
	public Product() { }
	
	public Product (Integer sku, String name, String description, double price, User user, Integer qty) {
		this();
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
