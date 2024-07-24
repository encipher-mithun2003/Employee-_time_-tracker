<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.model.User"%>
<%@ page import="com.model.Task"%>
<%@ page import="java.util.*"%>
<%@ page import="com.DAO.TaskDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% List<Task> tasks = new ArrayList<>(); %>
	<% User user = (User) session.getAttribute("user"); %>
	<% String username = user.getName(); %>
	<% TaskDAO taskDAO = new TaskDAO(); %>
	<% tasks = 	taskDAO.getTaskbyEmployeeName(username); %>

	<table border=1>

		<thead>
			<tr>
				<th>Project</th>
				<th>Date</th>
				<th>Start Time</th>
				<th>End Time</th>
				<th>Task Category</th>
				<th>Description</th>
			</tr>
		</thead>

		<tbody>
			<% for(Task task:tasks){%>
			<tr>
				<td><%= task.getProject() %></td>
				<td><%= task.getDate() %></td>
				<td><%= task.getStartTime() %></td>
				<td><%= task.getEndTime() %></td>
				<td><%= task.getTaskCategory() %></td>
				<td><%= task.getDescription() %></td>
				<td>
					<!-- <form action="editTask.jsp"> --> <%-- <input type="hidden" name="time" value="<%=String.valueOf() %>" --%>
					<!-- </form> -->
					<button
						onclick="window.location.href='editTask.jsp?id=<%= task.getId() %>'">Edit</button>
				</td>
				<td>
					<form action=deleteTaskServlet method="post">
						<input type="hidden" name="id" value=<%=task.getId() %>>
						<% HttpSession se = request.getSession();  %>
						<% se.setAttribute("task",task); %>
						<button type=submit>Delete</button>
					</form>
				</td>
			</tr>

			<%} %>
		</tbody>


	</table>

</body>
</html>