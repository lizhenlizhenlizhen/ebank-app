package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3p0连接池工具类
 * @author ZhenLi
 *
 */
public class C3p0Util {

	//通过标识符来创建相应连接池
	private static DataSource ds=new ComboPooledDataSource("mysql");
	
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
