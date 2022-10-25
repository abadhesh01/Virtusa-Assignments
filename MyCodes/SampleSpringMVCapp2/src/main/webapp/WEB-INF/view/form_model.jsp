<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Form Model</title>
</head>
<body>
	<h1 style="color: Red; text-align: center;">Contact Form Model</h1>
	<section style="color: Blue; text-align: center; font-size: 20px">
		<form action="postcontactmodel" method="post">
			<strong>Enter name:</strong> <input type="text" name="name" /> <br>
			<br> <strong>Enter mobile number:</strong><input type="text"
				name="phone" /> <br> <br> <strong>Enter email
				id:</strong> <input type="text" name="email" /> <br> <br> <input
				type="submit" value="POST" />
		</form>
	</section>
</body>
</html>