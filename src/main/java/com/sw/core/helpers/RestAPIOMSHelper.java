package com.sw.core.helpers;

import com.sw.core.helpers.Security.EncryptionUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class RestAPIOMSHelper extends MobileBaseUtil{
	
	private static final Logger LOGGER = Logger.getLogger(RestAPIOMSHelper.class);

	public static String getQAOMSOrderStatus(String orderNo) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String omsStatus = null;
		try {
			HttpPost postRequest = new HttpPost("https://qa-oms.sherwin-williams.com/smcfs/interop/InteropHttpServlet");
			ArrayList<NameValuePair> postParameters;
			String input = "<Order OrderNo=\"PA_" + orderNo + "\" EnterpriseCode=\"SHW_US\" />";
			postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("YFSEnvironment.userId", "oms-qa-auto-validation"));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.password", ""));
			postParameters.add(new BasicNameValuePair("InteropApiData", input.toString()));
			postParameters.add(new BasicNameValuePair("TemplateData", ""));
			postParameters.add(new BasicNameValuePair("InteropApiName", "getOrderList"));
			postParameters.add(new BasicNameValuePair("ApiName", "getOrderList"));
			postParameters.add(new BasicNameValuePair("IsFlow", "N"));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.progId", "SterlingHttpTester"));
			postParameters.add(new BasicNameValuePair("ServiceName", ""));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.version", ""));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.locale", ""));

			// HttpResponse response = httpClient.execute(postRequest);
			postRequest.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			response = httpClient.execute(postRequest);

			String responseString = new BasicResponseHandler().handleResponse(response);
			int i = responseString.indexOf(" Status=");
			String firstIndex = responseString.substring(i, responseString.length());
			int j = firstIndex.indexOf("TaxExemptFlag=");
			omsStatus = firstIndex.substring(0, j);
			// System.out.println("response " + responseString);
			System.out.println("omsStatus " + omsStatus);
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		} finally {
			try {
				httpClient.close();
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return omsStatus;

	}

	public static String getOMSOrderStatus(String orderNo,String username,String password, String url) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String omsStatus = null;
		try {
			HttpPost postRequest = new HttpPost(url);
			ArrayList<NameValuePair> postParameters;
			String input = "<Order OrderNo=\"" + orderNo + "\" EnterpriseCode=\"SHW_US\" />";
			postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("YFSEnvironment.userId", username));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.password", password));
			postParameters.add(new BasicNameValuePair("InteropApiData", input.toString()));
			postParameters.add(new BasicNameValuePair("TemplateData", ""));
			postParameters.add(new BasicNameValuePair("InteropApiName", "getOrderList"));
			postParameters.add(new BasicNameValuePair("ApiName", "getOrderList"));
			postParameters.add(new BasicNameValuePair("IsFlow", "N"));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.progId", "SterlingHttpTester"));
			postParameters.add(new BasicNameValuePair("ServiceName", ""));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.version", ""));
			postParameters.add(new BasicNameValuePair("YFSEnvironment.locale", ""));

			// HttpResponse response = httpClient.execute(postRequest);
			postRequest.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			response = httpClient.execute(postRequest);

			String responseString = new BasicResponseHandler().handleResponse(response);
			int i = responseString.indexOf(" Status=");
			String firstIndex = responseString.substring(i, responseString.length());
			int j = firstIndex.indexOf("TaxExemptFlag=");
			omsStatus = firstIndex.substring(0, j);
			//System.out.println("response " + responseString);
			System.out.println("omsStatus for orderNo " + omsStatus);
			LOGGER.info("omsStatus for orderNo " + omsStatus);
		} catch (Exception e) {
			LOGGER.info("Error in calling OMS Status service" + e.getMessage());
			Allure.step("Error in calling OMS Status service " + e.getMessage());
		} finally {
			try {
				httpClient.close();
				response.close();
			} catch (IOException e) {
				LOGGER.info("Error in connections closing");
			}
		}
		return omsStatus;
	}

	@Step()
	public static String getQAOrderStatus(String orderNo) {

		String orderStatus = null;
		try {
			encryptionUtil = new EncryptionUtil();
			orderStatus = getOMSOrderStatus(orderNo,
					getPropertyValue(loadServiceProps, "oms_qa.service.username"), encryptionUtil
							.decrypt(getPropertyValue(loadServiceProps, "oms_qa.service.password"))
					, getPropertyValue(loadServiceProps, "oms_qa.service.url"));
		} catch (Exception e) {
			LOGGER.info("Error in calling OMS OrderStatus " + e.getMessage());
		}
		return orderStatus;
	}


	public static String getDEVOrderStatus(String orderNo) {
		
		String orderStatus = null;
		try {
			encryptionUtil = new EncryptionUtil();
			orderStatus = getOMSOrderStatus(orderNo,getPropertyValue(loadServiceProps,"oms_dev.service.username"),encryptionUtil.decrypt(getPropertyValue(loadServiceProps,"oms_dev.service.password"))
				,getPropertyValue(loadServiceProps,"oms_dev.service.url"));
		} catch (Exception e) {
			LOGGER.info("Error in calling OMS OrderStatus "+e.getMessage());
		}
		return orderStatus;
	}
	
	//public static void main(String[] args) {
		//System.out.println(getQAOrderStatus("WC_2700298057"));
		//System.out.println(getDEVOrderStatus("1000203004"));
		//getQAOMSOrderStatus("2000292605");
	//}

}