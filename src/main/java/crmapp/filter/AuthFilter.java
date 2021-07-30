package crmapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmapp.utils.UrlConst;

@WebFilter(urlPatterns = UrlConst.ROOT)
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String servletPath = req.getServletPath();
		
		if(servletPath.startsWith(UrlConst.ASSETS) || servletPath.startsWith(UrlConst.AUTH_LOGIN))
			chain.doFilter(request, response);
		else {
			String status = String.valueOf(req.getSession().getAttribute("status"));
			System.out.println("STATUS: " + status);
			if(status.equals("null"))
				resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			else
				chain.doFilter(request, response);
		}
		
	}

}
