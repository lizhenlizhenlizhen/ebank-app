package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidUtil {

	private static DataSource ds;
	
	static{
		//在POM中配置jar，加载配置文件
		
		try {
			Properties ps=new Properties();
			InputStream in=DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
			ps.load(in);
			//2.获取数据源对象
			ds=DruidDataSourceFactory.createDataSource(ps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		Connection con=null;
		try {
			con=ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void close(ResultSet rs,Statement stmt,Connection con){
		if(rs !=null){
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if(stmt !=null){
			try {
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement stmt,Connection con){
		close(null,stmt,con);
	}
	public static void close(Connection con){
		close(null,null,con);
	}
}
