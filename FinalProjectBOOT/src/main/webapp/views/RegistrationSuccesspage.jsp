<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<div align="center">
		<h2>Welcome ${customer.name}</h2>
		<br>
		<h2>Your Registered Customer Mobile Number is:${customer.mobileNo }</h2>
		<h2>Your initial Balance:${customer.wallet.balance}</h2>
	</div>
</body>
</html>