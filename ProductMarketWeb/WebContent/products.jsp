<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.util.List, market.login.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
ProductService ps = ProductService.getInstance();
List<Product> prods = ps.getProducts();
for (int i=0; i< prods.size(); i++) {
	Product product = prods.get(i);
	String name = product.getName();
	double price = product.getPrice();
	out.println(name);
	out.println(price);
	out.println("<br>");
}

%>
</html>