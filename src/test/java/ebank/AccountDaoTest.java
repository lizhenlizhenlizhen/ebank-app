package ebank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import dao.impl.AccountDaoJdbcImpl;
import dao.prototype.IAccountDao;
import entity.Account;

public class AccountDaoTest {

	IAccountDao actDao=new AccountDaoJdbcImpl();
	/*@Before
	public void init(){
		actDao=new AccountDaoJdbcImpl();
	}*/
/*	@Test
	public void testAdd(){
		
		Account act=new Account("007","123456",false,new Date(),3000);
		actDao.save(act);
	}*/
	
	@Test
	public  void testQueryById(){
		
		Account account = actDao.findById(2);
		System.out.println(account);
	}
	
	/*@Test
	public void testUpdate(){
		Account act=new Account("007","123456",false,new Date(),3000);
		act.setId(4);
		actDao.update(act);
		
	}*/
	
	/*@Test
	public void testDelete(){
		actDao.delete(4);
	}*/
	/*@Test
	public void testBatchAdd(){
		List<Account> acts=new ArrayList<>();
		acts.add(new Account("act01","123456",false,new Date(),2000));
		acts.add(new Account("act02","123456",false,new Date(),2000));
		acts.add(new Account("act03","123456",false,new Date(),2000));
		acts.add(new Account("act04","123456",false,new Date(),2000));
		actDao.saveAccounts(acts);
	}*/
}
