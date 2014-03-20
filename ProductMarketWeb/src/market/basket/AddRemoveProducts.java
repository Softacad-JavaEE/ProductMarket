package market.basket;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import market.login.ProductService;
import market.models.Product;

/**
 * Servlet implementation class AddRemoveProducts
 */
@WebServlet("/AddRemoveProducts")
public class AddRemoveProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB Basket basket;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRemoveProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String add = "true";
		int productSku = 0;
		Product product;
		
		add = request.getParameter("add");
		productSku = Integer.parseInt(request.getParameter("productNo"));
		product = ProductService.getInstance().getProduct(productSku);
		
		if(add.equals("true")){
			basket.addProduct(product);
			basket.setNumOfProducts(basket.getNumOfProducts()+1);
			basket.setTotalPrice(basket.getTotalPrice()+product.getPrice());
		} else{
			basket.setNumOfProducts(basket.getNumOfProducts()-1);
			basket.setTotalPrice(basket.getTotalPrice()-basket.getProduct(productSku).getPrice());
			basket.removeProduct(productSku);
		}
		
		response.sendRedirect("homePage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
