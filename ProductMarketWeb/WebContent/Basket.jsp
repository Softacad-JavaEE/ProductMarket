<%@ page import="java.util.List, market.login.*, market.basket.*" %>

<table border="1">
	<tr>
		<td colspan="2">Basket</td>
	</tr>
	<tr>
		<td colspan="2">Item</td>
	</tr>
	<tr>
		<td>Items: 
		<%
		
		Basket b = (Basket) session.getAttribute("Basket");
		out.println(b.getNumOfProducts());
				
		%></td>
		<td>Total:</td>
	</tr>
</table>