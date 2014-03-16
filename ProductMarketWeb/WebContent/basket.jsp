<%@ page import="java.util.List, market.login.*, market.basket.*" %>

<% Basket b = (Basket) session.getAttribute("Basket"); %>

<table border="1">
	<tr>
		<td colspan="2">Basket</td>
	</tr>
	
	<% for (int i=0; i < b.getNumOfProducts(); i++) {%>
	<tr>
		<td colspan="2"><% out.println(b.getProduct(i).getName() + " <a href=AddRemoveProducts?add=false&productNo=" + i + "> Remove</a>"); %></td>
	</tr>
	<% } %>
	<tr>
		<td>Items: 
		<%
		
		out.println(b.getNumOfProducts());
				
		%></td>
		<td>Total:
		<%
		
		out.println(b.getTotalPrice());
				
		%></td>
	</tr>
</table>