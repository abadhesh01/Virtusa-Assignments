<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userType} Signup</title>
</head>
<body style="background-color: #C7CFC9">

	<section id="signup">
		<div id="signupHeading" style="text-align: center; font-size: 50px">
			<br> <strong style="color:${headingColor}">${userType}
				Signup</strong> <br> <br>
		</div>
		<div id="signupForm" style="text-align: center">
			<form:form action="${onSignup}" method="${requestType}"
				modelAttribute="signup">
				<table
					style="margin-left: auto; margin-right: auto; border: 5px solid black;">
					<tr>
						<td align="left"><label id="username"><strong
								style="font-size: 30px">Enter username:</strong></label></td>
						<td align="right"><form:input path="username"
								required="required" placeholder="Enter username here"
								size="25px" /></td>
					</tr>
					<tr>
						<td align="left"><label id="newPassword"><strong
								style="font-size: 30px">Enter new password:</strong></label></td>
						<td align="right"><form:password path="newPassword"
								required="required" placeholder="Enter password here"
								size="25px" /></td>
					</tr>
					<tr>
						<td align="left"><label id="confirmPassword"><strong
								style="font-size: 30px">Confirm password:</strong></label></td>
						<td align="right"><form:password path="confirmPassword"
								required="required" placeholder="Enter password here"
								size="25px" /></td>
					</tr>
					<tr>
						<td align="left"><form:button id="signUpUser"
								style="color: #06F7E9; background-color: black; height: 30px; width: 200px">SignUp</form:button></td>
						<%
						if (session.getAttribute("disableLogin") != null && session.getAttribute("disableLogin").equals("false")) {
						%>
						<td align="right"><a href="${onLogin}"><input
								type="button" value="LogIn" id="loginUser"
								style="color: #06F7E9; background-color: black; height: 30px; width: 200px" /></a></td>
						<%
						} else {
						%>
						<td align="right"><a
							href="/PremiumEngineCalculator/home/admin/showAllPolicies"><input
								type="button" value="Show all policies"
								style="color: #06F7E9; background-color: black; height: 30px; width: 200px" /></a></td>
						<%
						}
						%>
					</tr>
				</table>
				<br>
				<br>
				<strong style="color: red; font-size: 30px">${errorMessage}</strong>
				<br>
				<strong style="color: red; font-size: 30px"><form:errors
						path="username" /></strong>
				<br>
				<strong style="color: red; font-size: 30px"><form:errors
						path="newPassword" /></strong>
				<br>
				<strong style="color: red; font-size: 30px"><form:errors
						path="confirmPassword" /></strong>
			</form:form>
		</div>
	</section>

</body>
</html>