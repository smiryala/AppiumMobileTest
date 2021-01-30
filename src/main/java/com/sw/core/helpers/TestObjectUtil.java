package com.sw.core.helpers;

import io.qameta.allure.Allure;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class TestObjectUtil extends MobileBaseUtil {

	private static final Logger LOGGER = Logger.getLogger(TestObjectUtil.class);

	/**
	 * API call to check the available devices
	 *
	 * @param username
	 * @param key
	 * @param baseUrl
	 * @param endPointURL
	 * @return
	 * @throws Exception
	 */
	public static List<String> getAvailableDevices(String username, String key, String baseUrl,
			String endPointURL)
			throws Exception {

		List<String> deviceList = null;
		try {

			String URL = baseUrl + endPointURL;
			URL obj = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			String authString = username + ":" + key;
			String authStringEnc = new Base64().encodeAsString(authString.getBytes());
			con.setRequestProperty("Authorization", "Basic " + authStringEnc);
			con.setRequestProperty("X-RateLimit-Enable", "true");

			int responseCode = con.getResponseCode();
			ConsoleLog.info("Response Code to get Available Devices : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			deviceList = new ArrayList<>(Arrays.asList(response.toString().split(",")));
		} catch (Exception e) {
			LOGGER.info("Error in getAvailableDevices" + e.getMessage());
			Allure.step("Error in getAvailableDevices " + e.getMessage());
		}

		return deviceList;
	}

//	public static void main(String[] args) {
//		String username = "";
//		String key = "";
//		String baseUrl = "";
//		String endPointURL = "";
//
//		try {
//			getAvailableDevices(username,key,baseUrl,endPointURL,"iPhone_XS_Maxsw_us");
//		} catch (Exception e) {
//			LOGGER.error("Error in connecting testobject api", e);
//		}
//	}
}
