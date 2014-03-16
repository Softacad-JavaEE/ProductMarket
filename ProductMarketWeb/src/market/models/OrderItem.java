package market.models;

import java.io.Serializable;
import javax.persistence.*;
import market.models.Order;
import market.models.Product;

/**
 * Entity implementation class for Entity: OrderItem
 *
 */
@Entity
@Table(name="Order_Item")
public class OrderItem implements Serializable {

	   
	@Id
	private int id;
	@ManyToOne(optional=false)
	private Order order;
	private int quantity;
	@ManyToOne(optional=false)
	private Product product;
	private static final long serialVersionUID = 1L;

	public OrderItem() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}   
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}   
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
   
}
