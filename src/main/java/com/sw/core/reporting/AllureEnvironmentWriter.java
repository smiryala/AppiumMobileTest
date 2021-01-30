package com.sw.core.reporting;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AllureEnvironmentWriter {

    private static final Logger LOGGER = Logger.getLogger(AllureEnvironmentWriter.class);

    public static void allureEnvironmentWriter(ImmutableMap<String, String> environmentValuesSet) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element environment = doc.createElement("environment");
            doc.appendChild(environment);
            environmentValuesSet.forEach((k, v) -> {
                Element parameter = doc.createElement("parameter");
                Element key = doc.createElement("key");
                Element value = doc.createElement("value");
                key.appendChild(doc.createTextNode(k));
                value.appendChild(doc.createTextNode(v));
                parameter.appendChild(key);
                parameter.appendChild(value);
                environment.appendChild(parameter);
            });

            // Write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File allureResultsDir = new File(System.getProperty("user.dir")
                    + "/target/allure-results");
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }
            StreamResult result = new StreamResult(
                    new File(System.getProperty("user.dir")
                            + "/target/allure-results/environment.xml"));
            transformer.transform(source, result);
            Allure.step("Allure environment data saved.");
        } catch (Exception e) {
            LOGGER.info("Error in allureEnvironmentWriter" + e.getMessage());
            Allure.step("Error in allureEnvironmentWriter " + e.getMessage());
        }

    }


    public static void allureEnvironmentWriter(ImmutableMap<String, String> environmentValuesSet,
            String customResultsPath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element environment = doc.createElement("environment");
            doc.appendChild(environment);
            environmentValuesSet.forEach((k, v) -> {
                Element parameter = doc.createElement("parameter");
                Element key = doc.createElement("key");
                Element value = doc.createElement("value");
                key.appendChild(doc.createTextNode(k));
                value.appendChild(doc.createTextNode(v));
                parameter.appendChild(key);
                parameter.appendChild(value);
                environment.appendChild(parameter);
            });

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File allureResultsDir = new File(customResultsPath);
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }
            StreamResult result = new StreamResult(
                    new File(customResultsPath + "environment.xml"));
            transformer.transform(source, result);
            System.out.println("Allure environment data saved.");
        } catch (Exception e) {
            LOGGER.info("Error in allureEnvironmentWriter" + e.getMessage());
            Allure.step("Error in allureEnvironmentWriter " + e.getMessage());
        }
    }
}