package anartzmugika.utils.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

import anartzmugika.utils.Constants;

/***********************************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 2014/03/02. Update: 2017/01/12
 * ----------------------------------------------------------------------------------------------
 * Class to manage select locale language info and manage preference data to app with one or more
 * values to use in correct app functionality
 ***********************************************************************************************/

public class PreferenceOptions {

    //Local (Language) data preference manage functions

    public static String getLocaleLanguage (Context context)
    {
        String langPref = Constants.LANG_PROPERTY;
        SharedPreferences prefs = context.getSharedPreferences(Constants.DEFAULT_PREFERENCES, Activity.MODE_PRIVATE);
        return prefs.getString(langPref, Constants.DEFAULT_LANG);
    }
    public static void loadLocale(Context context)
    {
        changeLang(getLocaleLanguage(context), context);
    }

    private static void changeLang(String lang, Context context)
    {
        if (lang.equalsIgnoreCase(Constants.EMPTY_VALUE))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang, context);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private static void saveLocale(String lang, Context context)
    {
        String langPref = Constants.LANG_PROPERTY;
        SharedPreferences prefs = context.getSharedPreferences(Constants.DEFAULT_PREFERENCES, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    //General preference manage functions

    private static SharedPreferences getPreferencesFile(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static String getPreferenceValue(Context context, String propertyName){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(propertyName, "");
    }
    public static void setPreferenceValues(Context context, String [] propertyNames
            , String [] propertyValues){
        SharedPreferences sharedPreferences = getPreferencesFile(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i< propertyNames.length; i ++)
        {
            editor.putString(propertyNames [i], propertyValues [i]);
        }

        editor.commit();
    }

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "intro-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PreferenceOptions(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
