package junit;

public class TestThreadPool {

	static{
		SystemUtil.setValue(6);
	}
	public static void main(String[] args) {
		test01();
		test02();
		new MyThread().start();
	}
	
	public static void test01(){
		System.out.println(SystemUtil.getValue());
	}
	public static void test02(){
		System.out.println(SystemUtil.getValue());
	}
}

class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println("son thread"+SystemUtil.getValue());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class SystemUtil{
	private static ThreadLocal<Integer> tl=new ThreadLocal<Integer>();
	
	public static int getValue(){
		return tl.get();  //当前线程的ID
	}
	
	public static void setValue(int n){
		if(tl.get()==null){
			tl.set(5);
		}
	}
}