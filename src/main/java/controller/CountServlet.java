package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Session的使用
 * @author ZhenLi
 *
 */
@WebServlet(urlPatterns="/count")
public class CountServlet extends HttpServlet{

	private static int total=0;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charser=utf-8");
		PrintWriter pw = response.getWriter();
		//1.如果有session就直接返回，如果没有就创建一个新的session
		HttpSession session = request.getSession();
		//2.是否新用户
		if(session.getAttribute("current")==null){
			total++;
			session.setAttribute("current", total);
		}
		
		pw.write("<h2 align='center'>Access Website</h2>");
		pw.write("<hr color='red'>");
		pw.write("<p align='center'>you are the "+session.getAttribute("current")+"</P>");
	}
}
