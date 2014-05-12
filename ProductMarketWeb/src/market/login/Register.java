package market.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import market.models.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User newUser = new User(request.getParameter("name"),
				request.getParameter("username"),
				request.getParameter("password"),
				request.getParameter("seller"));
		UserService.getInstance().addUser(newUser);
		HttpSession session = request.getSession();
		session.setAttribute("USER", newUser.getUsername());
		response.sendRedirect("homePage.jsp");
	}

}
