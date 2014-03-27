package market.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@ManyToOne(optional=false)
	User orderedBy;
	Date orderedOn;
	@ManyToOne(optional=false)
	@JoinColumn(name="order_item_id")
	List<OrderItem> items;

	public Order() { }
	
	public Order(int id, User orderedBy, Date orderedOn) {
		this();
		this.id = id;
		this.orderedBy = orderedBy;
		this.orderedOn = orderedOn;
	}
	public User getOrderedBy() {
		return orderedBy;
	}
	public void setOrderedBy(User orderedBy) {
		this.orderedBy = orderedBy;
	}
	public Date getOrderedOn() {
		return orderedOn;
	}
	public void setOrderedOn(Date orderedOn) {
		this.orderedOn = orderedOn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
}
