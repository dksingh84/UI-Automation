package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig(){
		
		File src=new File("./Configuration/config.properties");
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is:" +e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getApplicationURL(){
		
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getUsername(){
		
		String username=pro.getProperty("userName");
		return username;
	}

	public String getPassword(){
	
	String password=pro.getProperty("password");
	return password;
	}

	public String getChromePath(){
	
	String chromepath=pro.getProperty("chromepath");
	return chromepath;
	}
	
	public String getFirefoxPath(){
		
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}
	
	public String getEdgePath(){
		
		String edgepath=pro.getProperty("edgepath");
		return edgepath;
	}
	
	
	public String getTitle(){
		
		String getTitle=pro.getProperty("title");
		return getTitle;
	}
	
	public String getTestDataPath(){
		
		String testdatapath=pro.getProperty("testdata");
		return testdatapath;
	}
	
	
}
