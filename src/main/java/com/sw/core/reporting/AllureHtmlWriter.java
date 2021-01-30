package com.sw.core.reporting;

import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.log4j.Logger;

public class AllureHtmlWriter {

    private static final Logger LOGGER = Logger.getLogger(AllureHtmlWriter.class);

    public static void getHtmlReport(String key, String value, String fileName) {
        try {
            // define a HTML String Builder
            StringBuilder htmlStringBuilder = new StringBuilder();
            // append html header and title
            htmlStringBuilder.append("<html><head><title>Saucelabs Mobile Report</title></head>");
            // append body
            htmlStringBuilder.append("<body>");
            // append table
            htmlStringBuilder.append("<table>");
            // append row
            htmlStringBuilder
                    .append("<tr><td><b>" + key + "</b></td><td><b>" + value + "</b></td></tr>");
            // close html file
            htmlStringBuilder.append("</table></body></html>");
            // write html string content to a file
            WriteToFile(htmlStringBuilder.toString(), fileName);
        } catch (Exception e) {
            LOGGER.info("Error in Allure HtmlReport" + e.getMessage());
            Allure.step("Error in Allure HtmlReport " + e.getMessage());
        }
    }

    public static void WriteToFile(String fileContent, String fileName) throws IOException {
        File allureResultsDir = new File(System.getProperty("user.dir") + "/target/");
        if (!allureResultsDir.exists()) {
            allureResultsDir.mkdirs();
        }
        String tempFile = allureResultsDir + File.separator + fileName;
        File file = new File(tempFile);
        if (allureResultsDir.exists()) {

            OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
            Writer writer = new OutputStreamWriter(outputStream);
            writer.write(fileContent);
            writer.close();
        }
    }

//	public static void main(String[] args) {
//		getHtmlReport();
//	}
}