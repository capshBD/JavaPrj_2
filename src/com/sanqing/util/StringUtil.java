package com.sanqing.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rx.util.CommonUtil;

public class StringUtil {
	
	public  static String getMapToString(Map map,String key) {
			Object obj=map.get(key);
			if(obj==null){
				obj=map.get(key.toUpperCase());
			}
			return CommonUtil.getStringValue(obj);
	}
	
	public static Map keyCaseChange(Map m) {
		Map returnMap=new HashMap();
		
		Iterator keyIterator=m.keySet().iterator();
		while(keyIterator.hasNext()){
			String oldKey=(String) keyIterator.next();
			returnMap.put(oldKey.toLowerCase(), m.get(oldKey));
		}
		return returnMap;
	}

	/**
	 * 3.将List<Map<String, Object>>集合key转换为小写
	 * @param dataList
	 * @return
	 */
	public static List<Map<String, Object>> toLowerCaseMapKeyOfListO(List<Map<String,Object>> dataList) {
		List<Map<String, Object>> rtnDataList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> dataListMap : dataList) {			
			rtnDataList.add(keyCaseChange(dataListMap)); 			
		}
		return rtnDataList;
	}
	
	public static boolean isNullString(String str) {
		
		return (null==str||"".equals(str.trim()))?true:false;
	}
	
	/**
	 * 对象转为字符串 会去除前后空白符 为null时返回空字符串""
	 * @param obj 对象
	 * @return
	 */
	public static String toString(Object obj){
		return obj == null ? "" : obj.toString().trim();
	}
	
	public static String getStringValue(Map<Object,Object> map, String key) {
		    Object obj = map.get(key);
		    if (obj == null)
		      obj = map.get(key.toUpperCase());

		    return CommonUtil.getStringValue(obj);
		  }
	
	/**
	 * 根据字节数截取字符串
	 */
	public static String subStringOfByte(String str,int byteNum){
		byte[] bytes = str.getBytes();
		if(byteNum<=0){
			throw new StringIndexOutOfBoundsException(byteNum);
		}else if (bytes.length<byteNum) {
			return str;
		}else {
			if(bytes[byteNum]<0){
				return  new String(bytes,0,--byteNum);
				
			}else {
				return  new String(bytes,0,byteNum);
			}
		}
	}
	
	/** 判断是否为非null */
	public static boolean isNotNull(String str) {
		return !isNullString(str);
	}
}
