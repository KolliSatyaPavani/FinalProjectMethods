<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>

	<form:form action="showMenu" method="post" modelAttribute="customer">
	Mobile Number:
	<form:input path="mobileNo" size="30"/><br>
	<input type="submit" value="login">
	</form:form>
</body>
</html>