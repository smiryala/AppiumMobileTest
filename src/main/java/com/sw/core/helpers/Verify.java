package com.sw.core.helpers;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Verify {
    /**
     * Asserts that the given boolean is true.  If it is not true, then the current step will fail, and will be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param success      boolean to assert against
     * @param errorMessage String for the error message to print if it fails.
     */
    public static void isTrue(SoftAssert softAssert, boolean success, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, success, true);
        softAssert.assertTrue(success, errorMessage);
    }

    /**
     * Asserts that the given boolean is true.  If it is not true, then the current step will fail, and will be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param success      boolean to assert against
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void isTrue(SoftAssert softAssert, boolean success, String errorMessage, List<WebElement> webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, success, true);
        List<WebElement> elements = new ArrayList<>(webElements);

        setList(softAssert, elements);
        softAssert.assertTrue(success, errorMessage);
    }

    //isEquals

    /**
     * Asserts that the two given objects are equal.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       Object for the actual value to check
     * @param expected     Object for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void isEquals(SoftAssert softAssert, Object actual, Object expected, String errorMessage, List<WebElement> webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        setList(softAssert, webElements);

        softAssert.assertEquals(actual, expected, errorMessage);
    }

    /**
     * Asserts that the two given objects are equal.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       Object for the actual value to check
     * @param expected     Object for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     */
    public static void isEquals(SoftAssert softAssert, Object actual, Object expected, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        softAssert.assertEquals(actual, expected, errorMessage);
    }

    /**
     * Asserts that the two given objects are equal.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       Object for the actual value to check
     * @param expected     Object for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void isEquals(SoftAssert softAssert, Object actual, Object expected, String errorMessage, WebElement... webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        List<WebElement> elements = new ArrayList<>();
        Collections.addAll(elements, webElements);

        setList(softAssert, elements);

        softAssert.assertEquals(actual, expected, errorMessage);
    }

    //isEquals

    /**
     * Asserts that the two given Strings are equal.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to check
     * @param expected     String for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void isEquals(SoftAssert softAssert, String actual, String expected, String errorMessage, List<WebElement> webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        setList(softAssert, webElements);

        softAssert.assertEquals(actual, expected, errorMessage);
    }

    /**
     * Asserts that the two given Strings are equal.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to check
     * @param expected     String for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     */
    public static void isEquals(SoftAssert softAssert, String actual, String expected, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        softAssert.assertEquals(actual, expected, errorMessage);
    }

    /**
     * Asserts that the two given Strings are not equal.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to check
     * @param expected     String for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     */
    public static void isNotEquals(SoftAssert softAssert, String actual, String expected, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        softAssert.assertNotEquals(actual, expected, errorMessage);
    }

    /**
     * Asserts that the two given Strings are equal.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to check
     * @param expected     String for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void isEquals(SoftAssert softAssert, String actual, String expected, String errorMessage, WebElement... webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        List<WebElement> elements = new ArrayList<>();
        Collections.addAll(elements, webElements);

        setList(softAssert, elements);

        softAssert.assertEquals(actual, expected, errorMessage);
    }

    //isEqualsIgnoreCase

    /**
     * Asserts that the two given Strings are equal ignoring case.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to check
     * @param expected     String for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void isEqualsIgnoreCase(SoftAssert softAssert, String actual, String expected, String errorMessage, List<WebElement> webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        setList(softAssert, webElements);

        softAssert.assertEquals(actual.toLowerCase(), expected.toLowerCase(), errorMessage);
    }

    /**
     * Asserts that the two given Strings are equal ignoring case.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to check
     * @param expected     String for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     */
    public static void isEqualsIgnoreCase(SoftAssert softAssert, String actual, String expected, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        softAssert.assertEquals(actual.toLowerCase(), expected.toLowerCase(), errorMessage);
    }

    /**
     * Asserts that the two given Strings are equal ignoring case.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to check
     * @param expected     String for the Expected value to check against
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void isEqualsIgnoreCase(SoftAssert softAssert, String actual, String expected, String errorMessage, WebElement... webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        List<WebElement> elements = new ArrayList<>();
        Collections.addAll(elements, webElements);

        setList(softAssert, elements);

        softAssert.assertEquals(actual.toLowerCase(), expected.toLowerCase(), errorMessage);
    }

    //contains

    /**
     * Asserts that the actual string contains the expected string.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to see if it contains the expected value
     * @param expected     String for the Expected value to see if it is contained in the actual value
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void contains(SoftAssert softAssert, String actual, String expected, String errorMessage, List<WebElement> webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        setList(softAssert, webElements);

        softAssert.assertTrue(actual.contains(expected), errorMessage);
    }

    /**
     * Asserts that the actual string contains the expected string.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to see if it contains the expected value
     * @param expected     String for the Expected value to see if it is contained in the actual value
     * @param errorMessage String for the error message to print if it fails.
     */
    public static void contains(SoftAssert softAssert, String actual, String expected, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        softAssert.assertTrue(actual.contains(expected), errorMessage);
    }

    /**
     * Asserts that the actual string starts with the expected string.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to see if it contains the expected value
     * @param expected     String for the Expected value to see if it is contained in the actual value
     * @param errorMessage String for the error message to print if it fails.
     */

    public static void startsWith(SoftAssert softAssert, String actual, String expected, String errorMessage) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        softAssert.assertTrue(actual.startsWith(expected), errorMessage);
    }

    /**
     * Asserts that the actual string contains the expected string.  If it is not true, then the current step will fail, and be reported as such.
     *
     * @param softAssert   SoftAssert to run the check on
     * @param actual       String for the actual value to see if it contains the expected value
     * @param expected     String for the Expected value to see if it is contained in the actual value
     * @param errorMessage String for the error message to print if it fails.
     * @param webElements  WebElements related to the assertion for screenshot purposes.
     */
    public static void contains(SoftAssert softAssert, String actual, String expected, String errorMessage, WebElement... webElements) {
        errorMessage = IOUtil.updateErrorMessage(errorMessage, actual, expected);
        List<WebElement> elements = new ArrayList<>();
        Collections.addAll(elements, webElements);

        setList(softAssert, elements);

        softAssert.assertTrue(actual.contains(expected), errorMessage);
    }

    /**
     * Sets the list of WebElements for the soft assert to take a screenshot of, if there is a failure.
     *
     * @param softAssert  SoftAssert that will run the assert
     * @param webElements list of WebElements
     */
    private static void setList(SoftAssert softAssert, List<WebElement> webElements) {
        List<WebElement> elements = new ArrayList<>(webElements);

        softAssert.setWebElements(elements);
    }

    public static void checkIfListContainsValue(SoftAssert softAssert, String expected, String[] arrayValues, String errorMessage) {
        boolean iflag = false;
        for (String arrayValue : arrayValues) {
            if (arrayValue.contains(expected)) {
                iflag = true;
                softAssert.assertTrue(arrayValue.contains(expected), errorMessage);
                break;
            }
        }
        if (!iflag) {
            softAssert.fail(errorMessage);
        }
    }
}