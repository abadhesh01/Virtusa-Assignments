<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
	<section style="text-align: center;">
		<strong style="color:${headingColor}; font-size: 50px;">${heading}</strong>
	</section>
	<br>
	<br>
	<section style="text-align: center;">
		<form:form action="${action}" method="${requestType}"
			modelAttribute="login">
			<table
				style="margin-left: auto; margin-right: auto; border: 5px solid black;">
				<tr>
					<td><strong>Enter username:</strong></td>
					<td><form:input path="username" required="required" /></td>
				</tr>
				<tr>
					<td><strong>Enter password:</strong></td>
					<td><form:password path="password" required="required" /></td>
				</tr>
			</table>
			<br>
			<form:button name="Login"
				style="color: Blue; background-color: cyan;">Login</form:button>
			<br>
			<br>
			<br>
			<strong style="color: red;">${message}</strong>
			<br>
			<strong style="color: red;"> <form:errors path="username" />
				<br> <form:errors path="password" />
			</strong>
		</form:form>
	</section>
</body>
</html>