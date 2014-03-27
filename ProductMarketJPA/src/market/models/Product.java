package market.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.ws.rs.OPTIONS;

import com.sun.istack.internal.NotNull;

@Entity
public class Product {
	
	@Id
	Integer sku;
	@ManyToOne(optional=false)
	User seller;
	String name;
	String description;
	double price;
	int quantity;
	@OneToOne(optional=true)
	OrderItem orderItem;
	
	public Product() { }
	
	public Product (Integer sku, String name, String description, double price, User user, Integer qty) {
		this();
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;		
		this.seller = user;
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
	public User getSeller() {
		return seller;
	}
	public void setSeller(User user) {
		this.seller = user;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
