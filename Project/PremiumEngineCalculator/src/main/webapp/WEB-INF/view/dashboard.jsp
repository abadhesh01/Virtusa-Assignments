<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userType} Dashboard</title>
</head>
<body style="background-color: #C7CFC9">

	<%
	response.addHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.addHeader("Cache-Control", "pre-check=0, post-check=0");
	response.setDateHeader("Expires", 0);
	%>

	<section id="dashboardHeanding"
		style="background-color: ${headingColor}; color:white; text-align: center; font-size: 50px">
		<div id="heading" style="font-size: 50px; text-align: center">
			<strong>${userType} Dashboard</strong>
		</div>
		<div style="font-size: 20px">
			<table style="border: 1; width: 100%">
				<tr>
					<td align="left"><strong>${userName}</strong></td>
					<td align="right"><form:form action="${onLogout}"
							method="post" modelAttribute="login">
							<form:button>
								<strong style="color: ${headingColor}">Logout</strong>
							</form:button>
						</form:form></td>
				</tr>
			</table>
		</div>
	</section>


</body>
</html>