package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie Servlet
 * @author ZhenLi
 *
 */
@WebServlet(urlPatterns="/cookie")
public class Cookieservlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctxt = request.getServletContext();
		System.out.println(ctxt);
		
		
		Cookie c=new Cookie("k", "lizhen");
		c.setMaxAge(60*60);
		response.addCookie(c);
	}
}
