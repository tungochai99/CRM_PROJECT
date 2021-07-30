package crmapp.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmapp.dto.ProjectCreateDto;
import crmapp.dto.ProjectStaffDto;
import crmapp.dto.UserCreateDto;
import crmapp.model.Project;
import crmapp.model.ProjectStaff;
import crmapp.service.ProjectService;
import crmapp.service.UserService;
import crmapp.utils.PathConst;
import crmapp.utils.ServletConst;
import crmapp.utils.UrlConst;

@WebServlet(name = ServletConst.PROJECT, urlPatterns = {
		UrlConst.PROJECT_DASHBOARD,
		UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_MANAGE,
		UrlConst.PROJECT_UPDATE,
		UrlConst.PROJECT_DELETE, 
		UrlConst.PROJECT_STAFF,
		UrlConst.PROJECT_STAFF_ADD,
		UrlConst.PROJECT_STAFF_REMOVE
})
public class ProjectServlet extends HttpServlet {
	private ProjectService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new ProjectService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		switch(req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD :
			getDashboard(req, resp);
			break;
		case UrlConst.PROJECT_MANAGE :
			
			break;
		case UrlConst.PROJECT_UPDATE :
			getProjectUpdate(req, resp);
			break;
		case UrlConst.PROJECT_DELETE :
			getProjectDelete(req, resp);
			break;
	
		case UrlConst.PROJECT_STAFF :
			getProjectStaffDashboard(req, resp);
			break;
	
		case UrlConst.PROJECT_STAFF_ADD :
			getProjecStafftAdd(req, resp);
			break;
	
		case UrlConst.PROJECT_STAFF_REMOVE :
			getProjectRemove(req, resp);
			break;
	
		case UrlConst.PROJECT_ADD :
			getProjectAdd(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void getProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(PathConst.PROJECT_ADD).forward(req, resp);	
		
	}

	private void getProjectRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		service.removeStaff(id);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF);
	}

	private void getProjecStafftAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(PathConst.PROJECT_STAFF_ADD).forward(req, resp);
		
	}
	
	private void postProjectUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		ProjectCreateDto projectUpdate = extractDtoFromReq(req);
		
		service.update(projectUpdate, id);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}

	private void getProjectUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		Project project = service.findProjectById(id);
		req.setAttribute("project", project);
		
		req.getRequestDispatcher(PathConst.PROJECT_UPDATE).forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD :
			getDashboard(req, resp);
			break;
		case UrlConst.PROJECT_MANAGE :
			
			break;
		case UrlConst.PROJECT_UPDATE :
			postProjectUpdate(req, resp);
			break;
		case UrlConst.PROJECT_DELETE :
	
			break;
	
		case UrlConst.PROJECT_STAFF :
			getProjectStaffDashboard(req, resp);
			break;
	
		case UrlConst.PROJECT_STAFF_ADD :
			postProjectStaffAdd(req, resp);
			break;
	
		case UrlConst.PROJECT_STAFF_REMOVE :
	
			break;
	
		case UrlConst.PROJECT_ADD :
			postProjectAdd(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void postProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjectCreateDto dto = extractDtoFromReq(req);
		
		service.addProject(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
		
	}

	private ProjectCreateDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Date startDate = Date.valueOf(req.getParameter("startDate"));
		Date endDate = Date.valueOf(req.getParameter("endDate"));
		int ownerId = Integer.parseInt(req.getParameter("owner"));
		return new ProjectCreateDto(name, description, startDate, endDate, ownerId);
	}
	
	private void postProjectStaffAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjectStaffDto dto = extractDtoFromReq1(req);
		
		service.addProjectStaff(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF);
	}
	
	private ProjectStaffDto extractDtoFromReq1(HttpServletRequest req) {
		int idProject = Integer.parseInt(req.getParameter("projectId"));
		int idUser = Integer.parseInt(req.getParameter("userId"));
		Date joinDate = Date.valueOf(req.getParameter("joinDate"));
		String roleDescription = req.getParameter("roleDescription");
		return new ProjectStaffDto(idProject, idUser, joinDate, roleDescription);
	}

	

	private void getProjectStaffDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProjectStaff> projectStaffs = service.findAllProject();
		
		if(projectStaffs != null && !projectStaffs.isEmpty())
			req.setAttribute("projectStaffs", projectStaffs);
		
		req.getRequestDispatcher(PathConst.PROJECT_STAFF).forward(req, resp);	
		
	}

	protected void getDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Project> projects = service.findAll();
		
		if(projects != null && !projects.isEmpty())
			req.setAttribute("projects", projects);
		req.getRequestDispatcher(PathConst.PROJECT_DASHBOARD).forward(req, resp);
	}
	
	
	

	private void getProjectDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.deleteProjectById(id);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
		
	}

}
