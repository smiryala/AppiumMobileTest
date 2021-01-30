package com.sw.colorsnap.data;

public class ExplorePageData {
    public static final String EXPLORE_SCREENTITLE = "screenTitle";

    //MAIN FLOW
    public static final String EXPLORE_DCW_TILE = "digitalColorWallCardView";
    public static final String EXPLORE_DCW_HEADER = "//android.widget.TextView[@text = 'Digital Color Wall']";
    public static final String EXPLORE_DCW_DESC = "//android.widget.TextView[starts-with (@text, 'View any Sherwin-Williams paint') and ends-with(@text,'or add to your palette.')]";
    public static final String EXPLORE_COLOR_COLLECTIONS = "//android.widget.TextView[@text = 'Color Collections']";
    public static final String EXPLORE_COLORCOLLEC_DESC = "//android.widget.TextView[starts-with (@text, 'Find colors that work') and ends-with(@text,'throughout your home.')]";
    public static final String EXPLORE_MATCH_PHOTO = "//android.widget.TextView[@text = 'Match a Photo']";
    public static final String EXPLORE_MATCH_PHOTO_DESC = "//android.widget.TextView[starts-with (@text, 'Capture colors from any photo') and ends-with(@text,'Sherwin-Williams color.')]";
    public static final String EXPLORE_PROMO_TILE_VIEW = "promotionsRecyclerView";

    //PROMO TILE
    public static final String EXPLORE_LIVINGWELL_HEADER = "//android.widget.TextView[contains(@text, 'LIVING WELL')]";
    public static final String EXPLORE_PROMO_COLORID = "//android.widget.TextView[contains(@text, 'INTRODUCING COLORSNAP® COLOR ID')]";
    public static final String EXPLORE_PROMO_COLORID_DESC = "//android.widget.TextView[@text = 'Curated palettes to reflect personality.']";
    public static final String EXPLORE_COLOR_CHIPS = "//android.widget.TextView[@text = 'COLOR CHIPS DELIVERED']";
    public static final String EXPLORE_COLOR_OF_YEAR_TITLE = "//android.widget.TextView[@text = '2021 COLOR OF THE YEAR']";
    public static final String EXPLORE_COLOR_OF_YEAR_NAME = "//android.widget.TextView[@text = 'Urbane Bronze']";
    public static final String EXPLORE_COLOR_OF_YEAR_ID = "//android.widget.TextView[@text = 'SW 7048 (245-C7)']";
    public static final String EXPLORE_PAINT_FEATURES = "//android.widget.TextView[@text = 'PAINT FEATURES']";
    public static final String EXPLORE_PAINT_FEATURES_DESC = "//android.widget.TextView[@text = 'Try our latest ways to instantly paint your walls.']";
    public static final String EXPLORE_ORDER_ONLINE= "//android.widget.TextView[contains(@text,'ORDER PAINT AND SUPPLIES ONLINE')]";
    public static final String EXPLORE_EXPERT_COLOR_ADVICE= "//android.widget.TextView[@text = 'REQUEST EXPERT COLOR ADVICE']";


    //iOS PO

    //MAIN FLOW
    public static final String EXPLORE_IOS_SCREENTITLE = "**/XCUIElementTypeStaticText[`label == \"Explore\"`]";
    public static final String EXPLORE_ACK_ALERT = "**/XCUIElementTypeButton[`label == \"I Acknowledge\"`]";

    public static final String EXPLORE_IOS_DCW_HEADER = "**/XCUIElementTypeStaticText[`label == \"Digital Color Wall\"`]";
    public static final String EXPLORE_IOS_DCW_DESC = "**/XCUIElementTypeStaticText[`label BEGINSWITH \"View any Sherwin-Williams paint\"`]";
    public static final String EXPLORE_IOS_COLOR_COLLECTIONS = "**/XCUIElementTypeStaticText[`label == \"Color Collections\"`]";
    public static final String EXPLORE_IOS_COLORCOLLEC_DESC = "**/XCUIElementTypeStaticText[`label BEGINSWITH \"Find colors that work\"`]";
    public static final String EXPLORE_IOS_MATCH_PHOTO = "**/XCUIElementTypeStaticText[`label == \"Match a Photo\"`]";
    public static final String EXPLORE_IOS_MATCH_PHOTO_DESC = "**/XCUIElementTypeStaticText[`label BEGINSWITH \"Capture colors from any photo\"`]";
    public static final String EXPLORE_IOS_SCAN_COLOR = "**/XCUIElementTypeStaticText[`label == \"Scan Color Number\"`]";
    public static final String EXPLORE_IOS_SCAN_COLOR_DESC = "**/XCUIElementTypeStaticText[`label BEGINSWITH \"Scan a Sherwin-Williams color\"`]";

    //PROMO TILE
    public static final String EXPLORE_IOS_LIVINGWELL_HEADER = "**/XCUIElementTypeStaticText[`label == \"Living Well\"`)]";
    public static final String EXPLORE_IOS_PROMO_COLORID = "**/XCUIElementTypeStaticText[`label == \"INTRODUCING COLORSNAP® COLOR ID\")]";
    public static final String EXPLORE_IOS_PROMO_COLORID_DESC = "**/XCUIElementTypeStaticText[`label == \"Curated palettes to reflect personality.\"]";
    public static final String EXPLORE_IOS_COLOR_CHIPS = "**/XCUIElementTypeStaticText[`label == \"COLOR CHIPS DELIVERED\"`]";
    public static final String EXPLORE_IOS_COLOR_OF_YEAR_TITLE = "**/XCUIElementTypeStaticText[`label == \"2021 COLOR OF THE YEAR\"]";
    public static final String EXPLORE_IOS_COLOR_OF_YEAR_NAME = "**/XCUIElementTypeStaticText[`label == \"Urbane Bronze\"`]";
    public static final String EXPLORE_IOS_COLOR_OF_YEAR_ID = "**/XCUIElementTypeStaticText[`label == \"SW 7048 (245-C7)\"`]";
    public static final String EXPLORE_IOS_PAINT_FEATURES = "**/XCUIElementTypeStaticText[`label == \"PAINT FEATURES\"`]";
    public static final String EXPLORE_IOS_PAINT_FEATURES_DESC = "**/XCUIElementTypeStaticText[`label == \"Try our latest ways to instantly paint your walls.\"`]";

}