package crmapp.utils;

public class UrlConst {
	// Path
		public static final String ROOT 	= "/*";
		public static final String HOME 	= "/home";
		public static final String ASSETS 	= "/assets";
		
	// Monitor
		
		public static final String HEALTH   = "/health";
		public static final String INVALIDATE 	= "/invalidate-session";
		
	// Auth
		public static final String AUTH_LOGIN 	= "/log-in";
		public static final String AUTH_LOGOUT 	= "/log-out";
		public static final String AUTH_SIGNUP 	= "/sign-up";
		public static final String AUTH_FORGOT_PASSWORD = "/forgot-password";
		
		
	//Project
		
		public static final String PROJECT_DASHBOARD = "/project";
		public static final String PROJECT_MANAGE = "/projects";
		public static final String PROJECT_ADD = "/project/add";
		public static final String PROJECT_UPDATE = "/project/update";
		public static final String PROJECT_DELETE = "/project/delete";
		public static final String PROJECT_STAFF = "/project/staff";
		public static final String PROJECT_STAFF_ADD = "/project/staff/add";
		public static final String PROJECT_STAFF_REMOVE = "/project/staff/remove";
	
		
	//Role
		
		public static final String ROLE_DASHBOARD = "/role";
		public static final String ROLE_ADD = "/role/add";
		public static final String ROLE_UPDATE = "/role/update";
		public static final String ROLE_DELETE= "/role/delete";
	
	//Task
		public static final String TASK_DASHBOARD = "/task";
		public static final String TASK_ADD = "/task/add";
		public static final String TASK_UPDATE = "/task/update";
		public static final String TASK_DELETE = "/task/delete";
		
	// User
		public static final String USER_PROFILE = "/user/profile";
		public static final String USER_DASHBOARD 	= "/user";
		public static final String USER_UPDATE 	= "/user/update";
		public static final String USER_DELETE	= "/user/delete";
		public static final String USER_ADD     = "/user/add";
		public static final String USER_EDIT = "/user/edit";
		
}
