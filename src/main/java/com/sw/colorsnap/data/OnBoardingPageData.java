package com.sw.colorsnap.data;

public class OnBoardingPageData {

    public static final String ONBOARDING_LOGIN = "onboardingLogIn";
    public static final String ONBOARDING_PAGEINDICATOR = "viewPagerIndicator";
    public static final String ONBOARDING_SKIP = "onboardingSkip";
    public static final String ONBOARDING_IMG = "onboardingImage";
    public static final String ONBOARDING_PAP_TITLE = "//android.widget.TextView[@text, Paint a Photo']";
    public static final String ONBOARDING_PAP_DESC = "//android.widget.TextView[contains(@text, 'Take a Photo')]";

    //IOS
    public static final String ONBOARDING_LOGIN_IOS = "**/XCUIElementTypeButton[@label == 'Login']";
    public static final String ONBOARDING_PAGEINDICATOR_IOS = "**/XCUIElementTypePageIndicator[`value == \"page 1 of 5\"`]";
    public static final String ONBOARDING_SKIP_IOS="**/XCUIElementTypeStaticText[`label == \"Skip\"`]";
    public static final String ONBOARDING_IMG_IOS = "**/XCUIElementTypeWindow[2]/*/XCUIElementTypeImage[1]";
    public static final String ONBOARDING_PAP_TITLE_IOS = "Paint a Photo";
    public static final String ONBOARDING_PAP_DESC_IOS = "**/XCUIElementTypeTextView[`value CONTAINS \"Take a Photo or Choose a Photo\"`]";
    public static final String ONBOARDING_ALLOW_IOS= "//XCUIElementTypeButton[@name='Allow']";
    public static final String ONBOARDING_DALLOW_IOS= "//XCUIElementTypeButton[@name='Don’t Allow']";
}