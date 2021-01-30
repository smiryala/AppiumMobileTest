package com.sw.colorsnap.data;

public class PaintPageData {

    public static final String PAINT_SCREENTITLE = "//android.widget.TextView[@text='Paint']";
    public static final String PAINT_OPEN_IPTITLE = "//android.widget.TextView[@text='Open Instant Paint']";
    public static final String PAINT_OPEN_IPDESC = "//android.widget.TextView[starts-with (@text, 'See any Sherwin-Williams') and ends-with(@text,'own space instantly.')]";
    public static final String PAINT_PICTURE_TITLE = "//android.widget.TextView[@text='Paint a Photo']";
    public static final String PAINT_PICTURE_DESC = "//android.widget.TextView[starts-with (@text, 'Take or choose a photo') and ends-with(@text,' in your space.')]";

    public static final String PAINT_MATCHAPHOTO_TITLE = "//android.widget.TextView[@text='Match a Photo']";
    public static final String PAINT_MATCHAPHOTO_CONTENT = "//android.widget.TextView[@text='Capture colors from any photo and match them to a Sherwin-Williams color.']";
    public static final String PAINT_MATCHAPHOTO_IMAGE = "cardImageView";

    public static final String PAINT_PAINTAPHOTO_TITLE = "//android.widget.TextView[@text='Paint a Photo']";
    public static final String PAINT_PAINTAPHOTO_CONTENT = "//android.widget.TextView[@text='Take or choose a photo and see how our paint colors look in your space.']";

    public static final String EXPLORE_SCAN_COLOR = "//android.widget.TextView[@text = 'Scan Color Number']";
    public static final String EXPLORE_SCAN_COLOR_DESC = "//android.widget.TextView[starts-with (@text, 'Scan a Sherwin-Williams color') and ends-with(@text,' color in a sample scene.)]";
    public static final String PAINT_SCANCOLORNUM_TITLE = "//android.widget.TextView[@text='Scan Color Number']";
    public static final String PAINT_SCANCOLORNUM_CONTENT = "//android.widget.TextView[@text='Scan a Sherwin-Williams color number and see that color in a sample scene.']";
    public static final String PAINT_SCANCOLORNUM_IMAGE = "cardImageView";

    //IOS
    public static final String PAINT_SCREENTITLE_IOS = "**/XCUIElementTypeStaticText[`label == \"Paint\"`]";
    public static final String PAINT_OPEN_IPTITLE_IOS = "Open Instant Paint";
    public static final String PAINT_OPEN_IPDESC_IOS = "See any Sherwin-Williams color in your own space instantly.";
    public static final String PAINT_PICTURE_TITLE_IOS = "Paint a Photo";
    public static final String PAINT_PICTURE_DESC_IOS = "Take or choose a photo and see how our paint colors look in your space.";
}