package com.itstack.netty.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SessionStack {

	 private  static SessionStack self = null;
	 private  static int total=0; //总得客户端数
	 private  static Map<String ,Integer> map=new HashMap<String, Integer>();//每个客户端做的交易数
//	 private static Logger logger = Logger.getLogger(SessionStack.class);
	 private SessionStack(){
		 
	 }
	 public static SessionStack getInstance(){
		 if(self == null){
			  self = new SessionStack();
		  }	 
	    return self;
	 }
	public static int getTotal() {
		return total;
	}
	public static void setTotal(int total) {
		SessionStack.total = total;
		System.out.println("客户端总数 "+total);
	}
	public static Map<String, Integer> getMap() {
		return map;
	}
	public static void setMap(Map<String, Integer> map) {
		SessionStack.map = map;
		System.out.println("交易明细 "+map);
	}
	public static void remove(String key) {
		map.remove(key);
		System.out.println("客户端移出  "+key);
	}
	

	 
}
