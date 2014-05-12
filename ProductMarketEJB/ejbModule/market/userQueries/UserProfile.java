package market.userQueries;

import javax.ejb.Init;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import market.models.User;

@Stateless
public class UserProfile { // ejb used to retrieve users from DB

	@PersistenceUnit(unitName="ProductMarketJPA")
	private EntityManagerFactory emf;
	
//	@PersistenceContext
//	private EntityManagerFactory emf;
	
	private EntityManager em;
	private Query userQuery;

	@Init
	public void init() {
		em = emf.createEntityManager();
		userQuery = em.createNamedQuery("Select username from User where username = :username");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User getUser(String username) {
		userQuery.setParameter("username", username);
		return em.find(User.class, userQuery);
	}

}
