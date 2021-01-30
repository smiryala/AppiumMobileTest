package com.sw.core.helpers;

import com.saucelabs.ci.sauceconnect.SauceTunnelManager;
import com.saucelabs.saucerest.SauceREST;
import com.sw.core.testsuites.MobileDriverFactory;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class SauceUtil {
    public static final String SAUCE_USER_NAME = "sauce.UserName";
    public static final String SAUCE_KEY = "sauce.key";

    /**
     * Opens the Sauce Connect tunnel using the given TunnelManager.
     *
     * @param sauceTunnelManager SauceTunnelManager
     * @throws IOException for failed connection
     */
    public static void tryOpenSauceConnection(SauceTunnelManager sauceTunnelManager) throws IOException {
        if (Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_ENABLED)) && Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_CONNECT_ENABLED))) {
            ConsoleLog.info("Sauce Connect is enabled, Starting Sauce Connect.");
            File SauceConnectJar = new File("target" + File.separator + "jars", "SauceConnect-3.0.28.jar");
            String path = SauceConnectJar.getAbsolutePath();
            File SauceConnectJar2 = new File(path);
            sauceTunnelManager.openConnection(System.getProperty(MobileDriverFactory.SAUCE_USER_NAME),
                    System.getProperty(MobileDriverFactory.SAUCE_KEY), 80, SauceConnectJar2, "", null,
                    true, null);
            ConsoleLog.info("Sauce Connect setup is finished.");
        } else {
            ConsoleLog.info("Sauce Connect is disabled, skipping Sauce Connect startup.");
        }
    }

    /**
     * Attempts to close the Sauce Connect Tunnel. <br>
     * This does check that Sauce Connect is enabled first.
     *
     * @param sauceTunnelManager SauceTunnelManager
     */
    public static void tryCloseSauceConnection(SauceTunnelManager sauceTunnelManager) {
        if (Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_ENABLED))) {
            if (Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_CONNECT_ENABLED))) {
                ConsoleLog.info("Closing Sauce Connect Tunnel.");
                sauceTunnelManager.closeTunnelsForPlan(System.getProperty(MobileDriverFactory.SAUCE_USER_NAME),
                        "", null);
                ConsoleLog.info("Finished Closing Sauce Connect Tunnel");
            }
        }
    }

    /**
     * Will update the job information on sauce with the max-duration and current
     * test case name.
     *
     * @param webDriver           WebDriver
     * @param currentTestCaseName Name of the current test
     */
    public static void updateJobInformation(WebDriver webDriver, String currentTestCaseName) {

        if (Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_ENABLED))) {
            String jobId = ((RemoteWebDriver) webDriver).getSessionId().toString();
            // Open up a Sauce Client
            String sauceUN = System.getProperty(SAUCE_USER_NAME);
            String sauceKey = System.getProperty(SAUCE_KEY);
            SauceREST client = new SauceREST(sauceUN, sauceKey);
            Map<String, Object> updates = new HashMap<>();
            // Sets the given test case name
            updates.put("name", currentTestCaseName);
            // Timeout set to 5 minutes
            updates.put("max-duration", 600);

            client.updateJobInfo(jobId, updates);
        }
    }

    /**
     * Passes the current job for the given WebDriver
     *
     * @param webDriver WebDriver
     */
    public static void passJob(WebDriver webDriver) {
        if (Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_ENABLED))) {
            ConsoleLog.info("Setting status to failure in Sauce for the current job.");
            String jobId = ((RemoteWebDriver) webDriver).getSessionId().toString();
            // Open up a Sauce Client
            String sauceUN = System.getProperty(SAUCE_USER_NAME);
            String sauceKey = System.getProperty(SAUCE_KEY);
            SauceREST client = new SauceREST(sauceUN, sauceKey);
            Map<String, Object> updates = new HashMap<>();

            // Sets the current job to a passed job
            client.jobPassed(jobId);

            client.updateJobInfo(jobId, updates);
        }

    }

    /**
     * Fails the current job for the given WebDriver
     *
     * @param webDriver WebDriver
     */
    public static void failJob(WebDriver webDriver) {
        if (Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_ENABLED))) {
            ConsoleLog.info("Setting status to pass in Sauce for the current job.");

            String jobId = ((RemoteWebDriver) webDriver).getSessionId().toString();

            // Open up a Sauce Client
            String sauceUN = System.getProperty(SAUCE_USER_NAME);
            String sauceKey = System.getProperty(SAUCE_KEY);
            SauceREST client = new SauceREST(sauceUN, sauceKey);
            Map<String, Object> updates = new HashMap<>();

            // Fails the current job
            client.jobFailed(jobId);
            client.updateJobInfo(jobId, updates);
        }

    }

    /**
     * Changes status of the current test case for the given WebDriver
     *
     * @param webDriver WebDriver
     * @param status    test status, pass or fail
     */
    public static void changeStatusOfTestCase(WebDriver webDriver, String status) {
        if (Boolean.parseBoolean(System.getProperty(MobileDriverFactory.SAUCE_ENABLED))) {
            ConsoleLog.info("Setting status to " + status + " in Sauce for the current job.");

            String sessionId = ((RemoteWebDriver) webDriver).getSessionId().toString();

            // Open up a Sauce Client
            String sauceUN = System.getProperty(SAUCE_USER_NAME);
            String sauceKey = System.getProperty(SAUCE_KEY);
            SauceREST client = new SauceREST(sauceUN, sauceKey);
            Map<String, Object> updates = new HashMap<>();

            // changes status of the current test case
            if (status.equalsIgnoreCase("pass")) {
                updates.put("passed", true);
            } else if (status.equalsIgnoreCase("fail")) {
                updates.put("passed", false);
            }
            client.updateJobInfo(sessionId, updates);
        }
    }

    public static String getActiveTunnel() throws Exception {
        String userName = "SHERWIN-Admin-Group";
        String key = "28ef1928-3a74-4da1-ae7e-18803515d346";

        String url = "https://saucelabs.com/rest/v1/SHERWIN-Admin-Group/tunnels";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        // add request header
        String authString = "SHERWIN-Admin-Group" + ":" + "28ef1928-3a74-4da1-ae7e-18803515d346";
        String authStringEnc = new Base64().encodeAsString(authString.getBytes());
        con.setRequestProperty("Authorization", "Basic " + authStringEnc);
        con.setRequestProperty("X-RateLimit-Enable", "true");

        int responseCode = con.getResponseCode();
        ConsoleLog.info("Response Code to get active tunnel : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        List<String> tunnelList = new ArrayList<>(Arrays.asList(response.toString().split(",")));
        if (tunnelList.size() > 0) {
            String activeTunnel = tunnelList.get(0).replaceAll("[\\[\\]]", "").replaceAll("\"", "");
            ConsoleLog.info(" >>>>>>>>>> Active tunnel id: " + activeTunnel);
            return activeTunnel;
        }
        return null;
    }
}
