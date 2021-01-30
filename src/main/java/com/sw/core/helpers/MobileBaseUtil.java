package com.sw.core.helpers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sw.core.helpers.Security.EncryptionUtil;

public class MobileBaseUtil {


	public static EncryptionUtil encryptionUtil = null;
	public static Properties loadServiceProps = null ;
	private static final Logger LOGGER = Logger.getLogger(MobileBaseUtil.class);
	
	static {
			try (InputStream input = new FileInputStream("src/test/resources/config/services.properties")) {

				loadServiceProps = new Properties();
	            loadServiceProps.load(input);
			    //loadServiceProps = PropsUtil.setSystemProperties("config/services.properties");
		} catch (Exception e) {
			LOGGER.info("Error in loading service properties");
		}
	}

	protected static String getPropertyValue(Properties prop, String key)
	{
		return prop.getProperty(key);
	}
}
