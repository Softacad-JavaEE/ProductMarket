<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%!
	boolean doLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session.getAttribute("USER") != null){
			return true;
		}
		String userName = request.getParameter("username");
		
		if(userName == null){
			session.setAttribute("loginFailed", false);
			return false;
		}
		if(userName.equals("ivan")){
			session.setAttribute("USER", userName);
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
	out.println(session.getAttribute("USER") + "<a href='Logout'>Log out</a>");	
	} else  {
%>
<form method="POST">

	UserName: <br/>
	<input type="text" name="username"/>
	<br/>
	Password: <br/>
	<input type="text" name="password"/>
	<br/>
	<input type="submit" value="Login"/>
	
	<a href="Register">Register</a>
</form>
<%
	if(session.getAttribute("loginFailed") == Boolean.TRUE){
	%>	
		Incorrect user/pass !
	<%
		}
	}
%>
