package market.basket;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import market.models.Order;
import market.models.OrderItem;
import market.models.Product;
import market.models.User;
import market.userQueries.UserProfile;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

@Stateful
public class Basket {

	private Logger logger = Logger.getLogger(Basket.class);

	@Resource(lookup="java:queue/sellers")
	private Queue sellersQueue;

//	@Inject
//	JMSContext ctx;

	@JMSConnectionFactory("queue/sellers")
	private QueueConnectionFactory connFactory;

	@PersistenceUnit(name = "ProductMarketJPA")
	EntityManagerFactory emf;
	
	private List<Product> products = new ArrayList<Product>();
	private List<OrderItem> orderItems = new ArrayList<>();
	private int numOfProducts;
	private double totalPrice;
	
	private User buyer;
	
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
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
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void addProduct(Product product){
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setQuantity(1);
		orderItems.add(orderItem);
//		this.products.add(product);
	}
	
	public Product getProduct(int Index){
		return products.get(Index);
	}
	
	public void removeProduct(int sku){
		products.remove(sku);
		for (OrderItem item : orderItems) {
			if(item.getProduct().getSku() == sku){
				orderItems.remove(item);
				return;
			}
		}
	}

	public void buy() throws BasketBuyException {
		// foreach product send message
		Order order = new Order();
		order.setOrderedBy(buyer);
		order.setItems(orderItems);
		for (OrderItem item : orderItems) {
			item.setOrder(order);
			sendMessage(item.getProduct());
		}
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
		//TODO Use better transaction logging
//		ctx.createProducer().send(sellersQueue, map);
	}

	private void sendMessage(Product p) {
		Message m = null;

		QueueConnection conn = createConnection();
		Session session = createSession(conn);
		MessageProducer producer = createProducer(session);

		try {
			m = session.createTextMessage(p.getName());
			m.setIntProperty("productSKU", p.getSku());
			m.setStringProperty("buyer", p.getOrderItem().getOrder().getOrderedBy().getUsername());
			m.setStringProperty("seller", p.getSeller().getUsername());
		} catch (JMSException e) {
			throw new BasketBuyException("Cannot create message with text....", e);
		}

		try {
			producer.send(m);
		} catch (JMSException e) {
			logger.error("Message " + m + " cannot be sent.");
			new BasketBuyException("cannot send msg with id 6", e);
		}
	}
	private MessageProducer createProducer(Session session) throws BasketBuyException{
		MessageProducer producer = null; 
		try {
			producer = session.createProducer(sellersQueue);
		} catch (JMSException e) {
			logger.log(Level.ERROR, "cannot create producer", e);
			throw new BasketBuyException("cannot create producer", e);
		}
		return producer;
	}
	private Session createSession(QueueConnection conn) {
		Session session = null;
		try {
			boolean transacted = false;
			session = conn.createQueueSession(transacted, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			throw new BasketBuyException("cannot create session",e);
		}
		return session;
	}
	private QueueConnection createConnection() throws BasketBuyException {
		QueueConnection conn = null;
		try {
			conn = connFactory.createQueueConnection();
		} catch (JMSException e) {
			throw new BasketBuyException("cannot create conn",e);
		} finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (JMSException e) {
					throw new BasketBuyException("Cannot close conn",e);
				}
			}
		}
		return conn;
	}
}
