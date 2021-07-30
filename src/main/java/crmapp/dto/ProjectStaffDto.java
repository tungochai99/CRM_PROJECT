package crmapp.dto;

import java.sql.Date;

public class ProjectStaffDto {
	private int projectId;
	private int userId;
	private Date joinDate;
	private String role_description;
	
	
	public ProjectStaffDto(int projectId, int userId, Date joinDate, String role_description) {
		this.projectId = projectId;
		this.userId = userId;
		this.joinDate = joinDate;
		this.role_description = role_description;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getRole_description() {
		return role_description;
	}
	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}
	
	
}
