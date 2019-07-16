package ebank;

import java.util.Date;

import org.junit.Test;

import dao.impl.AccountDaoC3p0Impl;
import dao.impl.AccountDaoJdbcImpl;
import dao.prototype.IAccountDao;
import entity.Account;

public class AccountDaoC3p0Test1 {

	//对比jdbc和c3p0连接池实现
	IAccountDao jdbcDao=new AccountDaoJdbcImpl();
	IAccountDao c3p0Dao=new AccountDaoC3p0Impl();
	/*@Test
	public void testJdbcDao(){
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			jdbcDao.save(new Account("act"+i,"123456",true,new Date(),2000));
		
		}
		long end = System.currentTimeMillis();
		System.out.println("jdbc impl time"+(end-start));
	}*/
	@Test
	public void testC3p0Dao(){
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			c3p0Dao.save(new Account(("actt"+i),"123456",true,new Date(),2000));
		
		}
		long end = System.currentTimeMillis();
		System.out.println("c3p0 impl time"+(end-start));
	}
}
