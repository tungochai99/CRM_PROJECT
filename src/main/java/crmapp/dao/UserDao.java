package crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import crmapp.dbconnection.MySqlConnection;
import crmapp.dto.UserCreateDto;
import crmapp.model.Role;
import crmapp.model.User;

public class UserDao {

	public List<User> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email, "
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				
				int roleId = resultSet.getInt("role_id");
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}
	
	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id = ?";
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

	private Role getRoleFromList(List<Role> roles, int roleId) {
		for(Role role : roles)
			if(role.getId() == roleId)
				return role;
		return null;
	}

	public void add(UserCreateDto dto) throws SQLException {
		String query = "INSERT INTO user(email, password, name, address, phone, role_id)"
				+ "VALUES(?,?,?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getPassword());
			statement.setNString(3, dto.getName());
			statement.setNString(4, dto.getAddress());
			statement.setNString(5, dto.getPhone());
			statement.setInt(6, dto.getRoleId());
			
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public User findUserById(int id) {
		User user = null;
		
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT id, email, password, name, address, phone FROM user WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user = new User();
				user.setId(result.getInt("id"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setName(result.getString("name"));
				user.setPhone(result.getString("phone"));
				user.setAddress(result.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection to database has been disconnected!");
		}
		return user;
		
	}
	public void update(UserCreateDto userUpdate, int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "UPDATE user SET email = ? , password = ?, name = ?, phone = ?, address = ? WHERE id = ?";
		try {
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, userUpdate.getEmail());
			statement.setString(2, userUpdate.getPassword());
			statement.setString(3, userUpdate.getName());
			statement.setString(4, userUpdate.getPhone());
			statement.setString(5, userUpdate.getAddress());
			
			statement.executeUpdate();
		} catch(SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
}
