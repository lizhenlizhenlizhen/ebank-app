package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RespectBinding;

import dao.impl.AccountDaoJdbcImpl;
import dao.prototype.IAccountDao;
import entity.Account;
import service.impl.AccountServiceDaoImpl;
import service.prototype.IAccountService;

/**
 * 用于获取测试客户端通过浏览器发送的参数
 * @author ZhenLi
 *
 */
@WebServlet(urlPatterns="/login")
public class LoginServlet extends HttpServlet{

	private IAccountService actService=new AccountServiceDaoImpl();
	private IAccountDao actDao=new AccountDaoJdbcImpl();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
		//1.获取请求方式（GET POST）
		String method = request.getMethod();
		switch (method) {
		case "GET":
			handleGetRequest(request,reponse);
			break;

		case "POST":
			handlePostRequest(request,reponse);
			break;
		}
	}
	
	private void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ServletContext ctxt = request.getServletContext();
		System.out.println(ctxt);
		//后台获取UserName和password参数值
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(userName+"_------"+password);
		PrintWriter pw = response.getWriter();
		//2.模拟登陆
		Account act = actDao.findByActNo(userName);
		System.out.println(act);
		if(act!=null){
			if(act.getPassword().equals(password)){
				pw.write("success");
			}
		}else{
			pw.write("faluer");
		}
	}
	
	private void handlePostRequest(HttpServletRequest request, HttpServletResponse reponse) {

	}
	
}
