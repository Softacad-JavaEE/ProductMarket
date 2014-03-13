<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="java.util.List,market.login.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<% if (session.getAttribute("USER") != null) { %>

Hello, <% out.println(session.getAttribute("USER")); %> 

<%	if (session.getAttribute("SELLER") == "1") { %> (Seller) <a href='Logout'> Log out</a> <br> <a href="myproducts.jsp"> My products</a>

<%
	} else {
			out.println("(User)" + "<a href='Logout'> Log out</a>");
		}
	} else {
%>

<form method="POST">

	UserName: <br /> <input type="text" name="username" /> <br /> Password:
	<br /> <input type="password" name="password" /> <br /> <input
		type="submit" value="Login" /> <a href="register.jsp">Register</a>
</form>

<%
	if (session.getAttribute("loginFailed") == Boolean.TRUE) {
%>
Incorrect user/pass !
<%
	}
	}
%>
