<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Empolyee Task Manger</title>
<style>
body {
	font-family: Arial, sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	background-color: #f0f0f0;
}

.container {
	text-align: center;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

button {
	padding: 10px 20px;
	margin: 10px;
	font-size: 16px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.admin-login-button {
	background-color: #007bff;
	color: white;
}

.user-login-button {
	background-color: #28a745;
	color: white;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Welcome to Task Manager</h1>
		<p>View task login to continue:</p>
		<button class="user-login-button"
			onclick="window.location.href='UserLogin.jsp'">User Login</button>

	</div>

</body>
</html>