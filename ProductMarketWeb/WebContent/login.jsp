<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.util.List, market.login.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%!
	boolean doLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null){
			return true;
		}
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
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
			session.setAttribute("USER", userName);
			session.setAttribute("SELLER", user.isSeller());
			session.setAttribute("loginFailed", false);
			return true;
		} else {
			session.setAttribute("loginFailed", true);
			return false;
		}
}
%>
<%
	
	if(doLogin(request, response)){
%>
		Hello,
<%
		out.println(session.getAttribute("USER"));
		if (session.getAttribute("SELLER") == "1") {
%>
			(Seller) <a href='Logout'>Log out</a><br>
<a href="myproducts.jsp"> My products</a>	
<%
		}
		else {
			out.println("(User)"+ "<a href='Logout'>Log out</a>");		
		}
	} else  {
%>
<form method="POST">

	UserName: <br/>
	<input type="text" name="username"/>
	<br/>
	Password: <br/>
	<input type="password" name="password"/>
	<br/>
	<input type="submit" value="Login"/>
	
	<a href="register.jsp">Register</a>
</form>
<%
	if(session.getAttribute("loginFailed") == Boolean.TRUE){
	%>	
		Incorrect user/pass !
	<%
		}
	}
%>
