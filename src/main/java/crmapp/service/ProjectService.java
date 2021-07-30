package crmapp.service;

import java.sql.SQLException;
import java.util.List;

import crmapp.dao.ProjectDao;
import crmapp.dto.ProjectCreateDto;
import crmapp.dto.ProjectStaffDto;
import crmapp.model.Project;
import crmapp.model.ProjectStaff;

public class ProjectService {
	private ProjectDao dao;
	
	public ProjectService() {
		dao = new ProjectDao();
	}
	
	public List<ProjectStaff> findAllProject() {
		List<ProjectStaff> projectStaffs = null;
		try {
			projectStaffs = dao.findAllProjectStaff();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectStaffs;
	}
	public List<Project> findAll() {
		List<Project> projects = null;
		try {
			projects = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}
	
	public void deleteProjectById(int id) {
		try {
			dao.deleteProjectById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addProject(ProjectCreateDto dto) {
		try {
			dao.addProject(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Project findProjectById(int id) {
		return dao.findProjectById(id);
	}
	
	public void update(ProjectCreateDto projectUpdate, int id) {
		try {
			dao.update(projectUpdate, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addProjectStaff(ProjectStaffDto dto) {
		try {
			dao.addProjectStaff(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeStaff(int id) {
		try {
			dao.removeStaff(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
