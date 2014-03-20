<%@page import="javax.ejb.EJB"%>
<%@ page import="java.util.List, market.login.*, market.basket.*, market.basket.Basket" %>


<%! @EJB Basket b;
	int numProducts = b.getNumOfProducts();
%>

<table border="1">
	<tr>
		<td colspan="2">Basket</td>
	</tr>
	
	<% for (int i=0; i < numProducts; i++) {%>
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