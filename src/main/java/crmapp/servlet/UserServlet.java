package crmapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmapp.dto.UserCreateDto;
import crmapp.model.User;
import crmapp.service.ProjectService;
import crmapp.service.UserService;
import crmapp.utils.PathConst;
import crmapp.utils.ServletConst;
import crmapp.utils.UrlConst;

@WebServlet(name = ServletConst.USER, urlPatterns = {
		UrlConst.USER_DASHBOARD,
		UrlConst.USER_PROFILE,
		UrlConst.USER_ADD,
		UrlConst.USER_UPDATE,
		UrlConst.USER_DELETE
})
public class UserServlet extends HttpServlet {
	private UserService service;
	 
	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		switch(req.getServletPath()) {
		case UrlConst.USER_DASHBOARD :
			getUserDashboard(req, resp);
			break;
		case UrlConst.USER_PROFILE:
			getUserProfile(req, resp);
			break;
		case UrlConst.USER_ADD :
			getUserAdd(req, resp);
			break;
		case UrlConst.USER_DELETE :
			getUserDelete(req, resp);
			break;
		case UrlConst.USER_UPDATE :
			getUserUpdate(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlConst.USER_DASHBOARD :
			getUserDashboard(req, resp);
			break;
		case UrlConst.USER_PROFILE:
			
			break;
		case UrlConst.USER_ADD :
			postUserAdd(req, resp);
			break;
		case UrlConst.USER_DELETE :
	
			break;
	
		case UrlConst.USER_UPDATE :
			postUserUpdate(req, resp);
			break;
		default:
			break;
		}
	}

	private void postUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int idUpdate = Integer.parseInt(req.getParameter("id"));
		UserCreateDto userUpdate = extractDtoFromReq(req);
		
		service.update(userUpdate, idUpdate);
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		UserCreateDto dto = extractDtoFromReq(req);
		
		service.add(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}
	
	private UserCreateDto extractDtoFromReq(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		return new UserCreateDto(id, email, password, name, address, phone, roleId);
	}

	private void getUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = service.findUserById(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher(PathConst.USER_UPDATE).forward(req, resp);
		
	}


	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.deleteUserById(id);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}


	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(PathConst.USER_ADD).forward(req, resp);
		
	}


	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private void getUserDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<User> users = service.findAll();
		
		if(users != null  && !users.isEmpty())
			req.setAttribute("users", users);
		
		req.getRequestDispatcher(PathConst.USER_DASHBOARD).forward(req, resp);
		
	}

	
}
