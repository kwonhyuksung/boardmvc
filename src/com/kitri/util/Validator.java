package com.kitri.util;

public class Validator {
	
	public static String nullToBlank(String tmp) {
		return tmp == null ? "" : tmp.trim();
	}

	public static int notNumberToZero(String tmp) {
		int num = 0;
		
		if (tmp != null && isNumber(tmp)) {
			num = Integer.parseInt(tmp);			
		}
		
		return num;
		
	}
	
	public static int notNumberToOne(String tmp) {
		int num = 1;
		
		if (tmp != null && isNumber(tmp)) {
			num = Integer.parseInt(tmp);			
		}
		
		return num;
		
	}

	private static boolean isNumber(String tmp) {
		boolean flag = true;
		
		int len = tmp.length();
		for(int i = 0; i < len; i++) {
			int x = tmp.charAt(i) - 48;
			if (x < 0 || x > 9) {
				flag = false;
				break;
			}			
		}
		
		return flag;		
			
	}
	
}
