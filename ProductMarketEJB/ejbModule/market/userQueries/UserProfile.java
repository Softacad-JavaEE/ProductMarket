package market.userQueries;

import javax.annotation.Resource;
import javax.ejb.Init;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import market.models.User;

@Stateless
public class UserProfile {

	@PersistenceUnit(unitName="ProductMarketJPA")
	private EntityManagerFactory emf;
	private EntityManager em;
	private Query userQuery;

	@Init
	public void init() {
		EntityManager em = emf.createEntityManager();
		userQuery = em.createNamedQuery("Select username from User where username = :username");
	}

	public User getUser(String username) {
		userQuery.setParameter("username", username);
		return em.find(User.class, userQuery);
	}

}
