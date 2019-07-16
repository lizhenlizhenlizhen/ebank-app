package ebank;
/**
 * 账户测试类
 * @author ZhenLi
 *
 */

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controller.BaseConfig;
import service.impl.AccountServiceDaoImpl;
import service.prototype.IAccountService;

public class AccountServiceTest {

	/*private IAccountService actService=new AccountServiceDaoImpl();*/
	ApplicationContext appContext=new AnnotationConfigApplicationContext(BaseConfig.class);
	
	private IAccountService actService=(IAccountService) appContext.getBean("accountService");
	@Test
	public void testTransfer(){
		actService.transfer(2,1,200);
	}
}
