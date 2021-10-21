package com.app.gitsin;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeUtil {

    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";
    //public static final String DEFAULT_MODE = "default";

    public static void applyTheme(String dark) {
        switch (dark){
            case LIGHT_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case DARK_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }

    public static void darkSave(Context context, String choice, SharedPreferences sp) {
        sp = context.getSharedPreferences("darkMode", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("darkMode", choice).commit();
    }

    public static String darkLoad(Context context) {
        SharedPreferences sp = context.getSharedPreferences("darkMode", context.MODE_PRIVATE);
        return sp.getString("darkMode","light");
    }

}
