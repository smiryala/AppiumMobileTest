package com.sw.proapp.pages;

import com.aventstack.extentreports.Status;
import com.sw.core.helpers.Common;
import com.sw.core.helpers.ConsoleLog;
import com.sw.core.reporting.ExtentTestManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import java.time.Duration;
import java.util.HashMap;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.sw.core.database.AppDataFromDB;
import com.sw.core.database.Login;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public abstract class MobileBasePage {

	private final ThreadLocal<Common> common = new ThreadLocal<>();
	private ThreadLocal<AppiumDriver<WebElement>> driver = new ThreadLocal<>();
	AppDataFromDB appDataFromDB = null;
	private Login login = null;
	public String languageBase="";

	public MobileBasePage() {
	}

	public MobileBasePage(Common common) {
		this.common.set(common);
		AppiumDriver<WebElement> appiumDriver = (AppiumDriver<WebElement>) common.getDriver();
		driver.set(appiumDriver);
	}
	public MobileBasePage(Common common, String languageBase) {
		this.common.set(common);
		AppiumDriver<WebElement> appiumDriver = (AppiumDriver<WebElement>) common.getDriver();
		driver.set(appiumDriver);
		this.languageBase=languageBase;
	}

	public Common getCommon() {
		return common.get();
	}

	public AppiumDriver<WebElement> getDriver() {
		return driver.get();
	}

	public boolean enterTextToElementByTextForIOS(String text, String enterText) {
		try {
			iOSWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
			WebElement element = getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'"))
					.get(0);
			getCommon().click(element, "unable to click element with text" + text);
			getCommon().type(element, enterText);
			pressEnter();

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickEditPencilBidsSummaryByText(String text, int index, String language) {
		try {
			text = getTextByLanguage(text, language);
			// clickElementbyXpath("//XCUIElementTypeButton[@name=\"" + text + "\"]");
			getDriver().findElementsByXPath("//XCUIElementTypeButton[@name=\"" + text + "\"]").get(index - 1).click();
			return true;
		} catch (Exception e) {
			ConsoleLog.error("unable to find and click element with xpath that contain text: " + text);
			return false;
		}
	}

	public boolean enterTextToElementByContainsTextForIOS(String text, String enterText, boolean returnFirst) {
		try {
			iOSWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
			if (getDriver().findElements(MobileBy.iOSNsPredicateString("value BEGINSWITH[c] '" + text + "'"))
					.size() > 0) {
				WebElement element = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("value BEGINSWITH[c] '" + text + "'")).get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				if (returnFirst) {
					return true;
				}
			}
			if (getDriver().findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + text + "'"))
					.size() > 0) {
				WebElement element = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + text + "'")).get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				if (returnFirst) {
					return true;
				}
			}
			if (getDriver().findElements(MobileBy.iOSNsPredicateString("label BEGINSWITH[c] '" + text + "'"))
					.size() > 0) {
				WebElement element = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("label BEGINSWITH[c] '" + text + "'")).get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				if (returnFirst) {
					return true;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean enterTextToElementByContainsTextForIOS(String text, String enterText) {
		try {
			iOSWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
			if (getDriver().findElements(MobileBy.iOSNsPredicateString("value BEGINSWITH[c] '" + text + "'"))
					.size() > 0) {
				WebElement element = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("value BEGINSWITH[c] '" + text + "'")).get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				// pressEnter();
			}

			if (getDriver().findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + text + "'"))
					.size() > 0) {
				WebElement element = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + text + "'")).get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				// pressEnter();
			}

			if (getDriver().findElements(MobileBy.iOSNsPredicateString("label BEGINSWITH[c] '" + text + "'"))
					.size() > 0) {
				WebElement element = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("label BEGINSWITH[c] '" + text + "'")).get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				// pressEnter();
			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isNumber(String text) {
		for (char c : text.toCharArray()) {
			if (("-0123456789.").indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}

	public boolean enterTextToElementByTextLabel(String text, String enterText) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				iOSWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
				WebElement element = getDriver().findElements(MobileBy.iOSNsPredicateString("label == '" + text + "'"))
						.get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				// getDriver().navigate().back();
			} else {
				androidWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
				WebElement element = getDriver().findElement(
						MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"));
				getCommon().click(element, "unable to click element with text" + text);
				if (isNumber(enterText)) {
					for (int i = 0; i <= 6; i++) {
						((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.DEL));
					}
					for (char c : enterText.toCharArray()) {
						if ((".").indexOf(c) == 0) {
							((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.NUMPAD_DOT));
						} else {
							((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
						}
					}
				} else {
					getDriver().getKeyboard().pressKey(enterText);
				}
				getDriver().navigate().back();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickElementByTextValue(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				iOSWaitForElementByText(text, "Unable to waint for element with text " + text);
				System.out.println(
						getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'")).size());
				getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'")).get(0).click();
				// getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text +
				// "'")).get(1).click();
			} else {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(
						MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")));
				getCommon().click(
						getDriver().findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")),
						"unable to click element with text" + text);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementExistsByTextLabel(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver().findElement(MobileBy.iOSNsPredicateString("label == '" + text + "'")).isEnabled();
			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementEnabledByText(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "'")).isEnabled();
			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean enterTextToElementByExactText(String text, String enterText) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				iOSWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
				WebElement element = getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'"))
						.get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				// getDriver().navigate().back();
			} else {
				androidWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
				WebElement element = getDriver().findElement(
						MobileBy.AndroidUIAutomator("new UiSelector().textMatches(\"" + text + "\").instance(0)"));
				getCommon().click(element, "unable to click element with text" + text);

				if (isNumber(enterText)) {
					for (int i = 0; i <= 6; i++) {
						((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.DEL));
					}
					for (char c : enterText.toCharArray()) {

						if (("-").indexOf(c) == 0) {
							((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.NUMPAD_SUBTRACT));
						}

						if ((".").indexOf(c) == 0) {
							((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.NUMPAD_DOT));
						} else {
							((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
						}
					}
				} else {
					getDriver().getKeyboard().pressKey(enterText);
				}
				getDriver().navigate().back();
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean enterTextToElementByText(String text, String enterText) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				iOSWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
				WebElement element = getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'"))
						.get(0);
				getCommon().click(element, "unable to click element with text" + text);
				getCommon().type(element, enterText);
				// getDriver().navigate().back();
			} else {
				androidWaitForElementByText(text, "unable to enter text on element field with placeholder: " + text);
				WebElement element = getDriver().findElement(
						MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"));
				getCommon().click(element, "unable to click element with text" + text);

				if (isNumber(enterText)) {
					for (int i = 0; i <= 6; i++) {
						((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.DEL));
					}
					for (char c : enterText.toCharArray()) {
						if ((".").indexOf(c) == 0) {
							((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.NUMPAD_DOT));
						} else {
							((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
						}
					}
				} else {
					getDriver().getKeyboard().pressKey(enterText);
				}
				getDriver().navigate().back();
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickLastElementByTextIOSANDROID(String textAndroid, String textIOS, String language,
			boolean trueFalse) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				textIOS = getTextByLanguage(textIOS, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions
						.elementToBeClickable(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + textIOS + "'")));
				int LastElement = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + textIOS + "'")).size();
				if (trueFalse) {
					getCommon().click(getDriver()
							.findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + textIOS + "'")).get(0),
							"unable to click element with text" + textIOS);
				} else {
					getCommon().click(getDriver()
							.findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + textIOS + "'"))
							.get(LastElement - 1), "unable to click element with text" + textIOS);
				}
			} else {
				if (trueFalse) {
					clickElementbyXpath("//android.widget.ImageView[@content-desc=\"Menu\"]");
				} else {
					textAndroid = getTextByLanguage(textAndroid, language);
					WebDriver driver = getDriver();
					new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(MobileBy
							.AndroidUIAutomator("new UiSelector().textContains(\"" + textAndroid + "\").instance(0)")));
					getCommon().click(
							getDriver().findElement(MobileBy.AndroidUIAutomator(
									"new UiSelector().textContains(\"" + textAndroid + "\").instance(0)")),
							"unable to click element with text" + textAndroid);
				}
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickLastElementByTextIOSANDROID(String textAndroid, String textIOS, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				textIOS = getTextByLanguage(textIOS, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions
						.elementToBeClickable(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + textIOS + "'")));
				int LastElement = getDriver()
						.findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + textIOS + "'")).size();
				getCommon().click(
						getDriver().findElements(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + textIOS + "'"))
								.get(LastElement - 1),
						"unable to click element with text" + textIOS);
			} else {
				textAndroid = getTextByLanguage(textAndroid, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(MobileBy
						.AndroidUIAutomator("new UiSelector().textContains(\"" + textAndroid + "\").instance(0)")));
				getCommon().click(
						getDriver().findElement(MobileBy.AndroidUIAutomator(
								"new UiSelector().textContains(\"" + textAndroid + "\").instance(0)")),
						"unable to click element with text" + textAndroid);

			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean androidWaitForEditField() {
		WebDriver driver = getDriver();
		try {
			new WebDriverWait(driver, 5).until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.className("android.widget.EditText")));
		} catch (Exception e) {
			ConsoleLog.error("unable to find input fields on android screen");
		}
		try {
			new WebDriverWait(driver, 10).until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.className("android.widget.EditText")));
		} catch (Exception e) {
			ConsoleLog.error("unable to find input fields on android screen");
			return false;
		}
		return true;
	}

	public boolean androidWaitForElementByText(String text, String errorMessage) {
		if (text.equals("espera men")) {
			System.out.println("PageSource "+getCommon().getDriver().getPageSource());
		}
		WebDriver driver = getDriver();
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(
					MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")));
			return true;
		} catch (Exception e) {
			ConsoleLog.error(errorMessage);
		}
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(
					MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")));
			return true;
		} catch (Exception e) {
			ConsoleLog.error(errorMessage);
			return false;
		}
	}


	public boolean iOSWaitForElementByText(String text, String errorMessage) {
		if (text.equals("espera men")) {
			System.out.println("PageSource "+getCommon().getDriver().getPageSource());
		}
		WebDriver driver = getDriver();
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions
					.presenceOfElementLocated(MobileBy.iOSNsPredicateString("value == '" + text + "' or name == '" + text + "'")));
			return true;
		} catch (Exception e) {
			ConsoleLog.error(errorMessage);
		}
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions
					.presenceOfElementLocated(MobileBy.iOSNsPredicateString("value == '" + text + "' or name == '" + text + "'")));
			return true;
		} catch (Exception e) {
			ConsoleLog.error(errorMessage);
			return false;
		}
	}

	public boolean scrollToElementByText(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				JavascriptExecutor js = (JavascriptExecutor) ((IOSDriver) getCommon().getDriver());
				HashMap scrollObject = new HashMap<>();
				scrollObject.put("predicateString", "value == '" + text + "'");
				//js.executeScript("mobile: scroll", scrollObject);
				js.executeScript("mobile: scroll, {'direction': 'down'}", scrollObject);
			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to find element with text: " + text);
				getDriver().findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
								+ text + "\").instance(0));"));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean scrollToElementByTextWait(String text, int time) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				common.get().sleepFor(time);
				JavascriptExecutor js = (JavascriptExecutor) ((IOSDriver) getCommon().getDriver());
				HashMap scrollObject = new HashMap<>();
				scrollObject.put("predicateString", "value == '" + text + "'");
				js.executeScript("mobile: scroll", scrollObject);
			} else {
				common.get().sleepFor(time);
				getDriver().findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
								+ text + "\").instance(0));"));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getTextByIndex(String text, int index) {
		if (!text.contains("°")) {
			return text;
		}
		String[] textEnglishSpanish = text.split("°");

		return textEnglishSpanish[index - 1];
	}

	public String checkIfEnglishOrSpanish(String text) {

		try {
			getCommon().sleepFor(2);
			if (getCommon().getDriver() instanceof IOSDriver) {
				if (getDriver()
						.findElements(MobileBy.iOSNsPredicateString("value == '" + getTextByIndex(text, 2) + "'"))
						.size() > 0) {
					ConsoleLog.info("Spanish language detected: using word " + getTextByIndex(text, 2));
					return "spanish";
				} else {
					ConsoleLog.info("English language detected: using word " + getTextByIndex(text, 1));
					return "english";
				}
			} else {
				if (getDriver()
						.findElements(MobileBy.AndroidUIAutomator(
								"new UiSelector().textContains(\"" + getTextByIndex(text, 2) + "\").instance(0)"))
						.size() > 0) {
					ConsoleLog.info("Spanish language detected: using word " + getTextByIndex(text, 2));
					return "spanish";
				} else {
					ConsoleLog.info("English language detected: using word " + getTextByIndex(text, 1));
					return "english";
				}
			}
		} catch (Exception e) {
			ConsoleLog.error("No language found", e);
			return "no language found";
		}
	}

	public String checkIfEnglishOrSpanish(String text, String language) {
		if(language==null) {
			language="";
		}
		if (language.equals("english") || language.equals("spanish")) {
			languageBase=language;
			return language;
		}

		try {
			getCommon().sleepFor(2);
			if (getCommon().getDriver() instanceof IOSDriver) {
				if (getDriver()
						.findElements(MobileBy.iOSNsPredicateString("value == '" + getTextByIndex(text, 2) + "'"))
						.size() > 0) {
					ConsoleLog.info("Spanish language detected: using word " + getTextByIndex(text, 2));
					languageBase="spanish";
					return "spanish";
				} else {
					ConsoleLog.info("English language detected: using word " + getTextByIndex(text, 1));
					languageBase="english";
					return "english";
				}
			} else {
				if (getDriver()
						.findElements(MobileBy.AndroidUIAutomator(
								"new UiSelector().textContains(\"" + getTextByIndex(text, 2) + "\").instance(0)"))
						.size() > 0) {
					ConsoleLog.info("Spanish language detected: using word " + getTextByIndex(text, 2));
					languageBase="spanish";
					return "spanish";
				} else {
					ConsoleLog.info("English language detected: using word " + getTextByIndex(text, 1));
					languageBase="english";
					return "english";
				}
			}
		} catch (Exception e) {
			ConsoleLog.error("No language found", e);
			return "no language found";
		}
	}

	public String getTextByLanguage(String text, String language) {
		if (language.equals("english")) {
			text = getTextByIndex(text, 1);
			return text;
		} else {
			text = getTextByIndex(text, 2);
			return text;
		}
	}

	public WebElement getElementByTypeAndIndex(String Type, int index) {
		return getDriver().findElementsByXPath("//" + Type).get(index);
	}

	public boolean scrollToElementByText(String text, String textToWait, String language) {
		try {
			ConsoleLog.info("scroll element " + language);
			if (getCommon().getDriver() instanceof IOSDriver) {
				textToWait = getTextByLanguage(textToWait, language);
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				JavascriptExecutor js = (JavascriptExecutor) ((IOSDriver) getCommon().getDriver());
				HashMap scrollObject = new HashMap<>();
				scrollObject.put("predicateString", "value == '" + text + "' or name == '" + text + "' or label == '" + text + "' or value contains '" + text + "' or name contains '" + text + "'");
				scrollObject.put("direction", "down");
				js.executeScript("mobile: scroll", scrollObject);
			} else {
				textToWait = getTextByLanguage(textToWait, language);
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(textToWait, "unable to find element with text: " + textToWait);
				getDriver().findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
								+ text + "\").instance(0));"));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickFirstElementByText(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions
						.elementToBeClickable(MobileBy.iOSNsPredicateString("value BEGINSWITH[c] '" + text + "'")));
				getCommon().click(getDriver()
						.findElements(MobileBy.iOSNsPredicateString("value BEGINSWITH[c] '" + text + "'")).get(0),
						"unable to click element with text" + text);

			} else {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
						MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")));
				getCommon().click(
						getDriver().findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")),
						"unable to click element with text" + text);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickPreceedingSiblingbyXpath(String text, String language) {
		try {

			getDriver()
					.findElementByXPath(
							"//XCUIElementTypeButton[@name=\"" + text + "\"]/preceding-sibling::XCUIElementTypeButton")
					.click();
		} catch (Exception e) {
			ConsoleLog.error("unable to find and click element with xpath that contain text: " + text);
		}
	}

	public void clickTaskCheckBoxByNameIOS(String text) {
		clickElementbyXpath("//XCUIElementTypeButton[@name=\"" + text
				+ "\"]/following-sibling::XCUIElementTypeButton[@name=\"off check\"]");
	}

	public void clickElementbyXpath(String xpath, int index) {
		try {
			getDriver().findElementsByXPath(xpath).get(index - 1).click();
		} catch (Exception e) {
			ConsoleLog.error("unable to find and click element with xpath that contain xpath: " + xpath);
		}
	}

	public void clickElementbyXpath(String xpath) {
		try {
			getDriver().findElementByXPath(xpath).click();
		} catch (Exception e) {
			ConsoleLog.error("unable to find and click element with xpath that contain xpath: " + xpath);
		}
	}

	public void typeOnElementbyXpath(String xpath, String text) {
		try {
			getDriver().findElementByXPath(xpath).click();
			getDriver().findElementByXPath(xpath).clear();
			getDriver().findElementByXPath(xpath).sendKeys(text);
		} catch (Exception e) {
			ConsoleLog.error("unable to find and click element with xpath that contain xpath: " + xpath);
		}
	}

	public void clickBlueEditPencilBYTaskName(String taskName, String language) {
		if (getCommon().getDriver() instanceof IOSDriver) {
			taskName = getTextByLanguage(taskName, language);
			iOSWaitForElementByText(taskName, "unable to find element with text: " + taskName);
			clickElementbyXpath("//XCUIElementTypeStaticText[@name=\"" + taskName
					+ "\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[@name=\"editBlue\"]");

		} else {
			androidWaitForElementByText(taskName, "unable to find element with text: " + taskName);
			taskName = getTextByLanguage(taskName, language);
			clickFollowingSiblingbyXpath("Walls", language);
		}
	}

	public void selectFromIosWheelerByText(String text, String language) {
		text = getTextByLanguage(text, language);
		getDriver().findElementByClassName("XCUIElementTypePickerWheel").sendKeys(text);
		clickElementbyXpath("//XCUIElementTypeButton[@name=\"Done\"]");
	}

	public void clickEditPencilBidsSummaryByText(String text, String language) {
		text = getTextByLanguage(text, language);
		clickElementbyXpath("//XCUIElementTypeButton[@name=\"" + text + "\"]");

	}

	/// XCUIElementTypeStaticText[@name="Trim"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeButton[@name="editBlue"])

	public void clickFollowingSiblingbyXpath(String text, String language) {
		WebElement element;
		try {
			element = getDriver().findElementByXPath(
					"//android.widget.TextView[@text=\"" + text + "\"]/following-sibling::android.widget.TextView");
			getCommon().click(element, "unable to click element with text" + text);
		} catch (Exception e) {
			ConsoleLog.error("unable to find and click element with xpath that contain text: " + text);
			return;
		}

	}

	public boolean firstRun = true;

	public boolean checkFirstRun() {
		clickElementbyXpath("/hierarchy/android.widget.RelativeLayout/android.widget.Button");
		if (firstRun) {
			firstRun = false;
			return true;
		}

		return false;
	}

	public boolean clickElementByText(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				iOSWaitForElementByText(text, "unable to find element with text "+text);
				if (getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'")).size() > 0) {
					getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "'")).click();
					return true;
				}
				if (getDriver().findElements(MobileBy.iOSNsPredicateString("name == '" + text + "'")).size() > 0) {
					getDriver().findElement(MobileBy.iOSNsPredicateString("name == '" + text + "'")).click();
					return true;
				}
				if (getDriver().findElements(MobileBy.iOSNsPredicateString("label == '" + text + "'")).size() > 0) {
					getDriver().findElement(MobileBy.iOSNsPredicateString("label == '" + text + "'")).click();
					return true;
				}
			} else {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(
						MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")));
				if (getDriver()
						.findElements(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.size() > 0) {

					getCommon().click(
							getDriver().findElement(MobileBy
									.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")),
							"unable to click element with text" + text);
					return true;
				}
				if (getDriver().findElements(By.name(text)).size() > 0) {
					getCommon().click(getDriver().findElement(By.name(text)),
							"unable to click element with text" + text);
					return true;
				}

				if (getDriver().findElements(By.xpath("//*[contains(@value=\"" + text + "\")]")).size() > 0) {
					getCommon().click(getDriver().findElement(By.name(text)),
							"unable to click element with text" + text);
					return true;
				}

			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickElementByElementName(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				getCommon().sleepFor(3);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions
						.presenceOfElementLocated(MobileBy.iOSNsPredicateString("name == '" + text + "'")));
				getDriver().findElement(MobileBy
						.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name BEGINSWITH[c] '" + text + "'"))
						.click();
			} else {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(
						MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")));
				getCommon().click(
						getDriver().findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")),
						"unable to click element with text" + text);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickElementByTextAndElementType(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions
						.presenceOfElementLocated(MobileBy.iOSNsPredicateString("value == '" + text + "'")));
				getCommon().click(getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "'")),
						"unable to click element with text" + text);
			} else {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(
						MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")));
				getCommon().click(
						getDriver().findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")),
						"unable to click element with text" + text);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickElementByTextIfExists(String text) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				getCommon().sleepFor(3);
				if (getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'")).size() > 0) {
					getCommon().click(getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "'")),
							"unable to click element with text" + text);
				}
			} else {
				getCommon().sleepFor(3);
				if (getDriver()
						.findElements(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.size() > 0) {
					getCommon().click(
							getDriver().findElement(MobileBy
									.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")),
							"unable to click element with text" + text);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getTextByPartialText(String text, String language) {
		text = getTextByLanguage(text, language);
		androidWaitForElementByText(text, "unable to find element with text: " + text);
		return getDriver()
				.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
				.getText();

	}

	public String getTextByXpath(String xpath) {
		try {
			String text = getDriver().findElementByXPath(xpath).getText();
			return text;
		} catch (Exception e) {
			return "error";
		}
	}

	public boolean elementExistsByText(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				if(getDriver().findElements(MobileBy.iOSNsPredicateString("value Contains '" + text + "'")).size()>0) {
					System.out.println("FOUND TEXT ======>  "+getDriver().findElements(MobileBy.iOSNsPredicateString("value Contains '" + text + "'")).get(0).getText().trim());
					return getDriver().findElements(MobileBy.iOSNsPredicateString("value Contains '" + text + "'")).get(0).getText().trim().equals(text);
				}
				return getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "' or name == '"+ text + "'")).isEnabled();
			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to find element with text: " + text);
				System.out.println("----------" + getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.getText());
				boolean exists = getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.isDisplayed();
				if (exists) {
					return true;
				}
				if (exists == false) {
					return getDriver()
							.findElement(MobileBy.AndroidUIAutomator(
									"new UiSelector().textContains(\"" + text.replace("$", "£") + "\").instance(0)"))
							.isDisplayed();
				} else {

					if (getDriver().findElements(By.name(text)).size() > 0) {

						return true;
					}

					if (getDriver().findElements(By.xpath("//*[contains(@value=\"" + text + "\")]")).size() > 0) {

						return true;
					} else {
						return false;
					}

				}
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementPresentWithoutFailByText(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'")).size() > 0;
			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver()
						.findElements(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.size() > 0;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementExistsByContainAllWordsIOS(ArrayList<String> text) {
		Boolean exists=true;
		try {
			for (String word:text) {
				String actualText = getDriver()
						.findElement(MobileBy.iOSNsPredicateString("name CONTAINS[c] '" + word + "'")).getText();
				if(actualText.length()<1){
					exists=false;
				}
				System.out.println("=========> "+word+" Found in word " + actualText);
			}
			return exists;


		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementDisplayedByText(String text, String completeText, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				String actualText = getDriver()
						.findElement(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + text + "'")).getText();
				return getDriver().findElement(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + text + "'"))
						.isEnabled() && actualText.equals(completeText);

			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementExistsByContainTextAndSpaceIOS(String text, String secondText) {
		try {
			iOSWaitForElementByText(text, "unable to find element with text: " + text);
			String actualText = getDriver()
					.findElement(MobileBy.iOSNsPredicateString("name CONTAINS[c] '" + text + "'")).getText();
			return actualText.contains(text) && actualText.contains(secondText) && actualText.contains("");

		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementClickByContainTextAndSpaceIOS(String text, String secondText) {
		try {
			iOSWaitForElementByText(text, "unable to find element with text: " + text);
			getDriver().findElement(MobileBy.iOSNsPredicateString("name CONTAINS[c] '" + text + "'")).click();
			// return actualText.contains(text) && actualText.contains(secondText) &&
			// actualText.contains("");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementExistsByContainTextValueAndSpaceIOS(String text, String secondText) {
		try {
			iOSWaitForElementByText(text, "unable to find element with text: " + text);
			String actualText = getDriver()
					.findElement(MobileBy.iOSNsPredicateString("value CONTAINS[c] '" + text + "'")).getText();
			return actualText.contains(text) && actualText.contains(secondText) && actualText.contains("");

		} catch (Exception e) {
			return false;
		}
	}

	public String getAttributyeByText(String text, String attr, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				iOSWaitForElementByText(text, "unable to find element with text: " + text);
				String attributeValue = getDriver()
						.findElement(MobileBy.iOSNsPredicateString("name BEGINSWITH[c] '" + text + "'"))
						.getAttribute(attr);
				System.out.println(attributeValue);
				return attributeValue;

			} else {
				text = getTextByLanguage(text, language);
				androidWaitForElementByText(text, "unable to find element with text: " + text);
				return getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.getAttribute(attr);
			}
		} catch (Exception e) {
			return "No attribute found";
		}
	}

	public boolean elementExistsByText(String text, String language, boolean waitOrNot) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				if (waitOrNot) {
					iOSWaitForElementByText(text, "unable to find element with text: " + text);
				}
				return getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "'")).isEnabled();
			} else {
				text = getTextByLanguage(text, language);
				if (waitOrNot) {
					androidWaitForElementByText(text, "unable to find element with text: " + text);
				}
				return getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.isDisplayed();
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void swipeUP_IOS(WebDriver webDriver, WebElement element) {
//		PointOption  pressOptions= new PointOption();
//		pressOptions.withCoordinates(697, 1391);
//		WaitOptions waitOptions = new WaitOptions();
//		waitOptions.withDuration(Duration.ofMillis(362));
//		PointOption pressOptions1 = new PointOption();
//		pressOptions1.withCoordinates(708, 1274);
//		new TouchAction((PerformsTouchActions) webDriver).press(pressOptions).waitAction(waitOptions).moveTo(pressOptions1).release().perform();
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		HashMap<String, String> scrollObject = new HashMap<>();
		scrollObject.put("direction", "down");
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void swipeDOWN_IOS() {
		PointOption pressOptions = new PointOption();
		pressOptions.withCoordinates(648, 1768);
		WaitOptions waitOptions = new WaitOptions();
		waitOptions.withDuration(Duration.ofMillis(417));
		PointOption pressOptions1 = new PointOption();
		pressOptions1.withCoordinates(682, 1402);
		new TouchAction(getDriver()).press(pressOptions).waitAction(waitOptions).moveTo(pressOptions1).release()
				.perform();
	}

	public void scrollDown() {
//		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		HashMap<String, String> scrollObject = new HashMap<>();
//		scrollObject.put("direction", "up");
//		js.executeScript("mobile: scroll", scrollObject);
		PointOption pressOptions = new PointOption();
		pressOptions.withCoordinates(648, 1768);
		WaitOptions waitOptions = new WaitOptions();
		waitOptions.withDuration(Duration.ofMillis(417));
		PointOption pressOptions1 = new PointOption();
		pressOptions1.withCoordinates(682, 1402);
		new TouchAction(getDriver()).press(pressOptions).waitAction(waitOptions).moveTo(pressOptions1).release()
				.perform();
	}

	public void scrollDown2(int x, int y, long mills, int a, int b) {
//		js.executeScript("mobile: scroll", scrollObject);
		common.get().sleepFor(5);
		PointOption pressOptions = new PointOption();
		pressOptions.withCoordinates(x, y);
		WaitOptions waitOptions = new WaitOptions();
		waitOptions.withDuration(Duration.ofMillis(mills));
		PointOption pressOptions1 = new PointOption();
		pressOptions1.withCoordinates(a, b);
		new TouchAction(getDriver()).press(pressOptions).waitAction(waitOptions).moveTo(pressOptions1).release()
				.perform();
	}

	public void scrollDown3() {
		common.get().sleepFor(2);
		PointOption pressOptions = new PointOption().withCoordinates(
				(getDriver().manage().window().getSize().getWidth()) / 2,
				(int) ((getDriver().manage().window().getSize().getHeight()) / 1.35));
		WaitOptions waitOptions = new WaitOptions();
		waitOptions.withDuration(Duration.ofMillis(3000));
		PointOption pressOptions1 = new PointOption()
				.withCoordinates((getDriver().manage().window().getSize().getWidth()) / 2, 0);
		new TouchAction(getDriver()).press(pressOptions).waitAction(waitOptions).moveTo(pressOptions1).release()
				.perform();
	}

	public void swipeLeft() {
		common.get().sleepFor(2);
		PointOption pressOptions = new PointOption().withCoordinates(
				(getDriver().manage().window().getSize().getWidth()) / 100,
				(int) ((getDriver().manage().window().getSize().getHeight()) / 3));
		WaitOptions waitOptions = new WaitOptions();
		waitOptions.withDuration(Duration.ofMillis(3000));
		PointOption pressOptions1 = new PointOption()
				.withCoordinates((int) ((getDriver().manage().window().getSize().getWidth())/1.1), (int) ((getDriver().manage().window().getSize().getHeight()) / 3));
		new TouchAction(getDriver()).press(pressOptions1).waitAction(waitOptions).moveTo(pressOptions).release()
				.perform();
	}

	protected boolean existsElement(WebElement element) {
		/*
		 * try { element.isDisplayed(); // common.exists(getLocator(element)); } catch
		 * (NoSuchElementException e) { return false; } return true;
		 */

		boolean isElementPresent = true;
		try {
			WebDriver driver = getDriver();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			isElementPresent = element.isDisplayed();
			return isElementPresent;
		} catch (Exception e) {
			isElementPresent = false;
			ConsoleLog.error("unable to find input fields on  screen " + e.getMessage());
			return isElementPresent;
		}

	}

	public By getLocator(WebElement element) {
		try {
			By findBy = (By) FieldUtils.readField(
					FieldUtils.readField(FieldUtils.readField(element, "h", true), "locator", true), "by", true);
			if (findBy != null) {
				return findBy;
			}
		} catch (IllegalAccessException ignored) {
		}
		return null;
	}

	public void clickBackButton() {
		((PressesKey) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public void waitForMobileElements(WebElement element) {
		new WebDriverWait(getDriver(), 15).until(ExpectedConditions.presenceOfElementLocated(getLocator(element)));
	}

	public boolean mobileMoveTo(AppiumDriver driver, By by, By end) {
		By scrollableArea = null;
		if (driver instanceof AndroidDriver)
			scrollableArea = By.xpath("//*[@class='android.widget.ScrollView']//*[@class='android.widget.ScrollView']");
		else if (driver instanceof IOSDriver)
			scrollableArea = By.xpath("//*[@class='UIAScrollView']");

		boolean status = false;
		WebElement element = null;
		int maximunRetryCount = 0;

		while (!status) {
			try {
				element = driver.findElement(by);
				if (element.getLocation().getY() >= (driver.findElement(scrollableArea).getLocation().getY()
						+ driver.findElement(scrollableArea).getSize().height - 20)) {
					throw new Exception();
				}
				status = true;

			} catch (Exception e) {
				try {
					driver.findElement(end);
					break;
				} catch (Exception ex) {
					if (maximunRetryCount == 5)
						break;
					scrollDown3();
					maximunRetryCount++;
					getCommon().sleepFor(1);
				}
			}
		}
		return status;
	}

	public boolean pressEnter() {
		try {
			if (getCommon().getDriver() instanceof IOSDriver)
				((IOSDriver) getCommon().getDriver()).getKeyboard().sendKeys("\n");
			else
				((AndroidDriver) getCommon().getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean hideKeyboard() {
		try {
			if (getCommon().getDriver() instanceof IOSDriver)
				// ((IOSDriver) getCommon().getDriver()).hideKeyboard();
				((IOSDriver) getCommon().getDriver()).getKeyboard().pressKey(Keys.RETURN);
			else
				((AndroidDriver) getCommon().getDriver()).hideKeyboard();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void mobileGrantPermission() {
		try {
			getCommon().sleepFor(3);
			getCommon().getDriver().findElement(By.xpath("//*[@text='OK']")).click();
		} catch (Exception ignored) {
			ConsoleLog.info("No Grant Required");
		}
	}

	public void progressBarLoading() {
		getCommon().sleepFor(2);
		By pbBy = By.xpath("//*[@id='progressBar']");
		try {
			if (getCommon().isElementPresent(pbBy)) {
				getCommon().getWaitUntil().invisibilityOfElements(pbBy, "Progress Bar Still there");
				ConsoleLog.info("Waiting for progress bar to disappear");
			}
		} catch (Exception ignored) {
			ConsoleLog.info("Error in Progress Bar Loading " + ignored.getMessage());
		}
	}

	public void closeAndLaunchApp() {
		((AppiumDriver) getCommon().getDriver()).closeApp();
		((AppiumDriver) getCommon().getDriver()).launchApp();
	}

	@Step("assertEquals actual is {0} expected is {1}")
	public void assertEquals(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
			ExtentTestManager.getTest().log(Status.INFO,
					"Success in assertEquals: Actual:" + actual + " Expected:" + expected);
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					"Error in assertEquals: Actual:" + actual + " Expected:" + expected);
		}
	}

	@Step("assertEquals actual is {0} expected is {1} errMsg {2}")
	public void assertEquals(String actual, String expected, String errorMessage) {
		try {
			Assert.assertEquals(actual, expected,errorMessage);
			ExtentTestManager.getTest().log(Status.INFO,
					"Success in assertEquals: " + errorMessage + ", Actual:" + actual + " Expected:" + expected);
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					"Error in assertEquals: " + errorMessage + ", Actual:" + actual + " Expected:" + expected);
		}
	}

	@Step("assertTrue flag is {0} errMsg {1}")
	public void assertTrue(boolean flag, String errorMessage) {
		try {
			Assert.assertTrue(flag, errorMessage);
			ExtentTestManager.getTest().log(Status.INFO, "Success in assertTrue: " + errorMessage);
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Error in assertTrue: " + errorMessage);
		}
	}

	public void sleepFor(long seconds) {
		getCommon().sleepFor(3);
	}

	public void deviceOrientationToPrtrait() {
		AndroidDriver driver = (AndroidDriver) getDriver();
		driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
	}

	public ArrayList<String> getCredentials(String proAppTestCase) {
		appDataFromDB = new AppDataFromDB(getCommon().getInsertResults());
		ArrayList<String> credentials=new ArrayList<String>();
		try {
			login = appDataFromDB.getLoginDetailsFromDB(proAppTestCase);
			credentials.add(login.getUserName());
			credentials.add(login.getPassword());
		} catch (Exception ex) {
			credentials.add(qaUserName());
			credentials.add(qaPassword());
		}
		return credentials;
	}

	public String qaUserName() {
		try (InputStream input = new FileInputStream("src/test/resources/config/commonConfig.properties")) {
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			String username = prop.getProperty("qa.username");
			return username;
		} catch (IOException ex) {
			ex.printStackTrace();
			ConsoleLog.error("unable to get username from properties file");
			return "";
		}
	}

	public String qaPassword() {
		try (InputStream input = new FileInputStream("src/test/resources/config/commonConfig.properties")) {
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			String password = prop.getProperty("qa.password");
			return password;
		} catch (IOException ex) {
			ex.printStackTrace();
			ConsoleLog.error("unable to get password from properties file");
			return "";
		}
	}
	public boolean elementExistsByText(String text, String language, Boolean wait) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				if(wait) {
					iOSWaitForElementByText(text, "unable to find element with text: " + text);
				}
				return getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "' or name == '"+ text + "'")).isEnabled();
			} else {
				text = getTextByLanguage(text, language);
				if(wait) {
					androidWaitForElementByText(text, "unable to find element with text: " + text);
				}
				System.out.println("----------" + getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.getText());
				boolean exists = getDriver()
						.findElement(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.isDisplayed();
				if (exists) {
					return true;
				}
				if (exists == false) {
					return getDriver()
							.findElement(MobileBy.AndroidUIAutomator(
									"new UiSelector().textContains(\"" + text.replace("$", "£") + "\").instance(0)"))
							.isDisplayed();
				} else {

					if (getDriver().findElements(By.name(text)).size() > 0) {

						return true;
					}

					if (getDriver().findElements(By.xpath("//*[contains(@value=\"" + text + "\")]")).size() > 0) {

						return true;
					} else {
						return false;
					}

				}
			}
		} catch (Exception e) {
			return false;
		}
	}
	public boolean clickElementByTextExact(String text, String language) {
		try {
			if (getCommon().getDriver() instanceof IOSDriver) {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				iOSWaitForElementByText(text, "unable to find element with text "+text);
				if (getDriver().findElements(MobileBy.iOSNsPredicateString("value == '" + text + "'")).size() > 0) {
					getDriver().findElement(MobileBy.iOSNsPredicateString("value == '" + text + "'")).click();
					return true;
				}
				if (getDriver().findElements(MobileBy.iOSNsPredicateString("name == '" + text + "'")).size() > 0) {
					getDriver().findElement(MobileBy.iOSNsPredicateString("name == '" + text + "'")).click();
					return true;
				}
				if (getDriver().findElements(MobileBy.iOSNsPredicateString("label == '" + text + "'")).size() > 0) {
					getDriver().findElement(MobileBy.iOSNsPredicateString("label == '" + text + "'")).click();
					return true;
				}
			} else {
				text = getTextByLanguage(text, language);
				WebDriver driver = getDriver();
				androidWaitForElementByText(text, "unable to find element with text "+text);
				if (getDriver().findElements(By.xpath("//*[@text=\"" + text + "\"]")).size() > 0) {
					clickElementbyXpath("//*[@text=\"" + text + "\"]");
					return true;
				}
				if (getDriver()
						.findElements(MobileBy
								.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"))
						.size() > 0) {

					getCommon().click(
							getDriver().findElement(MobileBy
									.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)")),
							"unable to click element with text" + text);
					return true;
				}
				if (getDriver().findElements(By.name(text)).size() > 0) {
					getCommon().click(getDriver().findElement(By.name(text)),
							"unable to click element with text" + text);
					return true;
				}

				if (getDriver().findElements(By.xpath("//*[contains(@value=\"" + text + "\")]")).size() > 0) {
					getCommon().click(getDriver().findElement(By.xpath("//*[contains(@value=\"" + text + "\")]")),
							"unable to click element with text" + text);
					return true;
				}



			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}