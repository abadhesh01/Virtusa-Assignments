<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userType}Dashboard</title>
</head>
<body>
	<section style="text-align: center;">
		<strong style="color:${color}; font-size: 50px;">${userType}
			Dashboard</strong> <br> <br> <strong
			style="color:${color}; font-size: 50px;">${userType} login
			successful.</strong> <br> <strong style="color: purplr"> <br>
			<br> <a
			href="http://localhost:9090/SampleSpringLoginApp/app/user/login">User
				Login</a> <br> <br> <a
			href="http://localhost:9090/SampleSpringLoginApp/app/admin/login">Admin
				Login</a>
		</strong>
	</section>
</body>
</html>