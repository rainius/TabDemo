package com.dmtech.tabdemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

    public static String formatTime(long time) {
        // 按照给定的格式串（"yyyy/MM/dd"）转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date(time));
    }

    /**
     * 读取当前登录的email账号
     * @param context
     * @return
     */
    public static String getUserEmail(Context context) {
        SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return pref.getString("email", "");
    }

    /**
     * 保存已登录的email账号
     * @param context
     * @param email
     */
    public static void saveUserEmail(Context context, String email) {
        SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("email", email);
        edit.commit();
    }

    public static void runOnUiThread(Runnable r) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(r);
    }

}
