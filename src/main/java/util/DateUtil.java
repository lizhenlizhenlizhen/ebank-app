package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * @author ZhenLi
 *
 */
public class DateUtil {

	public static String formateDate(Date date,String formatStr){
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
		String result = sdf.format(date);
		return result;
	}
	
	public static Date parseDate(String formatStr,String value){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(formateDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(parseDate("yyyy-MM-dd","2019-10-8"));
	}
	
}
