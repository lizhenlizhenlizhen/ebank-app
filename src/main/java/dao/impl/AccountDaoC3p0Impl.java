package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.prototype.IAccountDao;
import entity.Account;
import util.C3p0Util;
import util.DateUtil;

/**
 * 基于C3P0连接池的实现
 * @author ZhenLi
 *
 */
public class AccountDaoC3p0Impl implements IAccountDao{
	@Override
	public void saveOrupdate(Account Act) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save(Account act) {
		//1.获取连接对象
				Connection conn = C3p0Util.getConnection();
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
					C3p0Util.close(stat, conn);
				}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Account act) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Connection con, Account act) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAccounts(List<Account> acts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findByActNo(String ActNo) {
		// TODO Auto-generated method stub
		return null;
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

	

	
}
