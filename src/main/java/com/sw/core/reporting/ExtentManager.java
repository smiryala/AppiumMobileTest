package com.sw.core.reporting;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentManager {

    static ExtentReports extent;

    public static ExtentReports getInstance() {
        return extent;
    }

    /**
     * @param fileName
     * @return
     */
    public static synchronized ExtentReports createInstance(String fileName) {
        ExtentSparkReporter sparkReporterMobile = new ExtentSparkReporter(fileName).viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{
                        ViewName.TEST,
                        ViewName.DASHBOARD,
                        ViewName.CATEGORY,
                        ViewName.AUTHOR,
                        ViewName.DEVICE,
                        ViewName.EXCEPTION,
                        ViewName.LOG
                })
                .apply();
        ;
        sparkReporterMobile.config().setEncoding("utf-8");
        sparkReporterMobile.config().setDocumentTitle("Sherwin Williams");
        sparkReporterMobile.config().setReportName("Sherwin Williams Mobile Automation");
        sparkReporterMobile.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporterMobile);
        extent.setSystemInfo("Application Name", "Sherwin Williams - eCommerce -Mobile Apps");
        //extent.setSystemInfo("Platform", System.getProperty("os.name"));

        return extent;
    }
}