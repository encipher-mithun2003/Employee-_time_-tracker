package com.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbconnection.DBconnection;
import com.model.User;

public class UserDAO {
	
	Connection con = DBconnection.getConnection();
	
	public boolean validateUser(String username, String password, String roleId) {
		
		boolean validate = false;
		
		try {
			
		PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE name=? AND password=? AND roleId=?");
		ps.setString(1,username);
		ps.setString(2, password);
		ps.setString(3, roleId);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			validate = true;
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return validate;
	}
	
	public boolean validateAdmin(String username, String password, String roleId) {
				
		boolean validate = false;
				
		try {
					
			PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE name=? AND password=? AND roleId=?");
			ps.setString(1,username);
			ps.setString(2, password);
			ps.setString(3, roleId);
			ResultSet rs = ps.executeQuery();
				
			if(rs.next()) {
				validate = true;
			}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
				
			return validate;
	}
	
	public void addUser(User user) throws SQLException {
		
			PreparedStatement ps = con.prepareStatement("INSERT INTO user (name,role,email,password,roleId) VALUES (?,?,?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getRole());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRoleId());
			ps.executeUpdate();
	}
	
	public List<User> getAllEmployees(){
		
		List<User> employees = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE roleId=1");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				user.setEmail(rs.getString("email"));
				user.setRoleId(rs.getString("roleId"));
				employees.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}

}
