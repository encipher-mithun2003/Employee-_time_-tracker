package com.DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.dbconnection.DBconnection;
import com.model.Task;

public class TaskDAO {
	
	Connection con = DBconnection.getConnection();
	
	public void addTask(Task task) throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO task (EmployeeName,role,Project,Date,StartTime,EndTime,taskCategory,TaskDescription) VALUES (?,?,?,?,?,?,?,?)");
		ps.setString(1, task.getEmployeeName());
		ps.setString(2, task.getRole());
		ps.setString(3, task.getProject());
		ps.setDate(4, task.getDate());
		ps.setTime(5, task.getStartTime());
		ps.setTime(6, task.getEndTime());
		ps.setString(7, task.getTaskCategory());
		ps.setString(8, task.getDescription());
		ps.executeUpdate();
	}
	
	public boolean checkTaskOverlap(String EmployeeName, Date date, Time StartTime, Time EndTime) throws SQLException {
		
		String query = "SELECT COUNT(*) FROM task WHERE EmployeeName = ? AND Date = ? " +
                "AND ((StartTime < ? AND EndTime > ?) OR " +
                "(StartTime >= ? AND StartTime < ?) OR " +
                "(EndTime > ? AND EndTime <= ?))";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, EmployeeName);
		ps.setDate(2, date);
		ps.setTime(3, StartTime);
		ps.setTime(4, StartTime);
		ps.setTime(5, StartTime);
		ps.setTime(6, EndTime);
		ps.setTime(7, StartTime);
		ps.setTime(8, EndTime);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int count = rs.getInt(1);
			return count>0;
		}
		return false;
	}
	
	public double getTotalHoursOnDate(String EmployeeName, Date date) throws SQLException {
		
		double totalHours = 0.0;
		
		PreparedStatement ps = con.prepareStatement("SELECT SUM(TIME_TO_SEC(TIMEDIFF(EndTime, StartTime))) / 3600 "
													+"AS total_hours FROM task WHERE EmployeeName = ? AND date = ?");
		ps.setString(1, EmployeeName);
		ps.setDate(2, date);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			totalHours = rs.getDouble("total_hours");
		}
		
		return totalHours;
	}
	
	public List<Task> getTaskbyEmployeeName(String EmployeeName) throws SQLException{
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM task WHERE EmployeeName = ?");
		ps.setString(1, EmployeeName);
		ResultSet rs = ps.executeQuery();
		
		List<Task> tasks = new ArrayList<>();
		
		while(rs.next()) {
			
			Task task = new Task();
			task.setId(rs.getInt("id"));
			task.setEmployeeName(rs.getString("EmployeeName"));
			task.setRole(rs.getString("role"));
			task.setProject(rs.getString("Project"));
			task.setDate(rs.getDate("date"));
			task.setStartTime(rs.getTime("StartTime"));
			task.setEndTime(rs.getTime("EndTime"));
			task.setTaskCategory(rs.getString("taskCategory"));
			task.setDescription(rs.getString("TaskDescription"));
			
			tasks.add(task);	
		}
		return tasks;
	}
	
	public void updateTask(Task task) throws SQLException {
        String query = "UPDATE task SET Project = ?, Date = ?, StartTime = ?, EndTime = ?, " +
                       "taskCategory = ?, TaskDescription = ? WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, task.getProject());
            stmt.setDate(2, task.getDate());
            stmt.setTime(3, task.getStartTime());
            stmt.setTime(4, task.getEndTime());
            stmt.setString(5, task.getTaskCategory());
            stmt.setString(6, task.getDescription());
            stmt.setInt(7, task.getId());

            stmt.executeUpdate();
    }
	
	public Task getTaskById(String taskId) throws SQLException {
        Task task = new Task();
        String query = "SELECT * FROM task WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, taskId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setEmployeeName(rs.getString("EmployeeName"));
                    task.setRole(rs.getString("role"));
                    task.setProject(rs.getString("Project"));
                    task.setDate(rs.getDate("Date"));
                    task.setStartTime(rs.getTime("StartTime"));
                    task.setEndTime(rs.getTime("EndTime"));
                    task.setTaskCategory(rs.getString("taskCategory"));
                    task.setDescription(rs.getString("TaskDescription"));
                }
            }
        return task;
    }
	
	public void deleteTask(String taskId) throws SQLException {
        String query = "DELETE FROM task WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, taskId);
            stmt.executeUpdate();
    }
	
	public boolean checkTaskOverlapEdit(String id, String EmployeeName, Date date, Time StartTime, Time EndTime) throws SQLException {
		
		String query = "SELECT COUNT(*) FROM task WHERE   EmployeeName = ? AND Date = ? " +
                "AND ((StartTime < ? AND EndTime > ?) OR " +
                "(StartTime >= ? AND StartTime < ?) OR " +
                "(EndTime > ? AND EndTime <= ?)) and  id != ? ";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(9, id);
		ps.setString(1, EmployeeName);
		ps.setDate(2, date);
		ps.setTime(3, StartTime);
		ps.setTime(4, StartTime);
		ps.setTime(5, StartTime);
		ps.setTime(6, EndTime);
		ps.setTime(7, StartTime);
		ps.setTime(8, EndTime);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int count = rs.getInt(1);
			return count>0;
		}
		return false;
	}
	
	public double getTotalHoursOnDateEdit(String id, String EmployeeName, Date date) throws SQLException {
		
		double totalHours = 0.0;
		
		PreparedStatement ps = con.prepareStatement("SELECT SUM(TIME_TO_SEC(TIMEDIFF(EndTime, StartTime))) / 3600 "
													+"AS total_hours FROM task WHERE EmployeeName = ? AND date = ? AND id!= ?");
		ps.setString(1, EmployeeName);
		ps.setDate(2, date);
		ps.setString(3, id);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			totalHours = rs.getDouble("total_hours");
		}
		
		return totalHours;
	}

}
