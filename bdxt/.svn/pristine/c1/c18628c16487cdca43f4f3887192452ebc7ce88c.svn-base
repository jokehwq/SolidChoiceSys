package com.foreveross.webbase.weixin.sdk.util;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author 
 * @createTime 
 * @history  1.修改时间,修改;修改内容：
 * 
 */
public class UUIDUtil {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getUUIDByNum(int num){ 
        String s = getUUID(); 
        return s.substring(0,num); 
    } 
	
	/** 
     * 获得指定数目的UUID 
     * @param counts int 需要获得的UUID数量 
     * num int 需要的位数
     * @return String[] UUID数组 
     */ 
    public static String[] getUUID(int counts,int num){ 
        if(counts < 1){ 
            return null; 
        } 
        String[] ss = new String[counts]; 
        for(int i=0;i<counts;i++){ 
            ss[i] = getUUIDByNum(num); 
        } 
        return ss; 
    } 
    
    public static void main(String[] args) {
    	String s = getUUIDByNum(25); 
    	System.out.println(s);
	}
}
