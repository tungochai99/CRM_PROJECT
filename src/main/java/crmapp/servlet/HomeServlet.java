package crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crmapp.utils.PathConst;
import crmapp.utils.ServletConst;
import crmapp.utils.UrlConst;

@WebServlet(name = ServletConst.HOME, urlPatterns = {
		UrlConst.HOME
})
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(PathConst.HOME)
			.forward(req, resp);
	}
}
