package com.foreveross.webbase.weixin.sdk.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.foreveross.webbase.common.utils.StringUtils;




/**
 * 日期工具类
 * 
 * @author zxming4
 * @createTime 2014-12-31
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class DateUtil {
	
	/**
	 * 获取当前日期的前一天
	 * @return
	 */
	public static String getYesterday(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar=Calendar.getInstance();  
	    calendar.setTime(new Date());
	    calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1);
	    return sdf.format(calendar.getTime());
	}
	
	
	/**
	 * string转Date
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDateTime(String str) throws ParseException{
		if (!StringUtils.isBlank(str)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(str);
		}
		
		return null;
	}
	
	
	/**
	 * 获得月第一天
	 * @param month（0本月-1上月1下月）
	 * @param simpleDateFormat（如：new SimpleDateFormat("yyyyMMdd")）
	 * @return
	 */
	public static String getMonthFirstDay(int month, SimpleDateFormat simpleDateFormat) {
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return simpleDateFormat.format(c.getTime());
	}

	/**
	 * 获得星期一的日期
	 * @param weeks（0本周-1上周+1下周）
	 * @param simpleDateFormat（如：new SimpleDateFormat("yyyyMMdd")）
	 * @return
	 */
	public static String getMonday(int weeks, SimpleDateFormat simpleDateFormat) {
		int mondayPlus = getMondayPlus();  
		GregorianCalendar currentDate = new GregorianCalendar();  
		currentDate.add(GregorianCalendar.DATE, mondayPlus+7*weeks);  
		Date monday = currentDate.getTime();  
		DateFormat df = DateFormat.getDateInstance();  
		if (null!=simpleDateFormat) {
			df = simpleDateFormat;
		}
		String preMonday = df.format(monday);  
		return preMonday;  
	}  

	/**
	 * 获得当前日期与本周一相差的天数  
	 * @return
	 */
	public static int getMondayPlus() {  
	Calendar cd = Calendar.getInstance();  
	// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......  
	int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);  
		if (dayOfWeek == 1) {  
		    return -6;  
		} else {  
		   return 2 - dayOfWeek;  
		}  
	}

	/**
	 * 
	 * lhyan3
	 * 2015年3月10日上午11:12:17
	 * TODO 获取当前时间的一个月以前的第一天
	 * @param format
	 * @return
	 */
	public static String getFirstDayBeforeOneMonth(String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String str = dateFormat.format(date);
		return str;
	}  
	
	public static void main(String[] args) {
		/*String s = getFirstDayBeforeOneMonth("yyyy-MM-dd");
//		System.out.println( s );*/
//		Calendar now = Calendar.getInstance();
//		 System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + ""); 
//		 System.out.println("年: " + (now.get(Calendar.YEAR) ) + ""); 
		 System.out.println("日: " + ( Calendar.getInstance().get(Calendar.DATE) ) + ""); 
	
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date now =null;
		 String d = sdf.format(now);
		 
		try {
			now = sdf.parse("2016-04-07 11:32:15");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println( now );
	}

	/**
	 * 
	 * lhyan3
	 * 2015年3月14日下午4:21:45
	 * TODO 日期格式化
	 * @param value
	 * @param string
	 * @return
	 * @throws ParseException 
	 */
	public static Date format(Object value, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = simpleDateFormat.parse(value.toString());
		return date;
	}

	/**
	 * 把日期转化成规定的字符串
	 * lhyan3
	 * 2015年4月9日上午9:42:22
	 * TODO
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseDate(Date date,String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String result = simpleDateFormat.format(date);
		return result;
	}
	
	/**
	 * 获取系统当全时间的月份
	 * 2015年8月17日上午9:11:55
	 * TODO
	 * @return
	 */
	public static String getSysMonth(){
		String result = "";
		Calendar now = Calendar.getInstance();
		
		result = now.get(Calendar.MONTH) + 1 +"";	//假设当前月为8月份，输出：8
		/*
		 *orcal中获取的月份为01、02...、11、12 
		 *所以再前面拼接一个0
		 */
		if( result.length() < 2){
			result = "0" + result;	// 输出：08
		}
		
		return result;	
	}
	
	/**
	 * 获取当前时间的年份
	 * 2016年1月4日下午2:48:49
	 * TODO
	 * @return
	 */
	public static String getSysYear(){
		Calendar now = Calendar.getInstance();
		
		return now.get(Calendar.YEAR) +"";
	}
	
	/**
	 * 当前时间是否在有效时间范围内
	 * @param begin_time	起始时间
	 * @param end_time		结束时间
	 * @return
	 */
	public static boolean isValidTime(Date begin_time,
			Date end_time) {
		boolean result = false;

		Date now = new Date();
		SimpleDateFormat sdf   =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date  = sdf.format(now);
		try {
			now  = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int begin = now.compareTo(begin_time);
		int end = now.compareTo(end_time);
		
		
		if((end==-1 || end ==0)){
			result = true;
			}
		
		/*if((begin==0 || begin==1) && ==-1 ){
			result = true;
		}*/
		
		return result;
	}
}



