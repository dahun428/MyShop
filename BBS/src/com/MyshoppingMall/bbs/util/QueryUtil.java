package com.MyshoppingMall.bbs.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;



public class QueryUtil {

	private static Properties prop = new Properties();
	

	
	static {
		try {
			
			prop.load(new FileInputStream(""));
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static String getSQL(String name) {
		return prop.getProperty(name);
	}
		
	
}
