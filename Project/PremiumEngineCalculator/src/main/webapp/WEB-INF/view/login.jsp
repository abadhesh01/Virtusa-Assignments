<%@page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userType} Login</title>
</head>
<body style="background-color: #C7CFC9">

	<section id="login">
		<div id="loginHeading" style="text-align: center; font-size: 50px">
			<br> <strong style="color:${headingColor}">${userType}
				Login</strong> <br> <br>
		</div>
		<div id="loginForm" style="text-align: center">
			<form:form action="${onLogin}" method="${requestType}"
				modelAttribute="login">
				<table
					style="margin-left: auto; margin-right: auto; border: 5px solid black">
					<tr>
						<td align="left"><label id="username"><strong
								style="font-size: 30px">Enter username:</strong></label></td>
						<td align="right"><form:input path="username"
								required="required" placeholder="Enter username here"
								size="30px" /></td>
					</tr>
					<tr>
						<td align="left"><label id="password"><strong
								style="font-size: 30px">Enter password:</strong></label></td>
						<td align="right"><form:password path="password"
								required="required" placeholder="Enter password here"
								size="30px" /></td>
					</tr>
					<tr>
						<td align="left"><form:button id="loginUser"
								style="color: #06F7E9; background-color: black; height: 30px; width:230px">LogIn</form:button></td>
						<td align="right"><a href="${onSignup}"><input
								type="button" value="SignUp" id="signUpUser"
								style="color: #06F7E9; background-color: black; height: 30px; width: 230px" /></a></td>
					</tr>
				</table>
				<br>
				<br>
				<strong style="color: #0B8DDD; font-size: 30px">${message}</strong>
				<br>
				<strong style="color: red; font-size: 30px">${errorMessage}</strong>
				<br>
				<strong style="color: red; font-size: 30px"><form:errors
						path="username" /></strong>
				<br>
				<strong style="color: red; font-size: 30px"><form:errors
						path="password" /></strong>
			</form:form>
		</div>
	</section>

</body>
</html>