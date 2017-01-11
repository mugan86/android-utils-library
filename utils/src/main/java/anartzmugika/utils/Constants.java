package anartzmugika.utils;

/***************************************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 24/11/16.
 * Constants values to use in library
 ***************************************************************************************************/

public class Constants {

    //Photos
    public static final String DEFAULT_PHOTO_URL = "";

    //Server (URL CONNECTION PROPERTIES

    public static final String THIRTY_MINUTES_CACHE_AGE = "max-age=1800";
    public static final String ONE_HOUR_CACHE_AGE = "max-age=3600";

    public static final int READ_TIME_TIME_IN_MS = 20000;
    public static final int CONNECT_TIME_TIME_IN_MS = 30000;

    //NETWORK STATUS
    public static final String MOMENT_CONNECTION = "MOMENT_CONNECTION";
    public static final String CONNECT_TO_WIFI = "WIFI";
    public static final String CONNECT_TO_MOBILE = "MOBILE";
    public static final String NOT_CONNECT = "NOT_CONNECT";


    //NETWORK TYPE CONNECTIONS
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    //Data Preferences values
    //Default constants values
    static final String DEFAULT_PREFERENCES = "Default Preferences";
    static final String LANG_PROPERTY = "Language";
    static final String DEFAULT_LANG = "es";

    //Other data
    static final String EMPTY_VALUE = "";

    //LOCATION

    public static final int REQUEST_TO_LOCALIZATION_DEVICE = 101;

    //Urls
    public static final String GOOGLE_PLAY_MARKET_REFERENCE_WITH_ID = "market://details?id=%s";
    public static final String GOOGLE_PLAY_MAIN_PAGE_WITH_ID = "http://play.google.com/store/apps/details?id=%s";

    //DATE FORMATS
    public static final String ONLY_DATA = "yyyy-MM-dd";
    public static final String DATA_AND_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String ONLY_HOUR_WITH_SECONDS = "HH:mm:ss";
    public static final String ONLY_HOUR_WITHOUT_SECONDS = "HH:mm";

}
