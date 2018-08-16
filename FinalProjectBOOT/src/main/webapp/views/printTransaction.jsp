<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<h2>Welcome ${customer.name}</h2>
		<br>
		
		<h2>Transactions made:</h2>
		<table border="3">
		<tr>
		<th>Mobile Number</th>
		<th>Transaction Type</th>
		<th>Balance</th>
		<th>Transaction Date</th>
		<th>Transaction Status</th>
		</tr>
		<c:forEach items="${transactions1}" var="transactions">
		<tr>
		<td>${transactions.phoneNumber}</td>
		<td>${transactions.transactionType}</td>
		<td>${transactions.amount}</td>
		<td>${transactions.transactionDate}</td>
		<td>${transactions.transactionStatus}</td>
		</tr>
		</c:forEach>
		</table>
	</div>
	</body>
</html>