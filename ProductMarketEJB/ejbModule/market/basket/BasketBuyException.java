package market.basket;

import javax.ejb.EJBException;

public class BasketBuyException extends EJBException {

	private static final long serialVersionUID = -5007141509086483652L;

	public BasketBuyException(String string, Exception e) {
		super(string, e);
	}


}
