<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userType} Dashboard</title>
</head>
<body style="background-color: #C7CFC9">

	<section id="dashboardHeanding"
		style="background-color: ${headingColor}; color:white; text-align: center; font-size: 50px">
		<div id="heading" style="font-size: 50px; text-align: center">
			<strong>${userType} Dashboard</strong>
		</div>
		<div style="font-size: 20px">
			<table style="border: 1; width: 100%">
				<tr>
					<td align="left"><strong>${userName}</strong></td>
					<td align="right"><a href="${onLogout}"
						style="text-decoration: none"> <strong style="color: white">Logout</strong>
					</a></td>
				</tr>
			</table>
		</div>
	</section>


</body>
</html>