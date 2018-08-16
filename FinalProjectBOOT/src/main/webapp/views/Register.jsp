<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Registration Form</title>
<style>
.error {
	color: red;
	font-weight: bold
}
</style>
</head>
<body>
	<div align="center">
		<form:form action="registerCustomer" method="post" modelAttribute="customer">
			<table>
				<tr>
					<td>Name:</td>
					<td><form:input path="name" size="30" /></td>
					<td><form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Phone Number:</td>
					<td><form:input path="mobileNo" size="30" /></td>
					<td><form:errors path="mobileNo" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Amount:</td>
					<td><form:input path="wallet.balance" size="30" /></td>
					<td><form:errors path="wallet.balance" cssClass="error" /></td>
				</tr>
				<tr>
				<td><input type="submit" value="Submit"/></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>