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
 * Servlet implementation class editTaskServlet
 */
@WebServlet("/editTaskServlet")
public class editTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String username = request.getParameter("employeeName");
		String role = request.getParameter("role");
		String project = request.getParameter("project");
		String strDate = request.getParameter("date");
		String strStartTime = request.getParameter("StartTime");
		String strEndTime = request.getParameter("EndTime");
		String category = request.getParameter("taskCategory");
		String description = request.getParameter("description");
		int id1=0;
		try {
			id1=Integer.parseInt(id);
		}catch(Exception e){
			
		}
		
		
		Date date = Date.valueOf(strDate);
		Time StartTime = Time.valueOf(strStartTime+":00");
		Time EndTime = Time.valueOf(strEndTime+":00");
		
		boolean isOverlap = false;
		TaskDAO taskDAO = new TaskDAO();
		
		try {
			isOverlap = taskDAO.checkTaskOverlapEdit(id,username, date, StartTime, EndTime);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(isOverlap) {
			response.sendRedirect("editTask.jsp?error=Check the Time Overlap");
			return;
		}
		
		long durationMillies = EndTime.getTime() - StartTime.getTime();
		long durationHours = durationMillies / (1000*60*60);
		
		try {
			double totalHoursToday = taskDAO.getTotalHoursOnDateEdit(id,username, date);
			totalHoursToday = Math.abs(totalHoursToday);
			
			if(durationHours + totalHoursToday > 8) {
			//	request.setAttribute("error","cannot work more than 8 hours");
			//request.getRequestDispatcher("UserHome.jsp").forward(request, response);
				response.sendRedirect("editTask.jsp?error=Employee Cannot Work More than 8 Hours!");
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
		task.setId(id1);
		
	
		
		try {
			System.out.println("HI");
			taskDAO.updateTask(task);
			response.sendRedirect("UserHome.jsp?message=Task Added Successfully!");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
