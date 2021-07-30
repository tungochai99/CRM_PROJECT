package crmapp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import crmapp.dao.UserDao;
import crmapp.dto.UserCreateDto;
import crmapp.model.User;

public class UserService {
	private UserDao dao;
	
	public UserService() {
		dao = new UserDao();
	}

	public List<User> findAll() {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void deleteUserById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void add(UserCreateDto dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);
		try {
			dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(UserCreateDto userUpdate, int id) {
		try {
			dao.update(userUpdate, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User findUserById(int id) {
		return dao.findUserById(id);
	}
}
