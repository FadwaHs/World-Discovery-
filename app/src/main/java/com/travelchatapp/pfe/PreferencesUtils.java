package com.travelchatapp.pfe;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesUtils {

    public PreferencesUtils()
    {

    }

    public static boolean saveEmail(String email, Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor=prefs.edit();
        prefEditor.putString(Constants.USER_EMAIL,email);
        prefEditor.apply();
        return true;
    }
    public static String getEmail(Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.USER_EMAIL,null);
    }

    public static void removeSession(Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor=prefs.edit();
        prefEditor.putString(Constants.USER_EMAIL,null);
        prefEditor.apply();
    }


    public static boolean savePassword(String password, Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor=prefs.edit();
        prefEditor.putString(Constants.KEY_PASSWORD,password);
        prefEditor.apply();
        return true;
    }
    public static String getPassword(Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_PASSWORD,null);
    }


}
