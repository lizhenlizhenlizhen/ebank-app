package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC访问工具类
 * 
 * @author DELL
 *
 */
public class JdbcUtil {
	//属性:1.驱动类2.url 3.username4.password
	private static String driver="";
	private static String url="";
	private static String user="";
	private static String password="";
	
	//--线程池对象(Map<当前线程线程ID，object>)
	private static ThreadLocal<Connection> conPool=new ThreadLocal<Connection>();
	static{
		try {
			//1.用于加载属性文件并加载驱动
			Properties ps=new Properties();
			//2.配置路径		JdbcUtil.class.getClassLoader()找到类加载器
			InputStream in=JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.propertites");
			//3.加载配置文件
			ps.load(in);
			//4.给属性赋值
			driver = ps.getProperty("jdbc.driver");
			url=ps.getProperty("jdbc.url");
			user=ps.getProperty("jdbc.user");
			password=ps.getProperty("jdbc.password");
			//5.加载驱动类
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		
		if(conPool.get()==null){
		Connection conn=null;
			try {
				 conn=DriverManager.getConnection(url,user,password);
				conPool.set(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conPool.get();
	}
	/**
	 * 释放资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement stat,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		conPool.remove();
	}
	public static void close(Statement stat,Connection conn){
		close(null,stat,conn);
	}
	public static void close(Connection conn){
		close(null,null,conn);
	}
}
