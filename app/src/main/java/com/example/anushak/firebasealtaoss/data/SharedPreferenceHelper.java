package com.example.anushak.firebasealtaoss.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.anushak.firebasealtaoss.model.User;


public class SharedPreferenceHelper {
    private static SharedPreferenceHelper instance = null;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static String SHARE_USER_INFO = "userinfo";
    private static String SHARE_KEY_NAME = "name";
    private static String SHARE_KEY_EMPID= "Empid";
    private static String SHARE_KEY_EMAIL = "email";
    private static String SHARE_KEY_AVATA = "avata";
    private static String SHARE_KEY_UID = "uid";
    private static String SHARE_KEY_DOB = "dob";


    private SharedPreferenceHelper() {}

    public static SharedPreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceHelper();
            preferences = context.getSharedPreferences(SHARE_USER_INFO, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return instance;
    }

    public void saveUserInfo(User user) {
        editor.putString(SHARE_KEY_NAME, user.name);
        editor.putString(SHARE_KEY_EMPID,user.Empid);
        editor.putString(SHARE_KEY_EMAIL, user.email);
        editor.putString(SHARE_KEY_AVATA, user.avata);
        editor.putString(SHARE_KEY_DOB,user.dateofbirth);
        editor.putString(SHARE_KEY_UID, StaticConfig.UID);
        editor.apply();
    }

    public User getUserInfo(){
        String userName = preferences.getString(SHARE_KEY_NAME, "");
        String Empid=preferences.getString(SHARE_KEY_EMPID,"");
        String email = preferences.getString(SHARE_KEY_EMAIL, "");
        String avatar = preferences.getString(SHARE_KEY_AVATA, "");
        String dob=preferences.getString(SHARE_KEY_DOB,"");
        User user = new User();
        user.name = userName;
        user.Empid= Empid;
        user.email = email;
        user.avata = avatar;
        user.dateofbirth=dob;
        return user;
    }

    public String getUID(){
        return preferences.getString(SHARE_KEY_UID, "");
    }

}
