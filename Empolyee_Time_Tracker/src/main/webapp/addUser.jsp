<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Select Option Example</title>
</head>
<body>
	<form action="AddUserServlet" method="post">
		<label for="username">Employee Name:</label> <input name="username"
			id="username" type="text" required> <label for="role">Role:</label>
		<input name="role" id="role" type="text" required> <label
			for="mail">Mail Id:</label> <input name="mail" id="mail" type="text"
			required> <label for="password">Password:</label> <input
			name="password" id="password" type="password" required> <input
			type="submit" value="Submit">

		<button onclick="window.location.href='AdminHome.jsp'">Go
			Back</button>
	</form>


	<%String message = request.getParameter("message");

if(message!=null && !message.isEmpty()){%>
	<%=message %>
	<% } %>

</body>
</html>
