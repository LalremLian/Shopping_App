package com.main.store.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonFunction {

/*   public static void setStatusBarGradiant(Activity activity) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         Window window = activity.getWindow();
         Drawable background = activity.getResources().getDrawable(R.drawable.header_gradient);
         window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
         window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
         window.setBackgroundDrawable(background);
      }
   }*/

   public static String optStringNullCheckValue( final String myString) {
      if (myString == null || myString.equalsIgnoreCase( "" )||myString.equalsIgnoreCase( "--" )||myString.equalsIgnoreCase("null"))
         return "";
      else
         return myString;
   }

   public static boolean isValidMail(String email) {
      boolean check;
      Pattern p;
      Matcher m;

      String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
              + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

      p = Pattern.compile(EMAIL_STRING);

      m = p.matcher(email);
      check = m.matches();

      if(!check) {
         //txtEmail.setError("Not Valid Email");
      }
      return check;
   }

   public static boolean isValidMobile(String phone) {

      boolean check;
      Pattern p;
      Matcher m;

      //String MOBILE_STRING = "^(?:\\\\+88|01)?(?:\\\\d{11}|\\\\d{13})$";
      String MOBILE_STRING = "(^(\\+88|0088)?(01){1}[3456789]{1}(\\d){8})$";
//        String MOBILE_STRING = "(^(\\(01){2}[3456789]{1}(\\d){8})$";
      p = Pattern.compile(MOBILE_STRING);
      m = p.matcher(phone);
      check = m.matches();

      return check;
   }

   public static void savePreferences(Context context, String key, String value) {
      SharedPreferences sharedPreferences = PreferenceManager
              .getDefaultSharedPreferences(context);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(key, value);
      editor.commit();

   }

   public static String getPreferences(Context context, String prefKey) {
      SharedPreferences sharedPreferences = PreferenceManager
              .getDefaultSharedPreferences(context);
      return sharedPreferences.getString(prefKey, "");
   }

   public static boolean deleteValue(Context context, String key) {
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
      if (isValidKey2(context, key)) {
         SharedPreferences.Editor editor = sp.edit();
         editor.remove(key);
         editor.apply();
         editor.commit();
         return true;
      }

      return false;
   }

   public static boolean isValidKey2(Context context, String key) {
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
      Map<String, ?> map = sp.getAll();
      if (map.containsKey(key)) {
         return true;
      } else {
         Log.e("SharePref", "No element founded in sharedPrefs with the key " + key);
         return false;
      }
   }
}
