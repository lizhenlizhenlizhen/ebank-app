package entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 账户类（PoJo，基于JavaBean规范的实体类）
 * @author ZhenLi
 *
 */
@Component("account")
public class Account extends Object implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//属性
	private int id;
	private String actNo;
	private String password;
	private boolean enable;
	private Date createDate;
	private double balance;
	
	//--构造器
	public Account(){
		
	}

	public Account(String actNo, String password, boolean enable, Date createDate, double balance) {
		super();
		this.actNo = actNo;
		this.password = password;
		this.enable = enable;
		this.createDate = createDate;
		this.balance = balance;
	}


	//--get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActNo() {
		return actNo;
	}

	public void setActNo(String actNo) {
		this.actNo = actNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//重写toSting方法
	@Override
	public String toString() {
		return "Account [id=" + id + ", actNo=" + actNo + ", password=" + password + ", enable=" + enable
				+ ", createDate=" + createDate + ", balance=" + balance + "]";
	}
	
	//重写equals()和hashcode()方法
	//--equals方法默认比较的是俩个对象的地址，但是有时候我们需要比较俩个对象的内容
	
	//和集合框架中的Set有关，所以才重写 hashCode()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actNo == null) ? 0 : actNo.hashCode());
		/*long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());*/
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//--要写出equals 方法的几个特性
		if (this == obj)  //--自反性
			return true;
		if (obj == null)  //--非空
			return false;
		if (getClass() != obj.getClass()) //类型是否一致obj instanceof Account
			return false;
		//真正比较的内容
		Account other = (Account) obj;
		if (actNo == null) {
			if (other.actNo != null)
				return false;
		} else if (!actNo.equals(other.actNo))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (enable != other.enable)
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
	
}
