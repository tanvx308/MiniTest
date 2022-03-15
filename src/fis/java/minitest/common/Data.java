package fis.java.minitest.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fis.java.minitest.model.Account;
import fis.java.minitest.model.Transaction;

public class Data {
	public static List<Account> list = new ArrayList<>();
	
	public static Map<Integer, String> map = new HashMap<>();
	
	public static List<Transaction> transactions = new ArrayList<>();
	
	static {
		
		//data for list
		list.add(new Account(1L, "Duong Tang", "050100112233", 100000d, 1));
		list.add( new Account(2L, "Ton Ngo Khong", "050100112234", 500000d, 1));
		list.add( new Account(3L, "Xa Tang", "050100112235", 200000d, 1));
		list.add(new Account(4L, "Chu Bat Gioi", "050100112236", 1000000d, 1));
		
		
		//data for status
		map.put(0, "Het hieu luc");
		map.put(1, "Hieu luc");
		map.put(2, "Tam khoa");
	}
	

}
