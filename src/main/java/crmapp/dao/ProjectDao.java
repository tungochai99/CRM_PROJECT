package crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import crmapp.dbconnection.MySqlConnection;
import crmapp.dto.ProjectCreateDto;
import crmapp.dto.ProjectStaffDto;
import crmapp.model.Project;
import crmapp.model.ProjectStaff;
import crmapp.model.User;

public class ProjectDao {
	
	public List<ProjectStaff> findAllProjectStaff() throws SQLException {
		List<ProjectStaff> projectStaffs = new LinkedList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT *"
				+ "FROM project_user";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				ProjectStaff projectStaff = new ProjectStaff();
				
				projectStaff.setProjectId(result.getInt("project_id"));
				projectStaff.setUserId(result.getInt("user_id"));
				projectStaff.setJoinDate(result.getDate("join_date"));
				projectStaff.setRoleDescription(result.getString("role_description"));
				
				projectStaffs.add(projectStaff);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return projectStaffs;
	}
	public List<Project> findAll() throws SQLException {
		List<Project> projects = new LinkedList<>();
		List<User> users = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT p.id as id, p.name as name, p.description as description, p.start_date as start_date, p.end_date as end_date,u.id as user_id,u.name as user_name, u.email as user_email, u.address as user_address, u.phone as user_phone"
				+ " FROM project p, user u, project_user pu"
				+ " WHERE p.id = pu.project_id and u.id=pu.user_id ";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Project project = new Project();
				
				project.setId(result.getInt("id"));
				project.setName(result.getString("name"));
				project.setDescription(result.getString("description"));
				project.setStartDate(result.getDate("start_date"));
				project.setEndDate(result.getDate("end_date"));
				
				int userId = result.getInt("user_id");
				User user = getUserFromList(users, userId);
				
				if(user == null) {
					user = new User();
					user.setId(result.getInt("user_id"));
					user.setEmail(result.getString("user_email"));
					user.setName(result.getString("user_name"));
					user.setAddress(result.getString("user_address"));
					user.setPhone(result.getString("user_phone"));
					
					users.add(user);
					
				}
				project.setUser(user);
				
				projects.add(project);
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return projects;
	}
	
	public void deleteProjectById(int id) throws SQLException {
		String query = "DELETE FROM project WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	private User getUserFromList(List<User> users, int userId) {
		for(User user : users) {
			if(user.getId() == userId)
				return user;
			return null;
		}
		return null;
	}
	
	public void addProject(ProjectCreateDto dto ) throws SQLException {
		String query = "INSERT INTO project(name, description, start_date, end_date, owner)"
				+ "VALUES(?,?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());
			statement.setDate(3, dto.getStartDate());
			statement.setDate(4, dto.getEndDate());
			statement.setInt(5, dto.getOwnerId());
			
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public Project findProjectById(int id) {
		Project project = null;
		
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT id, name, description, start_date, end_date, owner FROM project WHERE id = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				project = new Project();
				project.setId(result.getInt("id"));
				project.setName(result.getString("name"));
				project.setDescription(result.getString("description"));
				project.setStartDate(result.getDate("start_date"));
				project.setEndDate(result.getDate("end_date"));
				project.setId(result.getInt("owner"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection to database has been disconnected!");
		}
		return project;
	}
	
	public void update(ProjectCreateDto projectUpdate, int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?, owner = ? ";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, projectUpdate.getName());
			statement.setString(2, projectUpdate.getDescription());
			statement.setDate(3, projectUpdate.getStartDate());
			statement.setDate(4, projectUpdate.getEndDate());
			statement.setInt(5, projectUpdate.getOwnerId());
			
			statement.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void addProjectStaff(ProjectStaffDto dto) throws SQLException {
		String query = "INSERT INTO project_user(project_id, user_id, join_date, role_discription)"
				+"VALUES(?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, dto.getProjectId());
			statement.setInt(2, dto.getUserId());
			statement.setDate(3, dto.getJoinDate());
			statement.setString(4, dto.getRole_description());
			
			statement.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public void removeStaff(int id) throws SQLException {
		String query = "DELETE FROM project_user WHERE project_id";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
}
