<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	width: 300px;
}

h2 {
	text-align: center;
}

form {
	display: flex;
	flex-direction: column;
}

label, input, button {
	margin-bottom: 10px;
}

input, button {
	padding: 10px;
	font-size: 16px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>User Login</h2>
		<form action=Login method="post">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required> <label
				for="password">Password:</label> <input type="password"
				id="password" name="password" required> <label for="roleId">Choose
				an option:</label> <select name="roleId" id="roleId">
				<option value="1">Associate</option>
				<option value="2">Admin</option>
			</select>
			<button type="submit">Login</button>
		</form>


		<%String message = request.getParameter("message");
        if(message!=null && !message.isEmpty()){%>
		<%= message%>
		<%} %>
	</div>
</body>
</html>