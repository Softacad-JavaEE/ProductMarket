<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Home</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<jsp:include page="login.jsp"></jsp:include>
			</td>
			<td>
							Basket
			</td>
		</tr>
		<tr>
			<td>
			<jsp:include page="products.jsp"></jsp:include>					
			</td>
		</tr>
	</table>
</body>
</html>