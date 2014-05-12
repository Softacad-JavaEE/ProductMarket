package market.login;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import market.models.Product;
import market.models.User;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Integer sku = Integer.parseInt(request.getParameter("sku"));
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		Double price = Double.parseDouble(request.getParameter("price"));
		User user = (User) session.getAttribute("USER");
		Product newProduct = new Product(sku,
				request.getParameter("name"),
				request.getParameter("desc"),
				price,
				user,
				qty);
		ProductService.getInstance().addProduct(newProduct);
		response.sendRedirect("myproducts.jsp");
	}

}
