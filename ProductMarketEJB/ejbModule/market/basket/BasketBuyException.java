package market.basket;

import javax.ejb.EJBException;
import javax.jms.JMSException;

public class BasketBuyException extends EJBException {

	private static final long serialVersionUID = -5007141509086483652L;

	public BasketBuyException(String string, JMSException e) {
		super(string, e);
	}


}
