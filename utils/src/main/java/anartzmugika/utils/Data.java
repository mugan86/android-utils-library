package anartzmugika.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

/***********************************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 2014/03/02.
 * -----------------------------------------------------------
 * Class to manage select locale language info and manage preference data to app with one or more
 * values to use in correct app functionality
 ***********************************************************************************************/

public class Data {

    //Local (Language) data preference manage functions

    public static String getLocaleLanguage (Context context)
    {
        String langPref = Constants.LANG_PROPERTY;
        SharedPreferences prefs = context.getSharedPreferences(Constants.DEFAULT_PREFERENCES, Activity.MODE_PRIVATE);
        return prefs.getString(langPref, Constants.DEFAULT_LANG);
    }
    public static void loadLocale(Context context)
    {
        String langPref = Constants.LANG_PROPERTY;
        SharedPreferences prefs = context.getSharedPreferences(Constants.DEFAULT_PREFERENCES, Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, Constants.DEFAULT_LANG);
        changeLang(language, context);
    }

    public static void changeLang(String lang, Context context)
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

    public static SharedPreferences getPreferencesFile(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static String getPreference(Context context, String propertyName){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(propertyName, "");
    }
    public static void setPreference(Context context, String [] propertyNames
            , String [] propertyValues){
        SharedPreferences sharedPreferences = getPreferencesFile(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i< propertyNames.length; i ++)
        {
            editor.putString(propertyNames [i], propertyValues [i]);
        }

        editor.commit();
    }
}
