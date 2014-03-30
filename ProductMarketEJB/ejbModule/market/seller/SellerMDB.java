package market.seller;

import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import market.models.Product;

@MessageDriven(mappedName = "queue/sellers")
public class SellerMDB implements MessageListener {

	@PersistenceUnit(name = "ProductMarketJPA")
	EntityManagerFactory emf;
	
	@Override
	public void onMessage(Message msg) {
		
		TextMessage message = (TextMessage) msg;
		int productSKU;
		try {
			productSKU = message.getIntProperty("productSKU");
		} catch (JMSException e) {
			throw new EJBException("Message: " + msg +": does not have productSKU property",e);
		}
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
