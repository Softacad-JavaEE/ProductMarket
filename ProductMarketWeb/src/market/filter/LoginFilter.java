package market.filter;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import market.login.UserService;
import market.models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	HttpSession session;
	String userName;
	String password;
	User user = null;

	@PersistenceUnit(unitName="ProductMarketWeb")
	EntityManagerFactory emf;
	
	EntityManager em;
	Query userQuery;
	
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		//Create HttpServlets and pass them the objects
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		session = httpServletRequest.getSession();
		
		userName = request.getParameter("username");
		password = request.getParameter("password");
		
		//First, check whether the Session has object USER
		boolean isUserLogged = checkLogged(httpServletRequest,httpServletResponse);
		
		//If the user is not logged in, check if he has passed loggin information
		if(!isUserLogged){
			checkLoggingCredentials(httpServletRequest,httpServletResponse);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private boolean checkLoggingCredentials(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		
		boolean loggedin = false;
		
		if(userName == null){
			session.setAttribute("loginFailed", false);
			return false;
		}
		
		List<User> allUsers = UserService.getInstance().getUsers();
		for (int i=0; i< allUsers.size(); i++) {
			user = allUsers.get(i);
			String username = user.getUsername();			
			if (username.equals(userName)) {
				String pass = user.getPassword();
				if (password.equals(pass)) {
					loggedin = true;
					break;
				}
			}
		}		
		
		if(loggedin){
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			userQuery.setParameter("username", userName);
			User userObj = em.find(User.class, userQuery);
			tx.commit();
			
			session.setAttribute("USER", userObj);
			session.setAttribute("SELLER", user.isSeller());
			session.setAttribute("loginFailed", false);
			return true;
		} else {
			session.setAttribute("loginFailed", true);
			return false;
		}
		
	}

	private boolean checkLogged(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		
		
			if(session.getAttribute("USER") != null){
				return true;
			}
		
		return false;
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		em = emf.createEntityManager();
		userQuery = em.createNamedQuery("Select username from User where username = :username");
	}

}
