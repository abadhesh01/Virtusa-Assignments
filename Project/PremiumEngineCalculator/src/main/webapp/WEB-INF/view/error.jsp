<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<section style="text-align: center; background-color: red;">
		<div>
			<strong style="font-size: 35px; color: white">Oops! :(<br>Something
				Went Wrong!
			</strong>
		</div>
	</section>
	<br>
	<br>
	<section>
		<div style="text-align: center; background-color: red;">
			<strong style="font-size: 20px; color: white">${errorMessage}</strong>
		</div>
	</section>
	<br>
	<br>
	<section>
		<div style="text-align: center;">
			<strong style="font-size: 20px; color: #9E0DCC"> <a
				href="/PremiumEngineCalculator/home/customer/login">Return
					to customer home</a> <br> <br> <a
				href="http://localhost:9090/PremiumEngineCalculator/home/admin/login">Return
					to admin home</a>
			</strong>
		</div>
	</section>
</body>
</html>