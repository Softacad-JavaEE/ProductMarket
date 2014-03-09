<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Product</title>
</head>
<body>
<form method="POST">

	SKU: <br/>
	<input type="text" name="sku"/>
	<br/>
	Name: <br/>
	<input type="text" name="name"/>
	<br/>
	Description: <br/>
	<input type="text" name="desc"/>
	<br/>
	Price: <br/>
	<input type="text" name="price"/>
	<br/>
	Quantity: <br/>
	<input type="text" name="qty"/>
	<br/>
		
	<input type="submit" value="Add"/>
</form>
</body>
</html>