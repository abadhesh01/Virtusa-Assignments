<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample JSP Page</title>
</head>
<body>
	<h1 style="color: violet; text-align: center;">Sample JSP Page</h1>
	<section style="text-align: center;">
		<a href="http://localhost:9090/SampleSpringMVCapp2/index/message">Print
			sample message.</a> <br> <br> <a
			href="http://localhost:9090/SampleSpringMVCapp2/index/contact">Print
			sample contact details.</a> <br> <br> <a
			href="http://localhost:9090/SampleSpringMVCapp2/index/clear">Clear</a>
		<br> <br> <br> <strong
			style="color: #008000; font-size: 20px;">${sessionScope.message}</strong>
		<br> <br> <br> <strong
			style="color: #800000; font-size: 20px;">${contact}</strong>
	</section>
</body>
</html>