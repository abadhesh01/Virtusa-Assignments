<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>

	<section style="text-align: center">
		<div>
			<strong style="font-size: 50px; color: ${headingColor};">${heading}</strong>
			<br> <br>
		</div>
		<div>
			<form:form action="${action}" method="post" modelAttribute="login">
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
					<tr>
						<td><strong>Select your mood:</strong></td>
						<td><form:radiobuttons path="mood" items="${moods}" /></td>
					</tr>
				</table>
				<br>
				<br>
				<form:button name="Login"
					style="color: Blue; background-color: cyan;">Login</form:button>
			</form:form>
		</div>
		<br> <br>
		<div></div>
	</section>
</body>
</html>