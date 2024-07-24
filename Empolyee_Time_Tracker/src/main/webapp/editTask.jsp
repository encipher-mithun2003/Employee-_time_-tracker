<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.model.Task"%>
<%@ page import="com.DAO.TaskDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% String id = request.getParameter("id"); %>
	<% Task task = new Task(); %>
	<% TaskDAO taskDAO = new TaskDAO(); %>
	<% task = taskDAO.getTaskById(id);  %>

	<form action="editTaskServlet" method="post">
		<input type="hidden" name="id" value="<%=id %>"> <input
			type="hidden" name="employeeName"
			value="<%= task.getEmployeeName() %>"> <input type="hidden"
			name="role" value="<%= task.getRole() %>">
		<p>
			<label>Project:</label> <input type="text" name="project"
				value="<%= task.getProject() %>">
		</p>
		<p>
			<label>Date:</label> <input type="date" name="date"
				value="<%= task.getDate() %>">
		</p>
		<p>
			<label>Start Time:</label> <input type="time" name="StartTime"
				value="<%=String.valueOf(task.getStartTime()).substring(0,5)%>">
		</p>
		<p>
			<label>End Time:</label> <input type="time" name="EndTime"
				value="<%=String.valueOf(task.getEndTime()).substring(0,5)%>">
		</p>
		<p>
			<label>Task Category:</label> <input type="text" name="taskCategory"
				value="<%= task.getTaskCategory() %>">
		</p>
		<p>
			<label>Description:</label><br>
			<textarea name="description"><%= task.getDescription() %></textarea>
		</p>
		<p>
			<button type="submit">Submit</button>
		</p>
	</form>

</body>
</html>