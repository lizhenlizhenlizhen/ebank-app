package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Component;

import dao.prototype.IAccountDao;
import entity.Account;
import util.DateUtil;
import util.JdbcUtil;

/**
 * AccountDao基于JDBC技术的实现类
 * @author ZhenLi
 *
 */
@Component
public class AccountDaoJdbcImpl implements IAccountDao{

	
	@Override
	public void save(Account act) {
	//1.获取连接对象
		Connection conn = JdbcUtil.getConnection();
		//2.保存对象
		Statement stat=null;
		try {
			stat = conn.createStatement();
			String sql="insert into t_account(act_no,password,enable,create_date,balance) values ('"
					+ act.getActNo()+"','"+act.getPassword()+"',"
					+act.isEnable()+",'"
					+DateUtil.formateDate(act.getCreateDate(), "yyyy-MM-dd HH:mm:ss")+"',"
					+act.getBalance()+")";
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stat, conn);
		}
		
	}

	@Override
	public void delete(int id) {
		//1.获取连接对象
				Connection conn = JdbcUtil.getConnection();
				//2.保存对象
				Statement stat=null;
				try {
					stat = conn.createStatement();
					String sql="delete from t_account where id="+id;
					stat.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					JdbcUtil.close(stat, conn);
				}
		
	}

	@Override
	public void update(Account act) {
		//1.先查看有没有
		Account oldAct=findById(act.getId());
		//2.更新账号
		if(oldAct!=null){
			//1.获取连接对象
			Connection conn = JdbcUtil.getConnection();
			
			//2.保存对象
			Statement stat=null;
			try {
				/*conn.setAutoCommit(false);*/
				stat = conn.createStatement();
				String  sql="update t_account set"
						+" password='"+act.getPassword()
						+"',enable="+act.isEnable()
						+",balance="+act.getBalance()
						+" where id="+act.getId();
				stat.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stat, conn);
			}
		}
	}
	@Override
	public void update(Connection conn, Account act) {
		
		//1.先查看有没有
				
				//2.更新账号
				
					//1.获取连接对象
					//2.保存对象
					Statement stat=null;
					try {
						
						stat = conn.createStatement();
						String sql="update t_account set"
						+" password='"+act.getPassword()
						+"',enable="+act.isEnable()
						+",balance="+act.getBalance()
						+" where id="+act.getId();
						stat.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			
	}

	@Override
	public void saveAccounts(List<Account> acts) {
		// TODO Auto-generated method stub
		
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement ps=null;
		String sql="insert into t_account(act_no,password,enable,create_date,balance) "
				+ "values (?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			int count=0;
			for (Account account : acts) {
				ps.setString(1, account.getActNo());
				ps.setString(2, account.getPassword());
				ps.setBoolean(3, account.isEnable());
				ps.setString(4, DateUtil.formateDate(account.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				ps.setDouble(5, account.getBalance());
				if(count%500==0){
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
				}
				ps.addBatch();
			}
			ps.executeBatch();	
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			JdbcUtil.close(ps,conn);
		}
		
	}
	
	@Override
	public Account findById(int id) {
		// 1.获取连接对象
		Connection conn = JdbcUtil.getConnection();
		// 2.保存对象
		Statement stat = null;
		ResultSet rs = null;
		Account act = null;
		try {
			stat = conn.createStatement();
			
			String sql = "select * from t_account where id=" + id;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				act = new Account();
				act.setId(rs.getInt(1));
				act.setActNo(rs.getString(2));
				act.setPassword(rs.getString(3));
				act.setEnable(rs.getBoolean(4));
				act.setCreateDate(DateUtil.parseDate("yyyy-MM-dd HH:mm:ss", rs.getString(5)));
				act.setBalance(rs.getDouble(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, stat, conn);
		}
		return act;
	}

	@Override
	public Account findByActNo(String actNo) {
		// 1.获取连接对象
				Connection conn = JdbcUtil.getConnection();
				// 2.保存对象
				Statement stat = null;
				ResultSet rs = null;
				Account act = null;
				try {
					stat = conn.createStatement();
					
					String sql = "select * from t_account where act_no='" + actNo+"'";
					rs = stat.executeQuery(sql);
					while (rs.next()) {
						act = new Account();
						act.setId(rs.getInt(1));
						act.setActNo(rs.getString(2));
						act.setPassword(rs.getString(3));
						act.setEnable(rs.getBoolean(4));
						act.setCreateDate(DateUtil.parseDate("yyyy-MM-dd HH:mm:ss", rs.getString(5)));
						act.setBalance(rs.getDouble(6));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JdbcUtil.close(rs, stat, conn);
				}
				return act;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findPaged(int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrupdate(Account act) {
		//-- 1. 先看账户有没有ID 
				int id = act.getId();
				String sql = "";
				if(id==0){
					//-- 保存对象
					sql = 
						"insert into t_account"
						+ "(act_no,password,enable,create_date,balance) values('"
						+ act.getActNo()+"',"
						+ act.getPassword()+","
						+ act.isEnable()+",'"
						+ DateUtil.formateDate(act.getCreateDate(), "yyyy-MM-dd HH:mm:ss")+"',"
						+ act.getBalance() +")";
				}else{
					//-- update对象
					sql = "update t_account set "
						  + "password="+act.getPassword()+","
						  + "enable="+act.isEnable()+","
						  + "balance="+act.getBalance()+" "
						  + "where id="+act.getId();
				}
				//-- 1. 获取连接对象
				Connection con = JdbcUtil.getConnection();
				//-- 2. 保存对象
				Statement stmt = null;
				try {
					stmt = con.createStatement();
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					JdbcUtil.close(null,con);
				}
		
	}

	



	

	
}
