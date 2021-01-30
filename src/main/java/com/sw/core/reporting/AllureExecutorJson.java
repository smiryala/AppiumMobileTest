package com.sw.core.reporting;

import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileWriter;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class AllureExecutorJson {

	private static final Logger LOGGER = Logger.getLogger(AllureExecutorJson.class);

	public static String generateExecutor() {
		JSONObject json = new JSONObject();
		json.put("name", "Jenkins");
		json.put("type", "Jenkins");
		json.put("url", "http://svdevbuild01:8080/");
		json.put("buildName", "SmokeTests-QA-BOPIS #");
		json.put("buildUrl", "http://svdevbuild01:8080/job/SmokeTests-QA-BOPIS/");
		json.put("reportName", "SmokeTests-QA-BOPIS Jenkins Report");
		return json.toString();
	}

	public static void allureExecutorWriter() {

		File allureResultsDir = new File(System.getProperty("user.dir") + "/target/allure-results");
		if (!allureResultsDir.exists()) {
			allureResultsDir.mkdirs();
		}
		try {
			FileWriter file = new FileWriter(
					new File(System.getProperty("user.dir")
							+ "/target/allure-results/executor.json"));
			file.write(generateExecutor());
			file.close();
		} catch (Exception e) {
			LOGGER.info("Error in allureExecutorWriter" + e.getMessage());
			Allure.step("Error in allureExecutorWriter " + e.getMessage());
		}

	}

//	public static void main(String args[]) throws Exception {
//
//		allureExecutorWriter(generateExecutor());
//	}
}

