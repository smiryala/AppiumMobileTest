package com.sw.colorsnap.data;

public class InstantPaintPageData {

    public static final String INSTANTPAINT_STARTPAINT_BUTTON = "//android.widget.Button[@text='Start Painting']";
    public static final String INSTANTPAINT_ALLOW_BUTTON = "//android.widget.Button[@text='ALLOW']";
    public static final String INSTANTPAINT_TITLE = "//android.widget.TextView[@text='Instant Paint']";
    public static final String INSTANTPAINT_BACKBUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    public static final String INSTANTPAINT_FINDCOLORS = "//android.widget.TextView[@text='FIND COLORS']";
    public static final String INSTANTPAINT_MYIDEAS = "//android.widget.TextView[@text='MY IDEAS']";
    public static final String INSTANTPAINT_EXPERTCOLORS = "//android.widget.TextView[@text='EXPERT COLORS']";
    public static final String INSTANTPAINT_ELIPSIS = "//android.widget.ImageView[@content-desc='More options']";


    public static final String INSTANTPAINT_TITLE_IOS = "**/XCUIElementTypeStaticText[`name == \"Instant Paint\"`]";
    public static final String INSTANTPAINT_STARTPAINT_BUTTON_IOS = "label == 'Start Painting' AND name == 'Start Painting' AND type == 'XCUIElementTypeButton'";
    public static final String INSTANTPAINT_DONEBUTTON_IOS = "Done";
    public static final String INSTANTPAINT_ALLOW_BUTTON_IOS="OK";

    public static final String INSTANTPAINT_FINDCOLORS_IOS = "Find Colors";
    public static final String INSTANTPAINT_MYIDEAS_IOS = "My Ideas";
    public static final String INSTANTPAINT_EXPERTCOLORS_IOS = "Expert Colors";
    public static final String INSTANTPAINT_ELIPSIS_IOS = "Show additional actions";
}