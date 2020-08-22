package com.assignment.rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	
	/*
	 * getValue returns the value of the key provided as an input. It also
	 * takes file path as an input.
	 */
	public static String getValue(String path, String key)
	{
		String propertyValue = null;
		FileInputStream FIS = null;
		Properties prop = new Properties();
		try {
			FIS = new FileInputStream(path);
			prop.load(FIS);
			propertyValue = prop.getProperty(key);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (FIS != null) {
				try {
					FIS.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
		}
		return propertyValue;
		}
	
	
	/* Get OS name
	 * @author: Prabodh G
	 * @return : OS name
	 */
	public static String getOS()
	{
		return System.getProperty("os.name");
	}
	
	
}
