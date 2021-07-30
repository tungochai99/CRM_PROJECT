package crmapp.service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import crmapp.dao.AuthDao;
import crmapp.dao.UserLoginDto;

public class AuthService {

	private AuthDao dao;
	
	public AuthService() {
		dao = new AuthDao();
	}
	
	public boolean login(String email, String password) {
		UserLoginDto dto = null;
		
		try {
			dto = dao.findUserLogin(email);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if(dto == null)
			return false;
		
		return BCrypt.checkpw(password, dto.getPassword());
	}
	
}
