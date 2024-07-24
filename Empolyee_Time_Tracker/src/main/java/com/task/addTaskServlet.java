package com.task;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.TaskDAO;
import com.model.Task;

/**
 * Servlet implementation class addTaskServlet
 */
@WebServlet("/addTaskServlet")
public class addTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String role = request.getParameter("role");
		String project = request.getParameter("project");
		String strDate = request.getParameter("date");
		String strStartTime = request.getParameter("StartTime");
		String strEndTime = request.getParameter("EndTime");
		String category = request.getParameter("taskCategory");
		String description = request.getParameter("description");
		
		Date date = Date.valueOf(strDate);
		Time StartTime = Time.valueOf(strStartTime+":00");
		Time EndTime = Time.valueOf(strEndTime+":00");
		
		boolean isOverlap = false;
		
		TaskDAO taskDAO = new TaskDAO();
		
		try {
			isOverlap = taskDAO.checkTaskOverlap(username, date, StartTime, EndTime);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(isOverlap) {
			response.sendRedirect("addTask.jsp?error=Check the Time Overlap");
			return;
		}
		
		long durationMillies = EndTime.getTime() - StartTime.getTime();
		long durationHours = durationMillies / (1000*60*60);
		
		try {
			double totalHoursToday = taskDAO.getTotalHoursOnDate(username, date);
			totalHoursToday = Math.abs(totalHoursToday);
			
			if(durationHours + totalHoursToday > 8) {
				
				response.sendRedirect("addTask.jsp?error=Employee Cannot Work More than 8 Hours!");
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Task task = new Task();
		task.setEmployeeName(username);
		task.setRole(role);
		task.setProject(project);
		task.setDate(date);
		task.setStartTime(StartTime);
		task.setEndTime(EndTime);
		task.setTaskCategory(category);
		task.setDescription(description);
		
		try {
			taskDAO.addTask(task);
			response.sendRedirect("addTask.jsp?error=Task Added Successfully!");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
