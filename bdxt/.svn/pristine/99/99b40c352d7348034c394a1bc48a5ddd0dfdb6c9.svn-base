package com.foreveross.webbase.common.utils;


import com.foreveross.webbase.bdxt.web.app.entity.response.ArgumentInvalidResult;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class CommonUtils {

    private static final String SEP1 = ",";

    /**
     * List转换String
     *
     * @param list:需要转换的List
     * @return String转换后的字符串
     */
    public static String ListToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                // 如果值是list类型则调用自己
                if (list.get(i) instanceof List) {
                    sb.append(ListToString((List<?>) list.get(i)));
                    sb.append(SEP1);
                } else if (list.get(i) instanceof Map) {
                    sb.append(MapToString((Map<?, ?>) list.get(i)));
                    sb.append(SEP1);
                } else {
                    sb.append(list.get(i));
                    sb.append(SEP1);
                }
            }
            return sb.toString().
                    substring(0, sb.length() - 1);
        }
        return "";
    }


    /**
     * Map转换String
     *
     * @param map :需要转换的Map
     * @return String转换后的字符串
     */
    public static String MapToString(Map<?, ?> map) {
        StringBuffer sb = new StringBuffer();
        // 遍历map
        for (Object obj : map.keySet()) {
            if (obj == null) {
                continue;
            }
            Object key = obj;
            Object value = map.get(key);
            if (value instanceof List<?>) {
                sb.append(key.toString() + SEP1 + ListToString((List<?>) value));
                sb.append(SEP1);
            } else if (value instanceof Map<?, ?>) {
                sb.append(key.toString() + SEP1 + MapToString((Map<?, ?>) value));
                sb.append(SEP1);
            } else {
                sb.append(key.toString() + SEP1 + value.toString());
                sb.append(SEP1);
            }
        }
        return sb.toString();
    }

    //反射获取单个对象的所有键值
    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    //处理请求参数异常 list转String
    public static String validListToString(List<ArgumentInvalidResult> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i).getDefaultMessage());
            } else {
                sb.append(list.get(i).getDefaultMessage());
                sb.append(";");
            }
        }
        return sb.toString();
    }

    //找出两个数组中不相同的元素
    public static <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1);
        List<T> list2 = new ArrayList<T>();
        for (T t : t2) {
            if (!list1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
    }

    //数组转换成string
    public static String arrayString(String[] str) {
        if (str == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String s : str) {
            sb.append(s).append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    //精确减法运算的sub方法
    public static BigDecimal sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(1);
        List<Integer> tempList= new ArrayList<Integer>();
        for(Integer i:list){
            if(!tempList.contains(i)){
                tempList.add(i);
            }
        }
        for(Integer i:tempList){
            System.out.println(i);
        }


    }


    //生成订单号
    public static String getOrderNumByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodev = UUID.randomUUID().toString().hashCode();
        System.out.println(UUID.randomUUID().toString());
        if (hashCodev < 0) {
            //有可能是负数
            hashCodev = -hashCodev;
        }
        //"%015d"的意思：0代表不足位数的补0，这样可以确保相同的位数，15是位数也就是要得到到的字符串长度是15，d代表数字。
        return machineId + String.format("%015d", hashCodev);
    }
}
