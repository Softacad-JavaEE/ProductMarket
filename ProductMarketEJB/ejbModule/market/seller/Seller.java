package market.seller;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import market.models.Product;
import market.userQueries.UserProfile;

@Stateless
public class Seller {
	
	@EJB
	private UserProfile userProfile;
	
	@Resource(lookup="java:queue/sellers")
	private Queue sellersQueue;
	
	@JMSConnectionFactory("queueFactory/sellers")
	private QueueConnectionFactory connFactory;
	
	@PersistenceUnit(name = "ProductMarketJPA")
	EntityManagerFactory emf;
	
	public void sell(int productsCount) throws JMSException {
		
		QueueConnection queueConn = connFactory.createQueueConnection();
		Session session = queueConn.createSession();

		// cycle
		for (int i = 0; i < productsCount; i++) {
			// is this message for me
			readMessage(session);
		}

		// close connection and session
	}

	private void readMessage(Session session) throws JMSException {
		MessageConsumer consumer = 
				session.createConsumer(sellersQueue);
		
		TextMessage message = (TextMessage) consumer.receive();
		
		///sender 
		
		
		int productSKU = message.getIntProperty("productSKU");
//		String buyer = message.getStringProperty("buyer");
//		String seller = message.getStringProperty("seller");
		
		EntityManager entityManager = emf.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Product product = entityManager.find(Product.class, productSKU);
		product.setQuantity(product.getQuantity() - product.getOrderItem().getQuantity());
		
		entityManager.persist(product);
		
		entityManager.getTransaction().commit();
	}

}
