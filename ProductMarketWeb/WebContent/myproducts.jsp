<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, market.login.*, market.models.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage My Products</title>
</head>
<body>
<%
	ProductService ps = ProductService.getInstance();
List<Product> prods = ps.getProducts();
for (int i=0; i< prods.size(); i++) {
	Product product = prods.get(i);
	if (session.getAttribute("USER").equals(product.getSeller())) {
	String name = product.getName();
	double price = product.getPrice();
	out.println(name);
	out.println(price);
	out.println("<br>");
	}
}
%>
<a href="addproduct.jsp">Add new product</a>
</body>
</html>