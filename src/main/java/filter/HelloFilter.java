package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import util.JdbcUtil;

/**
 * 第一个过滤器
 * @author ZhenLi
 *
 */
@WebFilter(urlPatterns="/*")
public class HelloFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1.调用Servlet的service方法前做些事情
		System.out.println("before  do something");
		Connection con = JdbcUtil.getConnection();
		/*	try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//2.调用Servlet的service方法
		chain.doFilter(request, response);
		
		System.out.println("after do something");
		JdbcUtil.close(con);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
