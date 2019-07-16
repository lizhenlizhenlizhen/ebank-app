package dao.prototype;

import java.sql.Connection;
import java.util.List;



import entity.Account;

public interface IAccountDao {

	void saveOrupdate(Account Act);
	void save(Account act);
	void delete(int id);
	void update(Account act);
	void update(Connection con,Account act);
	void saveAccounts(List<Account> acts);
	
	Account findById(int id);
	Account findByActNo(String ActNo);
	
	List<Account> findAll();
	List<Account> findPaged(int offset,int pageSize);
	
	
}
