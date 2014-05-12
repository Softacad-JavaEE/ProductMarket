<%@page import="javax.ejb.EJB"%>
<%@ page import="javax.naming.*, java.util.List, market.login.*, market.basket.*, market.basket.Basket" %>

<% 

	Basket b = null;
	
	try {
		Context context = new InitialContext();
		// with this lookup string we can get Basket ejb instance so that we can add/remove and buy it
		b = (Basket) context.lookup("java:global/ProductMarket/ProductMarketEJB/Basket");
	}
	catch(Exception e) {
		// exception code here
		out.println(e);
	}
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