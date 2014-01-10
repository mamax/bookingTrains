package ua.gov.uz.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {

	Properties CONFIG = new Properties();
	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//ua//gov//uz//config//config.properties");
	CONFIG.load(ip);
	
	System.out.println(CONFIG.getProperty("screenshotPath"));
	
	Properties OR = new Properties();
	ip = new FileInputStream(System.getProperty("user.dir")+"//src//ua//gov//uz//config//config.properties");
	OR.load(ip);
	System.out.println(OR.getProperty("baseUrl"));
	System.out.println(OR.getProperty("fromCity"));
	System.out.println(OR.getProperty("dateForward"));
	System.out.println(System.getProperty("user.dir")+ "\\libs\\chromedriver.exe");

	
	}

}
