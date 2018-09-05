/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	public static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "HH:mm:ss"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	/**
	 * 设置时间格式
	 */
	public static String getDate(Date date,String pattern) {
		return DateFormatUtils.format(date, pattern);
	}
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}


	/**
	 * 获取日期
	 */
	public static String getDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}


	/**
	 * 获取时分秒
	 */
	public static String getHourTime(Date date){return formatDate(date, "HH:mm:ss");}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}


	/**
	 *
	 * 功能说明：根据当前日期计算出后面几个月后的日期或者 几天后的日期
	 * @param type:2 代表月 其它代表日
	 * @param style 指定格式化日期样式 默认 yyyy-MM-dd HH:mm:ss
	 * @param increment 数字
	 * @return   
	 */
	public static String getLastTime(int type, int increment,String style) throws ParseException {
		if (style == null || "".equals(style)) {
			style = parsePatterns[1];
		}
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (type == 2)
			calendar.add(Calendar.MONTH, increment);
		else
			calendar.add(Calendar.YEAR, increment);

		String sdate = sdf.format(calendar.getTime());
		calendar.setTime(sdf.parse(sdate));
		return sdate;
	}


	/**
	 *
	 * 功能说明：比较两个日期 大小
	 * panye  2014-11-29
	 * @param DATE1 日期1  DATE2 日期2
	 * @return   返回 int (-1 ：日期1 大于 日期2,0 ：日期1 小于日期2, 1： 日期1 等于日期2)
	 * @throws 
	 * 最后修改时间：
	 * 修改人：panye
	 * 修改内容：
	 * 修改注意点：
	 */
	public static int compareDate(Date dt1, Date dt2) {
		int i = 0;
		try {
			//Date dt1 = df.parse(DATE1);
			//Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				i = -1;
			} else if (dt1.getTime() < dt2.getTime()) {
				i = 0;
			} else if (dt1.getTime() == dt2.getTime()) {
				i = 1;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return i;
	}

	//获取当天6点
	public static String getDateByHour(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 6);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date m6 = c.getTime();
		return formatDateTime(m6);
	}

	//获取第2天5点59分
	public static String getDateByFiveHours(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, +1);
		c.set(Calendar.HOUR_OF_DAY, 5);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date  nextDay=c.getTime();
		return formatDateTime(nextDay);
	}


	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
		System.out.printf("m6:"+(float) 7/2);

	}
}
